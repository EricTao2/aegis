package com.erictao.aegis.aegiscore.advice.processor.impl;

import com.alibaba.fastjson.JSONObject;
import com.erictao.aegis.aegiscore.advice.processor.ModifyParameterProcessor;
import com.erictao.aegis.aegiscore.annotation.DefendModify;
import com.erictao.aegis.aegiscore.exception.AegisApiException;
import com.erictao.aegis.aegiscore.properties.AegisApiProperties;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TJX
 * @Title: ModifyParameterProcessor
 * @date 2018/8/218:32
 */
public class CheckModifyParameterProcessor implements ModifyParameterProcessor {

    private String signName;

    private String algorithm;

    private String key;

    private boolean isUpperSign;

    public CheckModifyParameterProcessor(AegisApiProperties aegisApiProperties) {
        this.signName = aegisApiProperties.getModifyParameter().getSignName();
        this.algorithm = aegisApiProperties.getModifyParameter().getAlgorithm();
        this.key = aegisApiProperties.getModifyParameter().getKey();
        this.isUpperSign = aegisApiProperties.getModifyParameter().isUpperSign();
    }

    @Override
    public void process(JoinPoint joinPoint, HttpServletRequest request, DefendModify ann) {
        String signName = StringUtils.isBlank(ann.name()) ? this.signName : ann.name();
        String algorithm = StringUtils.isBlank(ann.algorithm()) ? this.algorithm : ann.algorithm();
        String key = StringUtils.isBlank(ann.key()) ? this.key : ann.key();

        Map<String, String[]> map = request.getParameterMap();
        Map<String,String[]> mapWithoutSign = new HashMap<>(map);
        String[] sign = mapWithoutSign.remove(signName);
        if (sign == null) {
            throw new AegisApiException("缺少参数:" + signName);
        }
        String json = JSONObject.toJSONString(mapWithoutSign);
        String encodeParameters= digest(json, algorithm, key, isUpperSign);
        if (!StringUtils.equals(sign[0], encodeParameters)) {
            throw new AegisApiException("参数签名错误:" + signName + "=" + sign[0]);
        }
        System.out.println(JSONObject.toJSONString(map));
        System.out.println(encodeParameters);
    }

    @Override
    public void setKey(String key){
        this.key = key;
    }

    private String digest(String input, String algorithm, String key, boolean isUpperSign) {
        byte[] keys = null;
        byte[] inputs = null;
        try {
            inputs = input.getBytes("UTF-8");
            keys = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        algorithm = StringUtils.upperCase(algorithm);
        String result = null;
        switch (algorithm) {
            case"MD5" : result = Hashing.hmacMd5(keys).hashBytes(inputs).toString();break;
            case"SHA1" : result = Hashing.hmacSha1(keys).hashBytes(inputs).toString();break;
            case"SHA256" : result = Hashing.hmacSha256(keys).hashBytes(inputs).toString();break;
            case"SHA512" : result = Hashing.hmacSha512(keys).hashBytes(inputs).toString();break;
            default:break;
        }
        if (isUpperSign) {
            result = result.toUpperCase();
        }
        return result;
    }
}
