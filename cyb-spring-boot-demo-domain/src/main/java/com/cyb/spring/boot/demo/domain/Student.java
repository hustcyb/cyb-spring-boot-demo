package com.cyb.spring.boot.demo.domain;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.cyb.spring.boot.demo.domain.validation.Saving;
import com.cyb.spring.boot.demo.domain.validation.Updating;

/**
 * 学生
 * 
 * @author Administrator
 *
 */
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4805832758425535002L;

	/**
	 * 编号
	 */
	@Min(value = 1, message = "{student.id.min}", groups = Updating.class)
	@NotNull(message = "{student.id.not-null}", groups = Updating.class)
	private Integer id;

	/**
	 * 姓名
	 */
	@Size(min = 2, max = 20, message = "{student.name.size}", groups = { Saving.class, Updating.class })
	@NotNull(message = "{student.name.not-null}", groups = Saving.class)
	private String name;

	/**
	 * 年龄
	 */
	@Range(min = 6, max = 15, message = "{student.age.range}", groups = { Saving.class, Updating.class })
	@NotNull(message = "{student.age.not-null}", groups = Saving.class)
	private Byte age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getAge() {
		return age;
	}

	public void setAge(Byte age) {
		this.age = age;
	}
}
