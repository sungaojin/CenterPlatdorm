package com.byt.common.sequence.properties;

/**
 * @author liyang
 * @date 2020-02-26
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liyang
 * @date 2019/5/26
 * <p>
 * 发号器DB配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "byt.xsequence.db")
public class SequenceDbProperties extends BaseSequenceProperties {
	/**
	 * 表名称
	 */
	private String tableName = "byt_sequence";
	/**
	 * 重试次数
	 */
	private int retryTimes = 1;

}
