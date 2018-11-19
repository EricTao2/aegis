package com.erictao.aegis.api.properties;

import lombok.Data;

/**
 * @author TJX
 * @Title: ReqTimeoutProperties
 * @date 2018/7/3018:12
 */
@Data
public class ModifyParameterProperties {

    private String signName = "reqSign";

    private String algorithm  = "md5";

    private String key = "aegis-key";

    private boolean isUpperSign = false;
}
