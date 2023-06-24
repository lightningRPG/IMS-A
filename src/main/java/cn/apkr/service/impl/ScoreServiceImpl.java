package cn.apkr.service.impl;

import cn.apkr.domain.Score;
import cn.apkr.mapper.ScoreMapper;
import cn.apkr.service.ScoreService;

import java.util.List;

public class ScoreServiceImpl implements ScoreService {
	private ScoreMapper scoreMapper;
	public void setScoreMapper(ScoreMapper scoreMapper) {
		this.scoreMapper = scoreMapper;
	}

	@Override
	public List<Score> selectScore(Score score) {
		return scoreMapper.selectScore(score);
	}

	@Override
	public List<Score> selectAll() {
		return scoreMapper.selectAll();
	}

	@Override
	public Boolean insertScore(Score score) {
		return scoreMapper.insertScore(score);
	}

	@Override
	public Boolean updateScoreById(Score score) {
		return scoreMapper.updateScoreById(score);
	}

	@Override
	public Boolean deleteScore(Score score) {
		return scoreMapper.deleteScore(score);
	}
}
