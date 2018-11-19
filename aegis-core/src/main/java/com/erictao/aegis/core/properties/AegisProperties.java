package com.erictao.aegis.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author TJX
 * @Title: AegisApiProperties
 * @date 2018/7/2710:29
 */
@ConfigurationProperties(prefix = "aegis.api")
@Data
public class AegisProperties {

}
