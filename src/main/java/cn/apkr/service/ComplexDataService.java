package cn.apkr.service;

import cn.apkr.domain.ComplexData;

import java.util.List;


public interface ComplexDataService {

    List<ComplexData> selectComplexData(ComplexData complexData);

    List<ComplexData> selectAll();

    /**
     * 成绩搜索服务
     * @param keywords 搜索关键字符串
     * @return 搜索结果列表
     */
    public List<ComplexData> search(String[] keywords);

    /**
     * 修改指定成绩
     * @param sidIn 学号
     * @param courseNameIn 课程名称
     * @param scoreIn 修改的分数
     * @return 修改后成绩列表
     */
    public Boolean modifyBySidCourseName(String sidIn, String courseNameIn, Float scoreIn);
}
