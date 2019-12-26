package com.lisk.keyword.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class MyProps {
    public String url;
    public String getUrl() {
        return url;
    }
    public MyProps setUrl(String url) {
        this.url = url;
        return this;
    }
}