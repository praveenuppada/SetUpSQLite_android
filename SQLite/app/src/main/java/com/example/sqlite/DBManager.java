package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager (Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.SUBJECT, name);
        contentValues.put(DataBaseHelper.DESC, desc);
        database.insert(DataBaseHelper.TABLE_NAME, null, contentValues);
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.SUBJECT, name);
        contentValues.put(DataBaseHelper.DESC, desc);
        int i = database.update(DataBaseHelper.TABLE_NAME, contentValues, DataBaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper._ID + " = " + _id, null);
    }

    public Cursor fetch() {
        String[] columns = new String[] {DataBaseHelper._ID, DataBaseHelper.SUBJECT,DataBaseHelper.DESC};
        Cursor cursor = database.query(DataBaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
