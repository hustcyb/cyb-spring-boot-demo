package com.cyb.spring.boot.demo.api.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.spring.boot.demo.api.domain.StudentDTO;
import com.cyb.spring.boot.demo.api.domain.StudentQueryDTO;
import com.cyb.spring.boot.demo.core.service.StudentService;
import com.cyb.spring.boot.demo.domain.Student;
import com.cyb.spring.boot.demo.domain.query.StudentQuery;
import com.google.common.collect.Lists;

import cyb.spring.boot.demo.common.json.JsonUtils;

/**
 * 学生控制器
 * 
 * @author Administrator
 *
 */
@RequestMapping("students")
@RestController
public class StudentController {

	/**
	 * 日志接口
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(StudentController.class);

	/**
	 * 学生服务
	 */
	@Resource
	private StudentService studentService;

	/**
	 * 学生列表
	 * 
	 * @return 学生列表
	 */
	@GetMapping
	public List<StudentDTO> list(StudentQueryDTO queryDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.list: start, queryDTO = {}", JsonUtils.bean2Json(queryDTO));
		}

		StudentQuery query = null;
		if (queryDTO != null) {
			query = new StudentQuery();
			BeanUtils.copyProperties(queryDTO, query);
		}

		List<Student> students = studentService.listByQuery(query);
		List<StudentDTO> studentDTOs = Lists.newArrayList();
		for (Student student : students) {
			StudentDTO studentDTO = new StudentDTO();
			BeanUtils.copyProperties(student, studentDTO);

			studentDTOs.add(studentDTO);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.list: end, return = {}",
					JsonUtils.bean2Json(studentDTOs));
		}

		return studentDTOs;
	}

	/**
	 * 学生信息
	 * 
	 * @param id
	 *            学生编号
	 * @return 学生
	 */
	@GetMapping("{id}")
	public StudentDTO get(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.get: start, id = {}", id);
		}
		StudentDTO studentDTO = null;

		Student student = studentService.getById(id);
		if (student != null) {
			studentDTO = new StudentDTO();
			BeanUtils.copyProperties(student, studentDTO);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.end, return = {}",
					JsonUtils.bean2Json(studentDTO));
		}

		return studentDTO;
	}

	/**
	 * 保存学生
	 * 
	 * @param studentDTO
	 *            学生
	 */
	@PostMapping
	public void save(@RequestBody StudentDTO studentDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.save: start, studentDTO = {}",
					JsonUtils.bean2Json(studentDTO));
		}

		Student student = null;
		if (studentDTO != null) {
			student = new Student();
			BeanUtils.copyProperties(studentDTO, student);
		}

		studentService.save(student);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.save: end");
		}
	}

	/**
	 * 更新学生
	 * 
	 * @param studentDTO
	 *            学生
	 */
	@PutMapping
	public void update(@RequestBody StudentDTO studentDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.update: start, studentDTO = {}",
					JsonUtils.bean2Json(studentDTO));
		}

		Student student = null;
		if (studentDTO != null) {
			student = new Student();
			BeanUtils.copyProperties(studentDTO, student);
		}

		studentService.updateById(student);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.update: end");
		}
	}

	/**
	 * 删除学生
	 * 
	 * @param id
	 *            学生编号
	 */
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.delete: start, id = {}", id);
		}

		studentService.deleteById(id);
		if (logger.isDebugEnabled()) {
			logger.debug("StudentController.delete: end");
		}
	}
}
