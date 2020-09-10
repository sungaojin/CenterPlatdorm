package com.byt.drafting;

import com.byt.common.security.annotation.EnableBytFeignClients;
import com.byt.common.security.annotation.EnableBytResourceServer;
import com.byt.common.swagger.annotation.EnableBytSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: shenh
 * @Date: 2020/2/18
 * 托拉拽服务
 */
@EnableBytSwagger2
@SpringCloudApplication
@EnableBytFeignClients
@EnableBytResourceServer
public class DraftingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DraftingApplication.class, args);
	}

}
