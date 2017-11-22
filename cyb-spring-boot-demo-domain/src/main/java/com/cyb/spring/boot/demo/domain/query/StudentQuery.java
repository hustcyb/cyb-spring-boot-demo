package com.cyb.spring.boot.demo.domain.query;

/**
 * 学生查询条件
 * 
 * @author Administrator
 *
 */
public class StudentQuery {
	
	/**
	 * 姓名，模糊匹配
	 */
	private String name;
	
	/**
	 * 年龄下限，为null表示不限
	 */
	private Byte minAge;
	
	/**
	 * 年龄上限，为null表示不限
	 */
	private Byte maxAge;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getMinAge() {
		return minAge;
	}

	public void setMinAge(Byte minAge) {
		this.minAge = minAge;
	}

	public Byte getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Byte maxAge) {
		this.maxAge = maxAge;
	}
}
