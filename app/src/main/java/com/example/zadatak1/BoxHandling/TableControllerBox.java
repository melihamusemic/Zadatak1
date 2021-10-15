package com.example.zadatak1.BoxHandling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerBox extends DatabaseHandler {
    public TableControllerBox(Context context) {
        super(context);
    }

    public boolean create(Box box) {
        ContentValues values = new ContentValues();
        values.put("name", box.getName());
        values.put("width", box.getWidth());
        values.put("height", box.getHeight());
        values.put("count", box.getCount());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean createSuccessful = db.insert("boxes", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM boxes";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<Box> read() {
        List<Box> recordsList = new ArrayList<Box>();
        String sql = "SELECT * FROM boxes";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String boxName = cursor.getString((cursor.getColumnIndex("name")));
                int boxWidth = cursor.getInt((cursor.getColumnIndex("width")));
                int boxHeight = cursor.getInt((cursor.getColumnIndex("height")));
                int boxCount = cursor.getInt((cursor.getColumnIndex("count")));

                Box box = new Box(boxName, boxWidth, boxHeight, boxCount);

                recordsList.add(box);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return recordsList;
    }
}