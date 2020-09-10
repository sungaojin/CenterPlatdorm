package com.byt.daemon.quartz;

import com.byt.common.security.annotation.EnableBytFeignClients;
import com.byt.common.security.annotation.EnableBytResourceServer;
import com.byt.common.swagger.annotation.EnableBytSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author frwcloud
 * @date 2019/01/23
 * 定时任务模块
 */
@EnableBytSwagger2
@SpringCloudApplication
@EnableBytFeignClients
@EnableBytResourceServer
public class BytDaemonQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytDaemonQuartzApplication.class, args);
	}
}
