package com.lisk.keyword.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lisk.keyword.mapper.EssayMapper;
import com.lisk.keyword.mapper.LabelMapper;
import com.lisk.keyword.pojo.Essay;
import com.lisk.keyword.pojo.Label;
import com.lisk.keyword.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {
    @Autowired
    LabelMapper labelMapper;


    @Override
    public Boolean insertPdarticleLabel(int labelId, int EssayId,double score) {
        return labelMapper.insertPdarticleLabel(labelId, EssayId,score);
    }
}
