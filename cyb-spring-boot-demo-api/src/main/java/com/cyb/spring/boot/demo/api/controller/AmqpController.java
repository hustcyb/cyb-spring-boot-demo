package com.cyb.spring.boot.demo.api.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.spring.boot.demo.core.service.AmqpService;
import com.cyb.spring.boot.demo.domain.Student;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 消息控制器
 * 
 * @author Administrator
 *
 */
@RequestMapping("amqp")
@RestController
public class AmqpController {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(AmqpController.class);

	/**
	 * 消息服务
	 */
	@Resource
	private AmqpService amqpService;

	/**
	 * 发送字符串消息
	 * 
	 * @param value
	 *            字符串消息
	 */
	@PostMapping("values")
	public void sendValue(@RequestBody String value) {
		if (logger.isDebugEnabled()) {
			logger.debug("AmqpController.sendStringMessage: start, message = {}");
		}

		amqpService.sendValue(value);
		if (logger.isDebugEnabled()) {
			logger.debug("AmqpController.sendStringMessage: end");
		}
	}

	/**
	 * 发送学生信息
	 * 
	 * @param student
	 *            学生
	 */
	@PostMapping("students")
	public void sendStudentMessage(@RequestBody Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("AmqpController.sendStudent: start, student = {}",
					JsonUtils.bean2Json(student));
		}

		amqpService.sendStudent(student);
		if (logger.isDebugEnabled()) {
			logger.debug("AmqpController.sendStudentMessage: end");
		}
	}
}
