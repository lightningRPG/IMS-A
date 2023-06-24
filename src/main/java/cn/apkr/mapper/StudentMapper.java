package cn.apkr.mapper;

import cn.apkr.domain.Student;

import java.util.List;

public interface StudentMapper {

    // 查询
    List<Student> selectStudent(Student student);
    List<Student> selectAll();

    // 添加
    Boolean insertStudent(Student student);

    // 修改
    Boolean updateStudentById(Student student);

    // 删除
    Boolean deleteStudent(Student student);
}
