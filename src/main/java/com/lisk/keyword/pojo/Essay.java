package com.lisk.keyword.pojo;

import java.util.Date;

public class Essay {
    int id;
    String textLabel;
    String title;
    String textContent;
    Date publishedData;
    String dataSources;

    public Essay() {
    }

    public Essay(int id, String textLabel, String title, String textContent, Date publishedData, String dataSources) {
        this.id = id;
        this.textLabel = textLabel;
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
