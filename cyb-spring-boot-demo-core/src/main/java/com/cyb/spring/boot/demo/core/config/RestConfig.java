package com.cyb.spring.boot.demo.core.config;

import javax.annotation.Resource;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest接口配置
 * @author Administrator
 *
 */
@Configuration
public class RestConfig {

	@Resource
	private RestTemplateBuilder builder;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
