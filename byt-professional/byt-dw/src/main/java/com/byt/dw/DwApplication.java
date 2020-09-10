package com.byt.dw;

import com.byt.common.security.annotation.EnableBytFeignClients;
import com.byt.common.security.annotation.EnableBytResourceServer;
import com.byt.common.swagger.annotation.EnableBytSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: shenh
 * @Date: 2020/2/18
 * 数仓私仓
 */
@EnableBytSwagger2
@SpringCloudApplication
@EnableBytFeignClients
@EnableBytResourceServer
public class DwApplication {
	public static void main(String[] args) {
		SpringApplication.run(DwApplication.class, args);
	}
}
