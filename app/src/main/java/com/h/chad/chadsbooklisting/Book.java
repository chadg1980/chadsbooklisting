package com.h.chad.chadsbooklisting;

import android.net.Uri;

import static android.R.attr.thumbnail;

/**
 * Created by chad on 1/30/2017.
 */

public class Book {

    private String[] mAuthors;
    private String mTitle;
    private String mPublishDate;
    private String mThumbnail;

    /**
     * @param authors is an array of authors of the book
     * @param title is the title of the book
     * @param publishDate is the year the book was pubslished
     * @param thumbnail is a link to a thumbnail image of the book
     * **/

    public Book(String title, String[] authors,  String publishDate, String thumbnail){
        this.mTitle = title;
        this.mAuthors = authors;
        this.mPublishDate = publishDate;
        this.mThumbnail = thumbnail;
    }

    public String[] getmAuthors() {
        return mAuthors;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmPublishDate() {
        return mPublishDate;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }
}
