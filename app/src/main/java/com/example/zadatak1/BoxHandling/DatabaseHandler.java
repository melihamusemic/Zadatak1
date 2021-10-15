package com.example.zadatak1.BoxHandling;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "BoxDatabase";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE boxes " +
                "( name TEXT, " +
                "height INTEGER, " +
                "width INTEGER, " +
                "count INTEGER); ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS boxes";
        db.execSQL(sql);
        onCreate(db);
    }
}
