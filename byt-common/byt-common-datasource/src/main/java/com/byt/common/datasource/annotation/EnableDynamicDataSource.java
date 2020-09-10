package com.byt.common.datasource.annotation;

import com.byt.common.datasource.config.DynamicDataSourceConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author liyang
 * @date 2020-02-18
 * <p>
 * 开启动态数据源
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({DynamicDataSourceConfig.class})
public @interface EnableDynamicDataSource {
}
