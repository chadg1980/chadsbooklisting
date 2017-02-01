package com.h.chad.chadsbooklisting;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.h.chad.chadsbooklisting.R.id.thumbNail;

/**
 * Created by chad on 1/31/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    public BookAdapter(Activity context, ArrayList<Book> book){
        super(context, 0, book);
    }
    /**
     * provide an adapter for the listview
     * @param position position in the list
     * @param convertView the recycle view to populate
     * @param parent the parent viewgroup
     *
     * **/
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }
        //Get the object at the position in the list
        Book currentBook = getItem(position);

        //Find the TextView in the book_item with the id title
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        //set the title of the book to the textview
        titleTextView.setText(currentBook.getmTitle());


        //Aurhtors is an array, we need to check if there are multiple authors
        String [] authorsArray = currentBook.getmAuthors();

        TextView authorTextView = (TextView)listItemView.findViewById(R.id.authors);
        authorTextView.setText(authorsArray.toString());

        //Find the TextView in the book_item with the id title
        TextView publishDateTextView = (TextView) listItemView.findViewById(R.id.year);
        //set the title of the book to the textview
        publishDateTextView.setText(" - " + currentBook.getmPublishDate());

        //Find the imageview for the thumbnail
        ImageView thumbnailImage = (ImageView)listItemView.findViewById(thumbNail);
        thumbnailImage.setImageURI(currentBook.getmThumbnail());

        return listItemView;
    }

}
