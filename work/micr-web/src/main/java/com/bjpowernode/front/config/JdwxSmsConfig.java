package com.bjpowernode.front.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Package:com.bjpowernode.front.config
 * Date:2022/3/9 11:14
 */
@Component
@ConfigurationProperties(prefix = "jdwx.sms")
public class JdwxSmsConfig {
    private String url;
    private String appkey;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}