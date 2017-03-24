package com.example.chris.sqliteexamwithoutfragmentsstandlistview;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    SimpleDateFormat mSimpleDateFormat;
    Button submit;
    TextView displayForDate;
    Button viewDatabase;
    public static String dateFormatted = "";
    public static String userNameparse = "";
    static String parseScore = "";
    DbHelper mDbHelper;
    SQLiteDatabase mSQLiteDatabase;
    EditText score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.username_txt);
        score =(EditText)findViewById(R.id.score_txt);
        submit = (Button) findViewById(R.id.submit_btn);
        displayForDate = (TextView) findViewById(R.id.date);
        viewDatabase = (Button) findViewById(R.id.viewDatabase_btn);
        mSimpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormatted = mSimpleDateFormat.format(new Date());


        displayForDate.setText(dateFormatted);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userNameparse = userName.getText().toString();
                parseScore = score.getText().toString();
                if (userNameparse.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Fill In A UserName", Toast.LENGTH_SHORT).show();
                }  if (parseScore.isEmpty()){
                    Toast.makeText(getBaseContext(),"Fill In A Score",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(), "You have Entered: " + userNameparse, Toast.LENGTH_LONG).show();

                }

                sendInformationToSQLDatabase();
            }
        });



        viewDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListOfScoresActivity.class));
            }
        });


    }

    public void sendInformationToSQLDatabase(){


        mDbHelper = new DbHelper(getApplicationContext());
        mSQLiteDatabase = mDbHelper.getWritableDatabase();
        mDbHelper.addInformation(userNameparse, parseScore, dateFormatted, mSQLiteDatabase);
        mDbHelper.close();

    }


}
