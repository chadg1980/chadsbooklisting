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
    private Uri mThumbnail;


    /**
     * @param authors is an array of authors of the book
     * @param title is the title of the book
     * @param publishDate is the year the book was pubslished
     * @param thumbnail is a link to a thumbnail image of the book
     * **/

    public Book(String title, String[] authors,  String publishDate, Uri thumbnail){
        this.mAuthors = authors;
        this.mTitle = title;
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

    public Uri getmThumbnail() {
        return mThumbnail;
    }
}
