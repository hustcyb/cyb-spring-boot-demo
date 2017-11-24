package com.cyb.spring.boot.demo.core.config;

import javax.annotation.Resource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbitmq配置
 * 
 * @author Administrator
 *
 */
@EnableConfigurationProperties({ RabbitmqProperties.class })
@Configuration
public class RabbitmqConfig {

	/**
	 * Rabbitmq配置
	 */
	@Resource
	private RabbitmqProperties rabbitmqProperties;

	/**
	 * 值队列
	 * 
	 * @return 值队列
	 */
	@Bean
	public Queue valueQueue() {
		return new Queue(rabbitmqProperties.getQueue().getValue());
	}

	/**
	 * 学生队列
	 * 
	 * @return 学生队列
	 */
	@Bean
	public Queue studentQueue() {
		return new Queue(rabbitmqProperties.getQueue().getStudent());
	}

	/**
	 * 交换机
	 * 
	 * @return
	 */
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(rabbitmqProperties.getExchange());
	}

	/**
	 * 绑定交换机和值队列
	 * 
	 * @param valueQueue
	 *            值队列
	 * @param exchange
	 *            交换机
	 * @return 绑定
	 */
	@Bean
	public Binding valueBinding(Queue valueQueue, DirectExchange exchange) {
		return BindingBuilder.bind(valueQueue).to(exchange)
				.with(rabbitmqProperties.getRouteKey().getValue());
	}

	/**
	 * 绑定交换机和学生队列
	 * 
	 * @param studentQueue
	 *            学生队列
	 * @param exchange
	 *            交换机
	 * @return
	 */
	@Bean
	public Binding studentBinding(Queue studentQueue, DirectExchange exchange) {
		return BindingBuilder.bind(studentQueue).to(exchange)
				.with(rabbitmqProperties.getRouteKey().getStudent());
	}
}
