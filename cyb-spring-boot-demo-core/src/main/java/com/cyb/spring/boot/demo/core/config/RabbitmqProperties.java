package com.cyb.spring.boot.demo.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Rabbitmq属性配置
 * 
 * @author Administrator
 *
 */
@ConfigurationProperties(prefix = "cyb.rabbitmq")
public class RabbitmqProperties {

	/**
	 * 队列名称配置
	 */
	private Queue queue = new Queue();

	/**
	 * 交换机名称
	 */
	private String exchange;

	/**
	 * 路由键配置
	 */
	private RouteKey routeKey = new RouteKey();

	public Queue getQueue() {
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public RouteKey getRouteKey() {
		return routeKey;
	}

	public void setRouteKey(RouteKey routeKey) {
		this.routeKey = routeKey;
	}

	/**
	 * 队列属性配置
	 * 
	 * @author Administrator
	 *
	 */
	public static class Queue {

		/**
		 * 值队列名称
		 */
		private String value;

		/**
		 * 学生队列名称
		 */
		private String student;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getStudent() {
			return student;
		}

		public void setStudent(String student) {
			this.student = student;
		}
	}

	/**
	 * 路由键属性配置
	 * 
	 * @author Administrator
	 *
	 */
	public static class RouteKey {

		/**
		 * 值路由键
		 */
		private String value;

		/**
		 * 学生路由键
		 */
		private String student;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getStudent() {
			return student;
		}

		public void setStudent(String student) {
			this.student = student;
		}
	}
}
