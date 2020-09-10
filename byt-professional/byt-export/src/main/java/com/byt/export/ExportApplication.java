package com.byt.export;

import com.byt.common.security.annotation.EnableBytFeignClients;
import com.byt.common.security.annotation.EnableBytResourceServer;
import com.byt.common.swagger.annotation.EnableBytSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: shenh
 * @Date: 2020/2/18
 * 导出服务
 */
@EnableBytSwagger2
@SpringCloudApplication
@EnableBytFeignClients
@EnableBytResourceServer
public class ExportApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExportApplication.class, args);
	}
}
