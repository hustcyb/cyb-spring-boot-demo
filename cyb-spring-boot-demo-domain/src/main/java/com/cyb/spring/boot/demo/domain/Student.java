package com.cyb.spring.boot.demo.domain;

import java.io.Serializable;

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
	private Integer id;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 年龄
	 */
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
