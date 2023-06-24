//package cn.apkr.mapper;
//
//import cn.apkr.config.MyBatisConfig;
//import cn.apkr.config.SpringConfig;
//import cn.apkr.domain.Student;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.List;
//
//public class StudentMapperTest {
//
//    private final SqlSession sqlSession = (SqlSession) SpringConfig.ctx.getBean("sqlSession");
//    private final StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
//
//    @Test
//    public void testSelectStudent() {
//        Student studentNull = new Student();
//        Student student = new Student();
//        student.setName("__");              // 支持模糊查询
//        List<Student> studentsNull = studentMapper.selectStudent(studentNull);
//        List<Student> students = studentMapper.selectStudent(student);
//        Assert.assertNotNull(studentsNull); // 查询全部
//        Assert.assertNotNull(students);     // 查询指定
//        System.out.println("空Student查询：" + studentsNull);
//        System.out.println("指定名字查询：" + students);
//    }
//
//    @Test
//    public void testSelectAll() {
//        List<Student> students = studentMapper.selectAll();
//        Assert.assertNotNull(students);
//        System.out.println("查询全部Student：" + students);
//    }
//
//    @Test
//    public void testInsertStudent() {
//        Student student = new Student();
//        student.setSid("1111");
//        student.setName("张四");
//        student.setClassname("机械2班");
//        student.setSex("女");
//        Assert.assertTrue(studentMapper.insertStudent(student));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testUpdateStudent() {
//        Student student = new Student();
//        student.setSid("test123456");
//        student.setName("阿坝阿坝");
//        Assert.assertTrue(studentMapper.updateStudentById(student));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteStudent() {
//        Student studentNull = new Student();
//        Student student = new Student();
//        student.setName("张三");
//        Assert.assertTrue(studentMapper.deleteStudent(student));
//        Assert.assertTrue(studentMapper.deleteStudent(studentNull));
//        sqlSession.rollback();
//    }
//}
