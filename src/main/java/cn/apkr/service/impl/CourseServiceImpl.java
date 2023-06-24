package cn.apkr.service.impl;

import cn.apkr.domain.Course;
import cn.apkr.mapper.CourseMapper;
import cn.apkr.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
	private CourseMapper courseMapper;
	public void setCourseMapper(CourseMapper courseMapper) {
		this.courseMapper = courseMapper;
	}

	@Override
	public List<Course> selectCourse(Course course) {
		return courseMapper.selectCourse(course);
	}

	@Override
	public List<Course> selectAll() {
		return courseMapper.selectAll();
	}

	@Override
	public Boolean insertCourse(Course course) {
		return courseMapper.insertCourse(course);
	}

	@Override
	public Boolean updateCourseById(Course course) {
		return courseMapper.updateCourseById(course);
	}

	@Override
	public Boolean deleteCourse(Course course) {
		return courseMapper.deleteCourse(course);
	}
}
