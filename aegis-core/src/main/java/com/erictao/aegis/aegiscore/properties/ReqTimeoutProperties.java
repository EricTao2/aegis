package com.erictao.aegis.aegiscore.properties;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author TJX
 * @Title: ReqTimeoutProperties
 * @date 2018/7/3018:12
 */
@Data
public class ReqTimeoutProperties {
    private String timeoutName = "timestamp";

    private Long timeout = 30000L;

    private String timeUnit = "ms";
}
