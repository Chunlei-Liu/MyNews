package com.example.mynews;

import org.litepal.crud.LitePalSupport;

public class NewsInfoBean extends LitePalSupport {
    private final String uniquekey;
    private final String title;
    private final String date;
    private final String category;
    private final String author_name;
    private final String url;
    private final String thumbnail_pic_s;
    private final String thumbnail_pic_s02;
    private final String thumbnail_pic_s03;

    public NewsInfoBean(NewsBean.ResultBean.DataBean dataBean) {
        this.uniquekey = dataBean.getUniquekey();
        this.title = dataBean.getTitle();
        this.date = dataBean.getDate();
        this.category = dataBean.getCategory();
        this.author_name = dataBean.getAuthor_name();
        this.url = dataBean.getUrl();
        this.thumbnail_pic_s = dataBean.getThumbnail_pic_s();
        this.thumbnail_pic_s02 = dataBean.getThumbnail_pic_s02();
        this.thumbnail_pic_s03 = dataBean.getThumbnail_pic_s03();
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    @Override
    public String toString() {
        return "NewsInfoBean{" +
                "uniquekey='" + uniquekey + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", author_name='" + author_name + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                '}';
    }
}
