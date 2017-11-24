package com.cyb.spring.boot.demo.core.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import com.cyb.spring.boot.demo.core.config.RabbitmqProperties;
import com.cyb.spring.boot.demo.domain.Student;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 消息服务
 * 
 * @author Administrator
 *
 */
@Service
public class AmqpService {

	/**
	 * 日志接口
	 */
	private static Logger logger = LoggerFactory.getLogger(AmqpService.class);

	/**
	 * Rabbitmq属性配置
	 */
	@Resource
	private RabbitmqProperties rabbitmqProperties;

	@Resource
	private AmqpTemplate amqpTemplate;

	/**
	 * 发布字符值消息
	 * 
	 * @param message
	 *            字符值消息
	 */
	public void sendValue(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"MessageService.sendStringMessage: start, message = {}",
					message);
		}

		amqpTemplate.convertAndSend(rabbitmqProperties.getExchange(),
				rabbitmqProperties.getRouteKey().getValue(), message);
		if (logger.isDebugEnabled()) {
			logger.debug("MessageService.sendStringMessage: end");
		}
	}

	/**
	 * 发布学生消息
	 * 
	 * @param student
	 *            学生
	 */
	public void sendStudent(Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"MessageService.sendStudentMessage: start, student = {}",
					JsonUtils.bean2Json(student));
		}

		amqpTemplate.convertAndSend(rabbitmqProperties.getExchange(),
				rabbitmqProperties.getRouteKey().getStudent(), student);
		if (logger.isDebugEnabled()) {
			logger.debug("MessageService.sendStudentMessage: end");
		}
	}
}
