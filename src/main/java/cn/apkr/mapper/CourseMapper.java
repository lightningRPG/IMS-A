package cn.apkr.mapper;

import cn.apkr.domain.Course;

import java.util.List;

public interface CourseMapper {
    // 查询
    List<Course> selectCourse(Course course);
    List<Course> selectAll();

    // 添加
    Boolean insertCourse(Course course);

    // 修改
    Boolean updateCourseById(Course course);

    // 删除
    Boolean deleteCourse(Course course);
}
