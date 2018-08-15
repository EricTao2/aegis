package com.erictao.aegis.aegiscore.properties;

import lombok.Data;

/**
 * @author TJX
 * @Title: CheckReNoProperties
 * @date 2018/7/2710:31
 */
@Data
public class ReplayAttacksProperties {


    private String reqNoName = "ReqNo";

    private String reqNoRedisPrefix = "AegisReqNo";

    private Long reqNoTimeout = 30000L;

    private String timeUnit = "ms";

}
