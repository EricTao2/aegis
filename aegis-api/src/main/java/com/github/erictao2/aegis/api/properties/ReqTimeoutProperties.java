package com.github.erictao2.aegis.api.properties;

import lombok.Data;

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
