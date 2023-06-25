package cn.apkr.mapper;

import cn.apkr.config.SpringConfig;
import cn.apkr.domain.Score;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class ScoreMapperTest {
	private final SqlSessionFactory sessionFactory = (SqlSessionFactory) SpringConfig.ctx.getBean("sessionFactory");
    private final SqlSession sqlSession = sessionFactory.openSession();
    private final ScoreMapper scoreMapper = sqlSession.getMapper(ScoreMapper.class);

    @Test
    public void testSelectScore() {
        Score scoreNull = new Score();
        Score score = new Score();
        score.setSid("test123456");
        score.setCid("test123450");
        List<Score> scoresNull = scoreMapper.selectScore(scoreNull);
        List<Score> scores = scoreMapper.selectScore(score);
        Assert.assertNotNull(scoresNull); // 查询全部
        Assert.assertNotNull(scores);     // 查询指定
        System.out.println("空Score查询：" + scoresNull);
        System.out.println("指定学生成绩查询：" + scores);
    }

    @Test
    public void testSelectAll() {
        List<Score> scores = scoreMapper.selectAll();
        Assert.assertNotNull(scores);
        System.out.println("查询全部Score：" + scores);
    }

    @Test
    public void testIUDScore() {
        Score score = new Score();
        // 插入
        score.setCid("1");
        score.setSid("test123450");
        score.setScore(55.55f);
        Assert.assertTrue(scoreMapper.insertScore(score));
        // 修改
        score.setCid("1");
        score.setSid("test123450");
        score.setScore(120.0f);
        Assert.assertTrue(scoreMapper.updateScoreById(score));
        // 删除
//        Score scoreNull = new Score();
        score.setCid("1");
        score.setSid("test123450");
        Assert.assertTrue(scoreMapper.deleteScore(score));
//        Assert.assertTrue(scoreMapper.deleteScore(scoreNull));	// 清空数据库
    }
}
