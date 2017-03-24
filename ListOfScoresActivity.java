package com.example.chris.sqliteexamwithoutfragmentsstandlistview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

/**
 * Created by chris on 3/7/2017.
 */

public class ListOfScoresActivity extends AppCompatActivity {

    ListView mListView;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> items = new ArrayList<>();
    SQLiteDatabase mSQLiteDatabase;
    DbHelper mDbHelper;
    Cursor mCursor;
    private String userName;
    private String score;
    private String date;
    String parsed;





    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_scores);


        mListView = (ListView)findViewById(R.id.list_view);
        mAdapter = new ArrayAdapter<>(this, simple_list_item_1,items);

        mListView.setAdapter(mAdapter);


        getInformation();






    }




    public void getInformation() {

        mDbHelper = new DbHelper(getApplicationContext());
        mSQLiteDatabase = mDbHelper.getReadableDatabase();
        mCursor = mDbHelper.getInformation(mSQLiteDatabase);
        if (mCursor.moveToFirst()) {// will return true if information is available false if not

            do {

                userName = mCursor.getString(0);
                score = mCursor.getString(1);
                date = mCursor.getString(2);
               // items.add("Username: "+userName);
                //items.add("Score: "+date);
                //items.add("Date: "+score);
                 parsed = userName.toString() +": Score : "+  date.toString() +" Date : " + score.toString();
                items.add(parsed);




            }while (mCursor.moveToNext()); // all information is stored in mCursor
        }


    }

}
