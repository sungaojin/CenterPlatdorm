package com.byt.common.datasource;

import com.byt.common.datasource.config.DruidDataSourceProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author liyang
 * @date 2020-02-18
 * <p>
 * 自动配置类
 */
@AllArgsConstructor
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DataSourceAutoConfiguration {
}
