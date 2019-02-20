package com.erictao.aegis.validator.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author TJX
 * @Title: AegisApiProperties
 * @date 2018/7/2710:29
 */
@ConfigurationProperties(prefix = "aegis.annotation")
@Data
public class AegisValidatorProperties {

}
