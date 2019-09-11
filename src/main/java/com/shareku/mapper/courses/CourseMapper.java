package com.shareku.mapper.courses;

import java.util.List;

import com.shareku.entity.courses.Course;
import com.shareku.entity.courses.Video;

public interface CourseMapper  {
	List<Course> listAllCourse(Course Course);
	
	List<Course> listPageCourse(Course Course);
	
	Course getCourseById(Integer coursesId);
	
	void insertCourse(Course Course);
	
	void updateCourse(Course Course);
	
	void deleteCourse(Course c);
	
	void addvideo(Video video);
}
