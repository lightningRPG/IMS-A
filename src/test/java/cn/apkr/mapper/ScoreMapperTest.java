//package cn.apkr.mapper;
//
//import cn.apkr.config.MyBatisConfig;
//import cn.apkr.config.SpringConfig;
//import cn.apkr.domain.Course;
//import cn.apkr.domain.Score;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.List;
//
//public class ScoreMapperTest {
//    private final SqlSession sqlSession = (SqlSession) SpringConfig.ctx.getBean("sqlSession");
//    private final ScoreMapper scoreMapper = (ScoreMapper) SpringConfig.ctx.getBean("scoreMapper");
//
//    @Test
//    public void testSelectScore() {
//        Score scoreNull = new Score();
//        Score score = new Score();
//        score.setSid("test123456");
//        score.setCid("test123450");
//        List<Score> scoresNull = scoreMapper.selectScore(scoreNull);
//        List<Score> scores = scoreMapper.selectScore(score);
//        Assert.assertNotNull(scoresNull); // 查询全部
//        Assert.assertNotNull(scores);     // 查询指定
//        System.out.println("空Score查询：" + scoresNull);
//        System.out.println("指定学生成绩查询：" + scores);
//    }
//
//    @Test
//    public void testSelectAll() {
//        List<Score> scores = scoreMapper.selectAll();
//        Assert.assertNotNull(scores);
//        System.out.println("查询全部Score：" + scores);
//    }
//
//    @Test
//    public void testInsertScore() {
//        Score score = new Score();
//        score.setCid("test123456");
//        score.setSid("test123450");
//        score.setScore(55.55f);
//        Assert.assertTrue(scoreMapper.insertScore(score));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testUpdateScore() {
//        Score score = new Score();
//        score.setCid("test123450");
//        score.setSid("test123450");
//        score.setScore(120.0f);
//        Assert.assertTrue(scoreMapper.updateScoreById(score));
//        sqlSession.rollback();
//    }
//
//    @Test
//    public void testDeleteCourse() {
//        Score scoreNull = new Score();
//        Score score = new Score();
//        score.setSid("test123456");
//        score.setCid("test123450");
//        Assert.assertTrue(scoreMapper.deleteScore(score));
//        Assert.assertTrue(scoreMapper.deleteScore(scoreNull));
//        sqlSession.rollback();
//    }
//}
