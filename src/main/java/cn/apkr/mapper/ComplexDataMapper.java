package cn.apkr.mapper;

import cn.apkr.domain.ComplexData;

import java.util.List;

public interface ComplexDataMapper {
     List<ComplexData> selectComplexData(ComplexData complexData);

     List<ComplexData> selectAll();
}
