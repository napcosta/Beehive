package com.example.bee.beehive;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nuno on 15/06/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "beehiveDB";
//    private static final String TABLE_HIVES = "hives";
    private static final String TABLE_APIARIES = "apiaries";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "apiary_name";


    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_HIVES_TABLE = "CREATE TABLE " + TABLE_APIARIES + "(" + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT," + ")";
        db.execSQL(CREATE_HIVES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_APIARIES);

        // Creates tables again
        onCreate(db);
    }
/*
    void addApiary(Apiary apiary)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, apiary.getna)
    }*/
}
