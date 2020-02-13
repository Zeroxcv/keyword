package com.lisk.keyword.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
@TableName(value = "t_pdarticle")
public class Essay {
    //文章id
    @TableId(type = IdType.AUTO)
    int id;
    //文章标签
    String textLabel;
    //文章标题
    String title;
    //文章内容
    String textContent;
    //文章发布日期
    Date publishedData;
    //文章来源
    String dataSources;

    public Essay() {
    }

    public Essay(int id, String textLabel, String title, String textContent, Date publishedData, String dataSources) {
        //文章id
        this.id = id;
        //文章标题
        this.textLabel = textLabel;
        //文章标题
        this.title = title;
        this.textContent = textContent;
        this.publishedData = publishedData;
        this.dataSources = dataSources;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextLabel() {
        return textLabel;
    }

    public void setTextLabel(String textLabel) {
        this.textLabel = textLabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Date getPublishedData() {
        return publishedData;
    }

    public void setPublishedData(Date publishedData) {
        this.publishedData = publishedData;
    }

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "id=" + id +
                ", textLabel='" + textLabel + '\'' +
                ", title='" + title + '\'' +
                ", textContent='" + textContent + '\'' +
                ", publishedData=" + publishedData +
                ", dataSources='" + dataSources + '\'' +
                '}';
    }
}
