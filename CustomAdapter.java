package com.example.chris.sqliteexamwithoutfragmentsstandlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 5/2/2017.
 */

public class CustomAdapter extends ArrayAdapter {

    List mList = new ArrayList(); // list variable for items



    public CustomAdapter(Context context, int resource){ // default constructor
        super(context, resource);

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    static class LayoutHandler{
        TextView userName,score,date; // declaring the textviews to use within the list
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.created_row,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.userName = (TextView)row.findViewById(R.id.username_txt);
            layoutHandler.score =(TextView)row.findViewById(R.id.score_txt);
            layoutHandler.date = (TextView)row.findViewById(R.id.date_txt);
            row.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler)row.getTag();
        }

        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.userName.setText(dataProvider.getUserName());
        layoutHandler.score.setText(dataProvider.getScore());
        layoutHandler.date.setText(dataProvider.getDate());


        return row;

    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        mList.add(object);
    }
}
