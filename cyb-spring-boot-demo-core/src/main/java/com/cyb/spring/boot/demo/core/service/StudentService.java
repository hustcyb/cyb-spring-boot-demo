package com.cyb.spring.boot.demo.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.spring.boot.demo.core.constants.StudentConstant;
import com.cyb.spring.boot.demo.core.persistence.StudentMapper;
import com.cyb.spring.boot.demo.domain.Student;
import com.cyb.spring.boot.demo.domain.query.StudentQuery;
import com.google.common.base.Preconditions;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 学生服务
 * 
 * @author Administrator
 *
 */
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
	public Student getById(Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.getById: start, id = {}", id);
		}

		Student student = null;
		if (id != null && id >= StudentConstant.MIN_ID) {
			student = studentMapper.selectById(id);
		}

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
	public Integer save(Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.save: start, students = {}",
					JsonUtils.bean2Json(student));
		}

		checkStudent(student);
		String name = student.getName();
		Preconditions.checkArgument(StringUtils.isNoneBlank(name), "请指定学生姓名");
		byte age = student.getAge();
		Preconditions.checkNotNull(age, "请指定学生年龄");

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
	public Integer updateById(Student student) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.updateById: start, student = {}",
					JsonUtils.bean2Json(student));
		}

		checkStudent(student);
		Integer id = student.getId();
		Preconditions.checkArgument(id != null, "请指定学生编号");

		int minId = StudentConstant.MIN_ID;
		String message = String.format("学生编号须大于等于%d", minId);
		Preconditions.checkArgument(id >= minId, message);
		Preconditions.checkArgument(
				student.getName() != null || student.getAge() != null,
				"请指定需要更新的学生数据");

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
	public Integer deleteById(Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.deleteById: start, id = {}", id);
		}

		Preconditions.checkNotNull(id != null, "请指定学生编号");
		int minId = StudentConstant.MIN_ID;
		String message = String.format("学生编号须大于等于%d", minId);
		Preconditions.checkNotNull(id >= StudentConstant.MIN_ID, message);

		int affectedRows = studentMapper.deleteById(id);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentService.deleteById: end, return = {}",
					affectedRows);
		}

		return affectedRows;
	}

	/**
	 * 验证学生数据
	 * 
	 * @param student
	 */
	private void checkStudent(Student student) {
		Preconditions.checkNotNull(student, "请指定学生数据");

		String name = student.getName();
		if (StringUtils.isNotBlank(name)) {
			int minNameLength = StudentConstant.MIN_NAME_LENGTH;
			int maxNameLength = StudentConstant.MAX_NAME_LENGTH;
			String message = String.format("学生姓名须大于等于%d个字符，小于等于%d个字符",
					minNameLength, maxNameLength);
			int codePointCount = name.codePointCount(0, name.length());
			Preconditions.checkArgument(codePointCount >= minNameLength
					&& codePointCount <= maxNameLength, message);
		}

		Byte age = student.getAge();
		if (age != null) {
			int minAge = StudentConstant.MIN_AGE;
			int maxAge = StudentConstant.MAX_AGE;
			String message = String.format("学生年龄须大于等于%d岁，小于等于%d岁", minAge,
					maxAge);
			Preconditions
					.checkArgument(age >= minAge && age <= maxAge, message);
		}
	}
}
