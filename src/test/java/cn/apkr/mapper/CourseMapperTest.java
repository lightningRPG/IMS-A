//package cn.apkr.mapper;
//
//import cn.apkr.config.MyBatisConfig;
//import cn.apkr.config.SpringConfig;
//import cn.apkr.domain.Course;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.List;
//
//public class CourseMapperTest {
//
//    private final SqlSession sqlSession = (SqlSession) SpringConfig.ctx.getBean("sqlSession");
//    private final CourseMapper courseMapper = sqlSession.getMapper(CourseMapper.class);
//
//    @Test
//    public void testSelectCourse() {
//        Course courseNull = new Course();
//        Course course = new Course();
//        course.setCourseName("计算机应用与技术");              // 支持模糊查询
//        List<Course> coursesNull = courseMapper.selectCourse(courseNull);
//        List<Course> courses = courseMapper.selectCourse(course);
//        Assert.assertNotNull(coursesNull); // 查询全部
//        Assert.assertNotNull(courses);     // 查询指定
//        System.out.println("空Course查询：" + coursesNull);
//        System.out.println("指定名字查询：" + courses);
//    }
//
//    @Test
//    public void testSelectAll() {
//        List<Course> courses = courseMapper.selectAll();
//        Assert.assertNotNull(courses);
//        System.out.println("查询全部Course：" + courses);
//    }
//
//    @Test
//    public void testInsertCourse() {
//        Course course = new Course();
//        course.setCid("1111");
//        course.setCourseName("计算机网络与应用");
//        course.setTeacherName("张三丰");
//        Assert.assertTrue(courseMapper.insertCourse(course));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testUpdateCourse() {
//        Course course = new Course();
//        course.setCid("test123456");
//        course.setCourseName("无敌外语课");
//        Assert.assertTrue(courseMapper.updateCourseById(course));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteCourse() {
//        Course courseNull = new Course();
//        Course course = new Course();
//        course.setCourseName("英语");
//        Assert.assertTrue(courseMapper.deleteCourse(course));
//        Assert.assertTrue(courseMapper.deleteCourse(courseNull));
//        sqlSession.rollback();
//    }
//}
