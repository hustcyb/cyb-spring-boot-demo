package com.cyb.spring.boot.demo.api.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.spring.boot.demo.core.service.RedisService;
import com.cyb.spring.boot.demo.domain.Student;

import cyb.spring.boot.demo.common.json.JsonUtils;

@RequestMapping("redis")
@RestController
public class RedisController {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RedisController.class);

	/**
	 * Redis缓存服务
	 */
	@Resource
	private RedisService redisService;

	/**
	 * 获取缓存值
	 * 
	 * @param key
	 *            缓存键
	 * @return 缓存值
	 */
	@GetMapping("values/{key}")
	public String getValue(@PathVariable String key) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.getValue: start, key = {}", key);
		}

		String value = redisService.getValue(key);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.getValue: end, return = {}", value);
		}

		return value;
	}

	/**
	 * 设置缓存值
	 * 
	 * @param key
	 *            缓存键
	 * @param value
	 *            缓存值
	 */
	@PostMapping("values/{key}")
	public void saveValue(@PathVariable String key, @RequestBody String value) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"RedisController.setValue: start, key = {}, value = {}",
					key, value);
		}

		redisService.saveValue(key, value);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.setValue: end");
		}
	}

	/**
	 * 删除缓存值
	 * 
	 * @param key
	 *            缓存值
	 */
	@DeleteMapping("values/{key}")
	public void deleteValue(@PathVariable String key) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.deleteValue: start, key = {}", key);
		}

		redisService.deleteValue(key);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.deleteValue: end");
		}
	}

	/**
	 * 获取学生
	 * 
	 * @param id
	 *            学生编号
	 * @return 学生
	 */
	@GetMapping("students/{id}")
	public Student getStudent(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.getStudent: start, id = {}", id);
		}

		Student student = redisService.getStudent(id);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.getStudent: end, return = {}",
					JsonUtils.bean2Json(student));
		}

		return student;
	}

	/**
	 * 保存学生
	 * 
	 * @param student
	 *            学生
	 */
	@PostMapping("students")
	public void saveStudent(@RequestBody Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.saveStudent: start, student = {}",
					JsonUtils.bean2Json(student));
		}

		redisService.saveStudent(student);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.saveStudent: end");
		}
	}

	/**
	 * 删除学生
	 * 
	 * @param id
	 *            学生编号
	 */
	@DeleteMapping("students/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.deleteStudent: start, id = {}", id);
		}

		redisService.deleteStudent(id);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisController.deleteStudent: end");
		}
	}
}
