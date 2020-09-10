package com.news.reporter;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsContent {
    public String title;
    public String imgUrl;
    public String link;
    public String source;

    public NewsContent(String title, String imgUrl, String link, String source)    {
        this.title = title;
        this.imgUrl = imgUrl;
        this.link = link;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
