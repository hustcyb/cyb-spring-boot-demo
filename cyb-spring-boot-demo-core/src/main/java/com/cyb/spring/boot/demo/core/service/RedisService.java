package com.cyb.spring.boot.demo.core.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.cyb.spring.boot.demo.domain.Student;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * Redis缓存服务
 * 
 * @author Administrator
 *
 */
@Service
public class RedisService {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RedisService.class);

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource(name = "stringRedisTemplate")
	private ValueOperations<String, String> valueOperations;

	@Resource
	private RedisTemplate<Integer, Student> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<Integer, Student> studentOperations;

	/**
	 * 获取缓存值
	 * 
	 * @param key
	 *            缓存键
	 * @return 缓存值
	 */
	public String getValue(String key) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.getValue: start, key = {}", key);
		}

		String value = valueOperations.get(key);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.getValue: end, return = {}", value);
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
	public void saveValue(String key, String value) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.setValue: start, key = {}, value = {}",
					key, value);
		}

		valueOperations.set(key, value);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.setValue: end");
		}
	}

	/**
	 * 删除缓存值
	 * 
	 * @param key
	 *            缓存键
	 */
	public void deleteValue(String key) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.deleteValue: start, key = {}", key);
		}

		stringRedisTemplate.delete(key);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.deleteValue: end");
		}
	}

	/**
	 * 获取学生
	 * 
	 * @param id
	 *            学生编号
	 * @return 学生
	 */
	public Student getStudent(Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.getStudent: start, id = {}", id);
		}

		Student student = studentOperations.get(id);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.getStudent: end, return = {}",
					JsonUtils.bean2Json(student));
		}

		return student;
	}

	/**
	 * 保存学生信息
	 * 
	 * @param student
	 */
	public void saveStudent(Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.saveStudent: start, student = {}",
					JsonUtils.bean2Json(student));
		}

		studentOperations.set(student.getId(), student);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.saveStudent: end");
		}
	}

	/**
	 * 删除学生信息
	 * 
	 * @param id
	 *            学生编号
	 */
	public void deleteStudent(Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.saveStudent: start, id = {}", id);
		}

		redisTemplate.delete(id);
		if (logger.isDebugEnabled()) {
			logger.debug("RedisService.saveStudent: end");
		}
	}
}
