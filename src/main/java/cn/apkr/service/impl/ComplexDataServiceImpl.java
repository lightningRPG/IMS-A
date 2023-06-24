package cn.apkr.service.impl;

import cn.apkr.config.MyBatisConfig;
import cn.apkr.domain.ComplexData;
import cn.apkr.domain.Course;
import cn.apkr.domain.Score;
import cn.apkr.mapper.ComplexDataMapper;
import cn.apkr.mapper.CourseMapper;
import cn.apkr.mapper.ScoreMapper;
import cn.apkr.service.ComplexDataService;

import java.util.Arrays;
import java.util.List;


public class ComplexDataServiceImpl implements ComplexDataService {

    private ComplexDataMapper complexDataMapper;
    public void setComplexDataMapper(ComplexDataMapper complexDataMapper) {
        this.complexDataMapper = complexDataMapper;
    }

    private ScoreMapper scoreMapper;
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    private CourseMapper courseMapper;
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public List<ComplexData> selectComplexData(ComplexData complexData) {
        return complexDataMapper.selectComplexData(complexData);
    }

    @Override
    public List<ComplexData> selectAll() {
        return complexDataMapper.selectAll();
    }

    /**
     * 成绩搜索服务
     * @param keywords 搜索关键字符串
     * @return 搜索结果列表
     */
    public List<ComplexData> search(String[] keywords) {
        // 去除多余空格 模糊查询格式化
        if (!Arrays.toString(keywords).equals("[]")) {
            for (int i = 0; i < keywords.length; i++) {
                keywords[i] = keywords[i].replace("\\s", "");
                if (i < 4) keywords[i] = "%" + keywords[i] + "%";
            }
        }

        System.out.println("keywords:" + Arrays.toString(keywords));

        // 整合对象获取查询结果
        ComplexData tempData = new ComplexData();
        switch (keywords.length) {
            case 5:
                tempData.setScore(keywords[4].isEmpty() ? null : Float.valueOf(keywords[4]));
            case 4:
                tempData.setCourseName(keywords[3].isEmpty() ? null : keywords[3]);
            case 3:
                tempData.setName(keywords[2].isEmpty() ? null : keywords[2]);
            case 2:
                tempData.setClassname(keywords[1].isEmpty() ? null : keywords[1]);
            case 1:
                tempData.setSid(keywords[0].isEmpty() ? null : keywords[0]);
            case 0:
                break;
        }

        return complexDataMapper.selectComplexData(tempData);
    }

    /**
     * 修改指定成绩
     * @param sidIn 学号
     * @param courseNameIn 课程名称
     * @param scoreIn 修改的分数
     * @return 修改后成绩列表
     */
    public Boolean modifyBySidCourseName(String sidIn, String courseNameIn, Float scoreIn) {
        // 获取科目对应cid
        Course course = new Course();
        course.setCourseName(courseNameIn);
        course = courseMapper.selectCourse(course).get(0);

        Score score = new Score();
        score.setSid(sidIn);
        score.setCid(course.getCid());
        score.setScore(scoreIn);
        if (scoreIn != null)
            return scoreMapper.updateScoreById(score);
        else return scoreMapper.deleteScore(score);
    }
}
