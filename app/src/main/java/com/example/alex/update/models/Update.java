package com.example.alex.update.models;

import org.parceler.Parcel;

/**
 * Created by alex on 6/19/17.
 */

@Parcel
public class Update {
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mImageUrl;
    private String mPublishedAt;



    //    private String mCreateDate;
//    private String mUpdateDate;
    public  Update(){}
    public  Update(String author, String title, String description, String url, String imageUrl, String publishedAt) {
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mImageUrl = imageUrl;
        this.mPublishedAt = publishedAt;
    }


    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }
}
