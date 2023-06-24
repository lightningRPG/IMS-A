package cn.apkr.mapper;

import cn.apkr.domain.Score;

import java.util.List;

public interface ScoreMapper {
    // 查询
    List<Score> selectScore(Score score);
    List<Score> selectAll();

    // 添加
    Boolean insertScore(Score score);

    // 修改
    Boolean updateScoreById(Score score);

    // 删除
    Boolean deleteScore(Score score);
}
