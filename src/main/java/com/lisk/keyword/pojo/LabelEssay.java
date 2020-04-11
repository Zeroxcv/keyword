package com.lisk.keyword.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName(value = "t_pdarticle_label")
public class LabelEssay {
    @TableId(type = IdType.AUTO)
    int id;
    int labelId;
    int pdarticleId;

    public LabelEssay() {
    }

    public LabelEssay(int id, int labelId, int pdarticleId) {
        this.id = id;
        this.labelId = labelId;
        this.pdarticleId = pdarticleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public int getPdarticleId() {
        return pdarticleId;
    }

    public void setPdarticleId(int pdarticleId) {
        this.pdarticleId = pdarticleId;
    }
}
