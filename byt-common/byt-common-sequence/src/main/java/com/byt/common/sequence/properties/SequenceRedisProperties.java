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
 * 发号器Redis配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "byt.xsequence.redis")
public class SequenceRedisProperties extends BaseSequenceProperties {

}
