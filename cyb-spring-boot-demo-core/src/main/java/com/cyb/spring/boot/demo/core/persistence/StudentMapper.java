package com.cyb.spring.boot.demo.core.persistence;

import java.util.List;

import com.cyb.spring.boot.demo.domain.Student;
import com.cyb.spring.boot.demo.domain.query.StudentQuery;

/**
 * 学生数据仓库
 * 
 * @author Administrator
 *
 */
public interface StudentMapper {

	/**
	 * 根据查询条件查询学生列表
	 * 
	 * @param query
	 *            查询条件
	 * @return 学生列表
	 */
	List<Student> selectByQuery(StudentQuery query);

	/**
	 * 根据编号查询学生数据
	 * 
	 * @param id
	 *            学生编号
	 * @return 学生
	 */
	Student selectById(int id);

	/**
	 * 选择性插入学生数据
	 * 
	 * @param student
	 *            学生
	 * @return
	 */
	int insertSelective(Student student);

	/**
	 * 选择性更新学生数据
	 * 
	 * @param student
	 *            学生
	 * @return 影响的记录数目
	 */
	int updateSelectiveById(Student student);

	/**
	 * 根据编号删除学生
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(int id);
}
