package com.lisk.keyword.service;

import com.baomidou.mybatisplus.service.IService;
import com.lisk.keyword.pojo.Label;


public interface LabelService extends IService<Label> {

    Boolean insertPdarticleLabel(int labelId, int EssayId,double score);
}
