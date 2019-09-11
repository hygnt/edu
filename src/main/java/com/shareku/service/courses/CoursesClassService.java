package com.shareku.service.courses;

import java.util.List;

import com.shareku.entity.courses.CoursesClass;

public interface CoursesClassService {
	List<CoursesClass> listAllCoursesClass(CoursesClass coursesClass);
	
	public List<CoursesClass> listPageCoursesClass(CoursesClass coursesClass);
	
	public CoursesClass getCoursesClassById(Integer ccId);
	
	public void insertCoursesClass(CoursesClass coursesClass);
	
	public void updateCoursesClass(CoursesClass coursesClass);
	
	public void deleteCoursesClass(CoursesClass coursesClass);
}
