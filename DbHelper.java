package com.example.chris.sqliteexamwithoutfragmentsstandlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chris on 3/7/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = "DATABASE_OPERATION";

    private static final String DATABASE_NAME = "Score_Table";
    private static final int DATABASE_VERSION =1;
    public static final String SCORE_TABLE_NAME = "score_table"; // key used to represent the column
    public static final String SCORE = "score";
    public static final String USER_NAME ="User_name";
    public static final String DATE = "Date";
    private static final String CREATE_QUERY = "CREATE TABLE "+ SCORE_TABLE_NAME+"("+ USER_NAME+" Text,"
            + SCORE+" Text,"+ DATE+" Text);"; // pay attention to spacing carefully

    public DbHelper(Context context){//constructor
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e(TAG,"DATABASE CREATED/OPENED");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // create table is only called the first time once the table is created it is not called

        sqLiteDatabase.execSQL(CREATE_QUERY);
        Log.e(TAG,"Table Created");
    }

    public void addInformation(String name, String score, String Date, SQLiteDatabase db ){

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,name);
        contentValues.put(SCORE,score);
        contentValues.put(DATE,Date);
        db.insert(SCORE_TABLE_NAME, null , contentValues);
        Log.e(TAG,"One row is inserted");
    }

    public Cursor getInformation(SQLiteDatabase db){

        Cursor cursor;// objects that retrieves information from the database
        String[] projections = {USER_NAME,DATE,SCORE};
        String orderBy = SCORE+" DESC";
        String limit = "5";
        

        cursor = db.query(SCORE_TABLE_NAME,projections,null,null,null,null,orderBy,limit); // the nulls have to do with where clause info

        return cursor;
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
