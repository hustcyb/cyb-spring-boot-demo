package com.cyb.spring.boot.demo.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching(proxyTargetClass = true)
@EnableAsync
//@EnableScheduling
@MapperScan("com.cyb.spring.boot.demo.core.persistence")
@SpringBootApplication(scanBasePackages = "com.cyb.spring.boot.demo")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
