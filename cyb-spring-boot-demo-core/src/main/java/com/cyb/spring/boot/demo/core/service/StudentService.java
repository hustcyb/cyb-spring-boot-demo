package com.cyb.spring.boot.demo.core.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.cyb.spring.boot.demo.core.persistence.StudentMapper;
import com.cyb.spring.boot.demo.domain.Student;
import com.cyb.spring.boot.demo.domain.query.StudentQuery;
import com.cyb.spring.boot.demo.domain.validation.Saving;
import com.cyb.spring.boot.demo.domain.validation.Updating;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 学生服务
 * 
 * @author Administrator
 *
 */
@Validated
@CacheConfig(cacheNames = "com.cyb.spring.boot.demo.core.service.StudentService")
@Service
public class StudentService {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(StudentService.class);

	/**
	 * 学生数据仓库
	 */
	@Resource
	private StudentMapper studentMapper;

	/**
	 * 根据查询条件获取学生列表
	 * 
	 * @param query
	 *            学生查询条件
	 * @return 学生列表
	 */
	@Cacheable(key = "'students'", condition = "#query?.name == null and #query?.minAge == null and #query?.maxAge == null")
	public List<Student> listByQuery(StudentQuery query) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.listByQuery: start, query = {}",
					JsonUtils.bean2Json(query));
		}

		if (query == null) {
			query = new StudentQuery();
		}

		List<Student> students = studentMapper.selectByQuery(query);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.listByQuery, return = {}",
					JsonUtils.bean2Json(students));
		}

		return students;
	}

	/**
	 * 根据编号获取学生
	 * 
	 * @param id
	 *            学生编号
	 * @return 学生
	 */
	@Cacheable(key = "'student#' + #id")
	public Student getById(
			@Min(value = 1, message = "{student.id.min}") Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.getById: start, id = {}", id);
		}

		Student student = studentMapper.selectById(id);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.getById: end, reutrn = {}",
					JsonUtils.bean2Json(student));
		}

		return student;
	}

	/**
	 * 保存学生信息
	 * 
	 * @param student
	 *            学生
	 * @return 学生编号
	 */
	@CacheEvict(key = "'students'")
	@Validated({ Default.class, Saving.class })
	public Integer save(@NotNull(message = "{student.null}") @Valid Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.save: start, students = {}",
					JsonUtils.bean2Json(student));
		}

		studentMapper.insertSelective(student);
		Integer id = student.getId();
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.save: end, return = {}", id);
		}

		return id;
	}

	/**
	 * 根据编号更新学生
	 * 
	 * @param student
	 *            学生
	 * @return 影响的记录数目
	 */
	@Caching(evict = { @CacheEvict(key = "'students'"),
			@CacheEvict(key = "'student#' + #student?.id") })
	@Validated({ Default.class, Updating.class })
	public Integer updateById(
			@NotNull(message = "{student.null}") @Valid Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.updateById: start, student = {}",
					JsonUtils.bean2Json(student));
		}

		int affectedRows = studentMapper.updateSelectiveById(student);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.updateById: end, return = {}",
					affectedRows);
		}

		return affectedRows;
	}

	/**
	 * 根据编号删除学生
	 * 
	 * @param id
	 *            学生编号
	 * @return 影响的记录数目
	 */
	@Caching(evict = { @CacheEvict(key = "'students'"),
			@CacheEvict(key = "'student#' + #id") })
	public Integer deleteById(Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.deleteById: start, id = {}", id);
		}

		int affectedRows = studentMapper.deleteById(id);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.deleteById: end, return = {}",
					affectedRows);
		}

		return affectedRows;
	}
}
