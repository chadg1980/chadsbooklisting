package com.h.chad.chadsbooklisting;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.author;
import static android.R.attr.thumbnail;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.h.chad.chadsbooklisting.R.id.add;
import static com.h.chad.chadsbooklisting.R.id.authors;
import static com.h.chad.chadsbooklisting.R.id.year;

/**
 * Created by chad on 1/30/2017.
 */

public class BookUtils {

    public static final String LOG_TAG = BookUtils.class.getSimpleName();

    public static ArrayList<Book> getBookData(String requestURL){
        //Create the URL from the string
        URL url = createURL(requestURL);

        //perform the https requst.
        String jsonResponse = null; //Create this string prior to try block
        try {
            jsonResponse = makehttpRequest(url);

        }catch (IOException e){
            Log.e(LOG_TAG, "getBookData, trying to make http request", e);
        }

        ArrayList bookData = extractJSON(jsonResponse);
        return bookData;
    }

    /**
     * String url as input
     * calls the URL and returns a string in JSON format
    * */
    private static String makehttpRequest(URL url) throws IOException{
        String jsonResponse = null;
        if(url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        //prepare HTTP request to the URL and receive a JSON response back
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(100000/*milliseconds*/);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        }
        catch(IOException e){
                Log.i(LOG_TAG, "http request Failed in catch", e);
            }
        finally {
            if(urlConnection!= null){
                urlConnection.disconnect();
            }
        }
        return jsonResponse;
    }
    /**
     * Creates a new URL object from a url string
     * input: String url
     * returns: a URL
     * */
    private static URL createURL(String stringURL){
        URL url = null;
        try {
            url = new URL(stringURL);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG, "createURL ", e);
        }
        return url;
    }
    //String Builder from Input Stream
    private static String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String oneLine = reader.readLine();
            while (oneLine != null){
                output.append(oneLine);
                oneLine = reader.readLine();
            }
        }
        return output.toString();
    }
    //Getting the Title, Author, Year, and a thumbnail from the jsonData returned from the query.
    private static ArrayList<Book> extractJSON(String jsonData){

        if(TextUtils.isEmpty(jsonData)){
            return null;
        }
        ArrayList<Book> books = new ArrayList<>();



        try{
            JSONObject baseResponse = new JSONObject(jsonData);
            JSONArray itemsArray = baseResponse.getJSONArray("items");

            for(int i = 0; i < itemsArray.length(); i++) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject bookInfo = book.getJSONObject("volumeInfo");
                //get the thumbnail image
                JSONObject thumbnailObject = bookInfo.getJSONObject("imageLinks");


                //Getting the Authors information from the authors array
                JSONArray authorsArray = bookInfo.getJSONArray("authors");
                int authorsLen = authorsArray.length();
                String[] authors = new String[authorsLen];
                if (authorsLen > 0) {

                    for (int j = 0; j < authorsLen; j++) {
                        authors[j] = authorsArray.getString(j);
                    }
                }else{
                    authors[0] = authorsArray.getString(0);
                }

                String title = bookInfo.getString("title");
                String year = bookInfo.getString("publishedDate");
                String thumbString = thumbnailObject.getString("thumbnail");
                Book addBook = new Book(title, authors, year, thumbString);
                books.add(addBook);
            }
        }catch (JSONException e){
            Log.e(LOG_TAG, "Error parsing json", e);
        }

        return books;

    }

}
