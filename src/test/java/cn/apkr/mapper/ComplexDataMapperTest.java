//package cn.apkr.mapper;
//
//import cn.apkr.config.MyBatisConfig;
//import cn.apkr.config.SpringConfig;
//import cn.apkr.domain.ComplexData;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.List;
//
//public class ComplexDataMapperTest {
//
//    private final SqlSession sqlSession = (SqlSession) SpringConfig.ctx.getBean("sqlSession");
//    private final ComplexDataMapper complexDataMapper = sqlSession.getMapper(ComplexDataMapper.class);
//
//    @Test
//    public void testSelectComplexData() {
//        ComplexData complexData = new ComplexData();
////        complexData.setName("张三");
//        complexData.setClassname("%%");    // 支持模糊查询
//        List<ComplexData> complexDataList = complexDataMapper.selectComplexData(complexData);
//        Assert.assertNotNull(complexDataList);
//        System.out.println("查询指定学生成绩：" + complexDataList);
//    }
//
//    @Test
//    public void testSelectAll() {
//        List<ComplexData> complexDataList = complexDataMapper.selectAll();
//        Assert.assertNotNull(complexDataList);
//        System.out.println("查询所有学生成绩" + complexDataList);
//    }
//}
