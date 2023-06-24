package cn.apkr.config;

import cn.apkr.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisConfig {
//    public static String resource = "mybatisConf.xml";
//    public static SqlSessionFactory sqlSessionFactory;
//    public static SqlSession session;
//
//    public static UserMapper userMapper;
//    public static StudentMapper studentMapper;
//    public static CourseMapper courseMapper;
//    public static ScoreMapper scoreMapper;
//    public static ComplexDataMapper complexDataMapper;
//
//    static {
//        try {
//            // 初始化Mybatis
//            InputStream inputStream = Resources.getResourceAsStream(MyBatisConfig.resource);
//            MyBatisConfig.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//            MyBatisConfig.session = sqlSessionFactory.openSession(true);
//            complexDataMapper = session.getMapper(ComplexDataMapper.class);
//            studentMapper = session.getMapper(StudentMapper.class);
//            courseMapper = session.getMapper(CourseMapper.class);
//            scoreMapper = session.getMapper(ScoreMapper.class);
//            userMapper = session.getMapper(UserMapper.class);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
