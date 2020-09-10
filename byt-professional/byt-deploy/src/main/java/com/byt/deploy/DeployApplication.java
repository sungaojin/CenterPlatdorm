package com.byt.deploy;

import com.byt.common.security.annotation.EnableBytFeignClients;
import com.byt.common.security.annotation.EnableBytResourceServer;
import com.byt.common.swagger.annotation.EnableBytSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: shenh
 * @Date: 2020/2/18
 * 业务配置服务
 */
@EnableBytSwagger2
@SpringCloudApplication
@EnableBytFeignClients
@EnableBytResourceServer
public class DeployApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeployApplication.class, args);
	}
}
