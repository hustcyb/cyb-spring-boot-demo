package com.cyb.spring.boot.demo.api.domain;

/**
 * 学生
 * 
 * @author Administrator
 *
 */
public class StudentDTO {

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
