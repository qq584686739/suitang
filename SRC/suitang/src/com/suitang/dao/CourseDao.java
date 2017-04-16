package com.suitang.dao;

import com.suitang.domain.Course;
/**
 * 课程表Dao
 * @author Xjh
 *
 */
public interface CourseDao extends BaseDao<Course>{

	//用来写除了增删改查的其他方法
	/**
	 * 
	 * getCourseByPrimarykeys
	 * @Description:根据四个主键得到Course
	 * @Author:肖家豪(作者)
	 * @Version:v1.00(版本号)
	 * @Create:Date:2017年4月16日 下午8:27:12
	 * @param cid		课程id
	 * @param cd_id		教室id
	 * @param c_year	学年名
	 * @param c_term	学期名
	 * @Return:Course
	 */
	public Course getCourseByPrimarykeys(
			String cid,
			String cd_id,
			int c_year,
			int c_term);
	
}
