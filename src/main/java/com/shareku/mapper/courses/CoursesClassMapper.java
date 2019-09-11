package com.shareku.mapper.courses;

import java.util.List;

import com.shareku.entity.courses.CoursesClass;

public interface CoursesClassMapper  {
	List<CoursesClass> listAllCoursesClass(CoursesClass coursesClass);
	
	List<CoursesClass> listPageCoursesClass(CoursesClass coursesClass);
	
	CoursesClass getCoursesClassById(Integer ccId);
	
	void insertCoursesClass(CoursesClass coursesClass);
	
	void updateCoursesClass(CoursesClass coursesClass);
	
	void deleteCoursesClass(CoursesClass coursesClass);
}
