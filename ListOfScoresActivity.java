package com.example.chris.sqliteexamwithoutfragmentsstandlistview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by chris on 3/7/2017.
 */

public class ListOfScoresActivity extends AppCompatActivity {

    ListView mListView;
    CustomAdapter mCustomAdapter;

    SQLiteDatabase mSQLiteDatabase;
    DbHelper mDbHelper;
    Cursor mCursor;
    private String gettingUsernameFromItemClicked;
    Context mContext; // context variable for this activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_scores);

        mContext = this;// initializing this activity context to use in the Alert Dialog

        mListView = (ListView) findViewById(R.id.list_view);
        mCustomAdapter = new CustomAdapter(mContext, R.layout.created_row);


        mListView.setAdapter(mCustomAdapter);


        getInformation();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView textview = (TextView) view.findViewById(R.id.username_txt);
                gettingUsernameFromItemClicked = textview.getText().toString();
                // String text = (String)mListView.getItemAtPosition(i); does not work due to dataprovider dont know why

                Toast.makeText(mContext, " " + gettingUsernameFromItemClicked, Toast.LENGTH_SHORT).show();

                deleteAlertDialog();
            }


        });


    }


    public void getInformation() {


        mDbHelper = new DbHelper(getApplicationContext());
        mSQLiteDatabase = mDbHelper.getReadableDatabase();
        mCursor = mDbHelper.getInformation(mSQLiteDatabase);
        if (mCursor.moveToFirst()) {// will return true if information is available false if not

            do {

                String userName = mCursor.getString(0);
                String score = mCursor.getString(1);
                String date = mCursor.getString(2);
                DataProvider dataProvider = new DataProvider(userName, score, date); // sending to dataprovider class to make into objects
                mCustomAdapter.add(dataProvider);// adding objects


            } while (mCursor.moveToNext()); // all information is stored in mCursor
        }


    }

    public void deleteAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteData();
            }
        });

        builder.setTitle("Delete Item? ");
        builder.setMessage("Would you like to delete this score? ");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void deleteData() {

        mDbHelper = new DbHelper(getApplicationContext());
        mSQLiteDatabase = mDbHelper.getReadableDatabase();
        String[] whereArgs = new String[]{gettingUsernameFromItemClicked};// to ensure no sql injection
        String whereClause = DbHelper.USER_NAME + " = ?"; // to ensure no sql injection

        mDbHelper.deleteInformation(whereClause, whereArgs, mSQLiteDatabase);


        recreate(); // refreshes the activity to show the deleted person
    }


}
