package com.example.mynews;

import org.litepal.crud.LitePalSupport;

// 新闻收藏列表
public class NewsCollectBean extends LitePalSupport {
    // 用户账号
    private String userIdNumer;
    // 新闻id
    private String newsId;
    // 新闻标题
    private String newsTitle;
    // 新闻链接
    private String newsUrl;

    public String getUserIdNumer() {
        return userIdNumer;
    }

    public void setUserIdNumer(String userIdNumer) {
        this.userIdNumer = userIdNumer;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }


    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    @Override
    public String toString() {
        return "NewsCollectBean{" +
                "userIdNumer='" + userIdNumer + '\'' +
                ", newsId='" + newsId + '\'' +
                ", newSTitle='" + newsTitle + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                '}';
    }
}


