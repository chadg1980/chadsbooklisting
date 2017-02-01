package com.h.chad.chadsbooklisting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.bitmap;
import static android.R.attr.thumbnail;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by chad on 2/1/2017.
 */

class GetImage{
    public static final String LOG_TAG = BookAdapter.class.getSimpleName();
    String mUrl;
    ImageView mIm;

    public GetImage(String url, ImageView im){
        this.mUrl = url;
        this.mIm = im;
        new GetImage.DownloadImageTask(im).execute(url);
    }


    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView mImageView;

        public DownloadImageTask(ImageView thisImageView){
            this.mImageView = thisImageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap thumb = null;
            try{
                InputStream in = new java.net.URL(urlDisplay).openStream();
                thumb = BitmapFactory.decodeStream(in);
            }catch (Exception e){
                Log.i(LOG_TAG, "#############", e);
            }
            return thumb;
        }
        protected void onPostExecute(Bitmap result){
            mImageView.setImageBitmap(result);
        }
    }

}
