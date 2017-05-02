package com.example.chris.sqliteexamwithoutfragmentsstandlistview;

/**
 * Created by chris on 5/2/2017.
 *
 * Reason for this class is to provide each row of data from sql as an object
 *
 */

public class DataProvider {

    private String userName;
    private String score;
    private String date;

    public String getUserName() {
        return userName;
    }

    public String getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public DataProvider(String userName, String score, String date){

        this.userName = userName;
        this.score = score;
        this.date = date;
    }
}
