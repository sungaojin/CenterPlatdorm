package com.byt.common.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyang
 * @date 2020-02-14
 * <p>
 * 参考DruidDataSourceWrapper
 */
@Data
@Component
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceProperties {
	private String username;
	private String password;
	private String url;
	private String driverClassName;
}
