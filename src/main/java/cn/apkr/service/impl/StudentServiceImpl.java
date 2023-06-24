package cn.apkr.service.impl;

import cn.apkr.domain.Student;
import cn.apkr.mapper.StudentMapper;
import cn.apkr.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

	private StudentMapper studentMapper;
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	@Override
	public List<Student> selectStudent(Student student) {
		return studentMapper.selectStudent(student);
	}

	@Override
	public List<Student> selectAll() {
		return studentMapper.selectAll();
	}

	@Override
	public Boolean insertStudent(Student student) {
		return studentMapper.insertStudent(student);
	}

	@Override
	public Boolean updateStudentById(Student student) {
		return studentMapper.updateStudentById(student);
	}

	@Override
	public Boolean deleteStudent(Student student) {
		return studentMapper.deleteStudent(student);
	}
}
