package com.erictao.aegis.api.advice.processor.impl;

import com.erictao.aegis.api.advice.processor.ReqTimeoutProcessor;
import com.erictao.aegis.api.annotation.DefendTimeout;
import com.erictao.aegis.api.exception.AegisApiException;
import com.erictao.aegis.api.properties.AegisApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author TJX
 * @Title: CheckReqTimeoutProcessor
 * @date 2018/7/3018:10
 */
@Slf4j
@Component
public class CheckReqTimeoutProcessor implements ReqTimeoutProcessor {


    private String timestampName;

    private Long timeout;

    private TimeUnit timeUnit;

    public CheckReqTimeoutProcessor(AegisApiProperties aegisApiProperties) {
        this.timestampName = aegisApiProperties.getReqTimeout().getTimeoutName();
        this.timeout = aegisApiProperties.getReqTimeout().getTimeout();
        if (StringUtils.equalsIgnoreCase("s", aegisApiProperties.getReqTimeout().getTimeUnit())) {
            this.timeUnit = TimeUnit.SECONDS;
        } else {
            this.timeUnit = TimeUnit.MILLISECONDS;
        }
    }

    @Override
    public void process(JoinPoint joinPoint, HttpServletRequest request, DefendTimeout ann) {
        String timestampName = StringUtils.isBlank(ann.name()) ? this.timestampName : ann.name();
        TimeUnit timeUnit = ann.timeUnit().length == 0 ? this.timeUnit : ann.timeUnit()[0];
        Long timeout = ann.timeout() > 0L ? ann.timeout() : this.timeout;

        String timestampStr = request.getParameter(timestampName);
        if(StringUtils.isBlank(timestampStr)){
            log.error("缺少参数:" + timestampName);
            throw new AegisApiException("缺少参数:" + timestampName);

        }
        if(!StringUtils.isNumeric(timestampStr)){
            log.error("参数格式错误:" + timestampName + "应该为正数");
            throw new AegisApiException("参数格式错误:" + timestampName + "应该为正数");

        }
        Long timestamp = Long.parseLong(timestampStr);
        Long nowTimeStamp = System.currentTimeMillis();

        if (TimeUnit.SECONDS.equals(timeUnit)) {
            nowTimeStamp = nowTimeStamp / 1000;
        }
        if (timestamp - nowTimeStamp > 0) {
            throw new AegisApiException("请求时间戳大于当前系统时间.");
        }
        if (nowTimeStamp - timestamp > timeout) {
            throw new AegisApiException("请求超时,"+ timestampName +"=" + timestamp, HttpStatus.CONFLICT);
        }
    }
}
