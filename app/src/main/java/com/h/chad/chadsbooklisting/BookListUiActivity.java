package com.h.chad.chadsbooklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chad on 1/31/2017.
 */

public class BookListUiActivity extends AppCompatActivity {

    private static final String LOG_TAG = BookListUiActivity.class.getName();
    private static final String url0 = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String url2 = "&maxResults=10";
    private String url1 = "";
    private TextView mEmptyStateTextView;
    private ProgressBar mLoadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        mEmptyStateTextView = (TextView) findViewById(R.id.empty);
        mLoadingProgress = (ProgressBar) findViewById(R.id.listLoadingBar);

        if (isConnected) {

            Bundle bundle = getIntent().getExtras();
            url1 = bundle.getString("queryString");

            new BookListUiActivity.BookAsyncTask().execute(url0 + url1 + url2);
        } else {

            mLoadingProgress.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.noNetwork);
            mEmptyStateTextView.setVisibility(View.VISIBLE);
        }
    }

    private void updateUi(ArrayList<Book> books) {
        BookAdapter bookAdapter = new BookAdapter(this, books);
        int count = bookAdapter.getCount();
        mLoadingProgress.setVisibility(View.GONE);
        Log.i("count: " + Integer.toString(count), " why is this not working!");
        if (count == 0) {
            mEmptyStateTextView.setText("No Books Founs");
            mEmptyStateTextView.setVisibility(View.VISIBLE);

        } else {
            ListView myList = (ListView) findViewById(R.id.list);
            myList.setAdapter(bookAdapter);
            myList.setVisibility(View.VISIBLE);

        }

    }

    private class BookAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            ArrayList<Book> returnJson = BookUtils.getBookData(urls[0]);
            return returnJson;
        }


        protected void onPostExecute(ArrayList<Book> bookData) {
            if (bookData == null) {
                return;
            }
            updateUi(bookData);

        }
    }
}
