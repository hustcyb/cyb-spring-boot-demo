package com.cyb.spring.boot.demo.core.service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cyb.spring.boot.demo.domain.Student;
import com.cyb.spring.boot.demo.server.Application;

/**
 * 学生服务单元测试
 * 
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentServiceTest {

	/**
	 * 学生服务
	 */
	@Resource
	private StudentService studentService;

	/**
	 * 测试保存学生时，当学生为null，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfStudentIsNull() {
		Student student = null;
		studentService.save(student);
	}

	/**
	 * 测试保存学生时，当姓名为空时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfNameIsNull() {
		Student student = new Student();
		student.setAge((byte) 10);
		studentService.save(student);
	}

	/**
	 * 测试保存学生时，当姓名少于2个字符时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfNameSizeLessThanTwo() {
		Student student = new Student();
		student.setName("J");
		student.setAge((byte) 10);

		studentService.save(student);
	}

	/**
	 * 测试保存学生时，当姓名多于20个字符时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfNameSizeMoreThanTwenty() {
		Student student = new Student();
		student.setName("abcdefghijklmnopqrstu");
		student.setAge((byte) 10);

		studentService.save(student);
	}

	/**
	 * 测试保存学生时，当年龄为null，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfAgeIsNull() {
		Student student = new Student();
		student.setName("Jim");

		studentService.save(student);
	}

	/**
	 * 测试保存学生时，当年龄小于6岁时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfAgeIsLessThanSix() {
		Student student = new Student();
		student.setName("Jim");
		student.setAge((byte) 5);

		studentService.save(student);
	}

	/**
	 * 测试保存学生时，当年龄大于15岁时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testSaveThrowsExceptionIfAgeGreaterThanSix() {
		Student student = new Student();
		student.setName("Jim");
		student.setAge((byte) 16);

		studentService.save(student);
	}

	/**
	 * 测试保存学生,当学生编号为空时，保存成功的场景
	 */
	@Test
	public void testSaveSuccessIfIdIsNull() {
		Student student = new Student();
		student.setName("Jim");
		student.setAge((byte) 10);

		studentService.save(student);
	}

	/**
	 * 测试保存学生，当学生编号小于1时，保存成功的场景
	 */
	@Test
	public void testSaveSuccessIfIdLessThanOne() {
		Student student = new Student();
		student.setId(0);
		student.setName("Jim");
		student.setAge((byte) 10);

		studentService.save(student);

	}

	/**
	 * 测试更新学生时，当学生为null，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfStudentIsNull() {
		Student student = null;
		studentService.updateById(student);
	}

	/**
	 * 测试更新学生时，当编号为空时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfIdIsNull() {
		Student student = new Student();
		student.setName("Jim");
		student.setAge((byte) 10);
		studentService.updateById(student);
	}

	/**
	 * 测试更新学生时，当编号小于1时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfIdLessThanOne() {
		Student student = new Student();
		student.setId(0);
		student.setName("Jim");
		student.setAge((byte) 10);

		studentService.updateById(student);
	}

	/**
	 * 测试更新学生时，当姓名少于2个字符时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfNameSizeLessThanTwo() {
		Student student = new Student();
		student.setId(1);
		student.setName("J");
		student.setAge((byte) 10);

		studentService.save(student);
	}

	/**
	 * 测试更新学生时，当姓名多于20个字符时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfNameSizeMoreThanTwenty() {
		Student student = new Student();
		student.setId(1);
		student.setName("abcdefghijklmnopqrstu");
		student.setAge((byte) 10);

		studentService.save(student);
	}

	/**
	 * 测试更新学生时，当年龄小于6岁时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfAgeIsLessThanSix() {
		Student student = new Student();
		student.setId(1);
		student.setName("Jim");
		student.setAge((byte) 5);

		studentService.save(student);
	}

	/**
	 * 测试更新学生时，当年龄大于15岁时，则抛出异常的场景
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testUpdateByIdThrowsExceptionIfAgeGreaterThanSix() {
		Student student = new Student();
		student.setId(1);
		student.setName("Jim");
		student.setAge((byte) 16);

		studentService.save(student);
	}

	/**
	 * 测试更新学生，当姓名为空，更新成功的场景
	 */
	@Test
	public void testUpdateByIdSuccessIfNameIsNull() {
		Student student = new Student();
		student.setId(1);
		student.setAge((byte) 10);

		studentService.updateById(student);
	}

	/**
	 * 测试更新学生，当年龄为空时，更新成功的场景
	 */
	@Test
	public void testUpdateByIdSuccessIfAgeIsNull() {
		Student student = new Student();
		student.setId(1);
		student.setName("Jim");

		studentService.updateById(student);
	}

	/**
	 * 测试更新学生，当姓名和年龄不能为空时，更新成功的场景
	 */
	@Test
	public void testUpdateByIdSuccessIfNameAndAgeIsNotNull() {
		Student student = new Student();
		student.setId(1);
		student.setName("Jim");
		student.setAge((byte) 10);

		studentService.updateById(student);

	}
}
