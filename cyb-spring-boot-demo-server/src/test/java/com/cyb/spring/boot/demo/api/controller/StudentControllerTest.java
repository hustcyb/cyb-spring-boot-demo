package com.cyb.spring.boot.demo.api.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyb.spring.boot.demo.api.domain.StudentDTO;
import com.cyb.spring.boot.demo.api.domain.StudentQueryDTO;
import com.cyb.spring.boot.demo.server.Application;

/**
 * 学生控制器单元测试用例
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentControllerTest {

	/**
	 * 学生控制器
	 */
	@Resource
	private StudentController studentController;
	
	/**
	 * 测试当不存在任务学生记录时，获取学生列表返回空列表场景
	 */
	@Test
	public void testListReturnsEmptyIfNotExists() {
		StudentQueryDTO query = new StudentQueryDTO();
		List<StudentDTO> students = studentController.list(query);
		
		assertNotNull(students);
		assertTrue(students.size() == 0);
	}
	
	/**
	 * 测试当保存学生之后，获取学生列表返回非空列表的场景
	 */
	@Test
	public void testListReturnsNotEmptyAfterSave() {
		StudentDTO student = new StudentDTO();
		student.setName("Jim");
		student.setAge((byte)10);
		studentController.save(student);
		
		StudentQueryDTO query = new StudentQueryDTO();
		List<StudentDTO> students = studentController.list(query);
		assertNotNull(students);
		assertTrue(students.size() > 0);
	}
}
