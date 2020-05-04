package com.lisk.keyword.pojo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.List;

@TableName(value = "t_label")
public class Label{
    @TableId(type = IdType.AUTO)
    private int id;

    private String labelName;

    private int pdarticleId;

    private List<Essay> essayList;

    private double score ;

    public Label() {
    }

    public Label(int id, String labelName, int pdarticleId, List<Essay> essayList, double score) {
        this.id = id;
        this.labelName = labelName;
        this.pdarticleId = pdarticleId;
        this.essayList = essayList;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getPdarticleId() {
        return pdarticleId;
    }

    public void setPdarticleId(int pdarticleId) {
        this.pdarticleId = pdarticleId;
    }

    public List<Essay> getEssayList() {
        return essayList;
    }

    public void setEssayList(List<Essay> essayList) {
        this.essayList = essayList;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", labelName='" + labelName + '\'' +
                ", pdarticleId=" + pdarticleId +
                ", essayList=" + essayList +
                ", score=" + score +
                '}';
    }
}
