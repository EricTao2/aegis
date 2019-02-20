package main.java.com.erictao.aegis.validator;

import com.erictao.aegis.validator.properties.AegisValidatorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author TJX
 * @Title: AegisApiConfigration
 * @date 2018/7/2710:18
 */
@ComponentScan
@Configuration
@EnableConfigurationProperties(AegisValidatorProperties.class)
public class AegisValidatorConfig {
    /**
     * 运行时的参数Properties对象
     */
    @Autowired
    private AegisValidatorProperties aegisValidatorProperties;


}
