package com.lisk.keyword.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lisk.keyword.pojo.Label;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabelMapper extends BaseMapper<Label> {
    Boolean insertPdarticleLabel(int labelId, int essayId,double score);
}
