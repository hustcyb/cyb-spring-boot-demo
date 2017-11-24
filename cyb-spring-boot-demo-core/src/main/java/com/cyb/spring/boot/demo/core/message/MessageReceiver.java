package com.cyb.spring.boot.demo.core.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cyb.spring.boot.demo.domain.Student;

import cyb.spring.boot.demo.common.json.JsonUtils;

@Component
public class MessageReceiver {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(MessageReceiver.class);

	/**
	 * 接收字符值消息
	 * 
	 * @param value
	 *            字符值
	 */
	@RabbitListener(queues = "${cyb.rabbitmq.queue.value}")
	public void receiveValue(String value) {
		logger.debug("receive value message, value = {}", value);
	}

	/**
	 * 接收学生消息
	 * 
	 * @param student
	 *            学生
	 */
	@RabbitListener(queues = "${cyb.rabbitmq.queue.student}")
	public void receiveStudent(Student student) {
		logger.debug("receive student message, student = {}",
				JsonUtils.bean2Json(student));
	}
}
