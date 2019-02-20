package com.github.erictao2.aegis.api.properties;

import lombok.Data;

/**
 * @author TJX
 * @Title: CheckReNoProperties
 * @date 2018/7/2710:31
 */
@Data
public class ReplayAttacksProperties {


    private String reqNoName = "reqNo";

    private String reqNoRedisPrefix = "aegisReqNo";

    private Long reqNoTimeout = 30000L;

    private String timeUnit = "ms";

}
