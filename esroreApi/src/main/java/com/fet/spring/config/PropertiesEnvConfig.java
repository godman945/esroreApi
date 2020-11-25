package com.fet.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"application-${spring.profiles.active}.properties"})
public class PropertiesEnvConfig {
}
