package com.h.chad.chadsbooklisting;

import android.net.Uri;
import android.util.Log;

import static android.R.attr.thumbnail;

/**
 * Created by chad on 1/30/2017.
 */

public class Book {

    private String[] mAuthors;
    private String mTitle;
    private String mPublishDate;
    private String mThumbnail;
    private boolean hasAuthor;
    private static final String LOG_TAG = Book.class.getSimpleName();

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
    public Book(String title,  String publishDate, String thumbnail){
        this.mTitle = title;
        this.mPublishDate = publishDate;
        this.mThumbnail = thumbnail;
        this.mAuthors = new String[0];
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

    public boolean isHasAuthor(){
        return (mAuthors != null);



    }
}
