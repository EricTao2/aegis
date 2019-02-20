package com.github.erictao2.aegis.core;

import com.github.erictao2.aegis.core.properties.AegisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author TJX
 * @Title: AegisApiConfigration
 * @date 2018/7/2710:18
 */
@ComponentScan
@Configuration
@EnableConfigurationProperties(AegisProperties.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AegisConfig {
    /**
     * 运行时的参数Properties对象
     */
    @Autowired
    private AegisProperties aegisProperties;


}
