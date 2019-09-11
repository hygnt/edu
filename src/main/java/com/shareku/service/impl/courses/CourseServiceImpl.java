package com.shareku.service.impl.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shareku.entity.courses.Course;
import com.shareku.entity.courses.Video;
import com.shareku.mapper.courses.CourseMapper;
import com.shareku.service.courses.CourseService;

public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseMapper coursesMapper;
	
	@Override
	public List<Course> listAllCourse(Course Course) {
		// TODO Auto-generated method stub
		return coursesMapper.listAllCourse(Course);
	}

	@Override
	public List<Course> listPageCourse(Course courses) {
		// TODO Auto-generated method stub
		return coursesMapper.listPageCourse(courses);
	}

	@Override
	public Course getCourseById(Integer coursesId) {
		// TODO Auto-generated method stub
		return coursesMapper.getCourseById(coursesId);
	}

	@Override
	public void insertCourse(Course courses) {
		// TODO Auto-generated method stub
		coursesMapper.insertCourse(courses);
	}

	@Override
	public void updateCourse(Course courses) {
		// TODO Auto-generated method stub
		coursesMapper.updateCourse(courses);
	}

	@Override
	public void deleteCourse(Course c) {
		// TODO Auto-generated method stub
		coursesMapper.deleteCourse(c);
	}

	@Override
	public void addvideo(Video video) {
		// TODO Auto-generated method stub
		coursesMapper.addvideo(video);
	}
	
	
}
