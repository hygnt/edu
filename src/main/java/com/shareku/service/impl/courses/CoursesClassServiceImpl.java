package com.shareku.service.impl.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shareku.entity.courses.CoursesClass;
import com.shareku.mapper.courses.CoursesClassMapper;
import com.shareku.service.courses.CoursesClassService;

public class CoursesClassServiceImpl implements CoursesClassService {

	@Autowired
	CoursesClassMapper coursesClassMapper;
	
	@Override
	public List<CoursesClass> listAllCoursesClass(CoursesClass coursesClass) {
		// TODO Auto-generated method stub
		return coursesClassMapper.listAllCoursesClass(coursesClass);
	}

	@Override
	public List<CoursesClass> listPageCoursesClass(CoursesClass coursesClass) {
		// TODO Auto-generated method stub
		return coursesClassMapper.listPageCoursesClass(coursesClass);
	}

	@Override
	public CoursesClass getCoursesClassById(Integer ccId) {
		// TODO Auto-generated method stub
		return coursesClassMapper.getCoursesClassById(ccId);
	}

	@Override
	public void insertCoursesClass(CoursesClass coursesClass) {
		// TODO Auto-generated method stub
		coursesClassMapper.insertCoursesClass(coursesClass);
	}

	@Override
	public void updateCoursesClass(CoursesClass coursesClass) {
		// TODO Auto-generated method stub
		coursesClassMapper.updateCoursesClass(coursesClass);
	}

	@Override
	public void deleteCoursesClass(CoursesClass coursesClass) {
		// TODO Auto-generated method stub
		coursesClassMapper.deleteCoursesClass(coursesClass);
	}
	
	
}
