package com.example.bee.beehive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nuno on 15/06/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "beehiveDB";
    private static final String TABLE_HIVES = "hives";
    private static final String TABLE_APIARIES = "apiaries";
    private static final String KEY_APIARY_ID = "apiary_id";
    private static final String KEY_HIVE_ID = "hive_id";
    private static final String KEY_APIARY_NAME = "apiary_name";
    private static final String KEY_HIVE_NUMBER = "hive_number";
    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String CREATE_APIARIES_TABLE = "CREATE TABLE " + TABLE_APIARIES + "(" +
                    KEY_APIARY_ID + " INTEGER PRIMARY KEY," +
                    KEY_APIARY_NAME + " TEXT UNIQUE" +
                ")";
        String CREATE_HIVES_TABLE = "CREATE TABLE " + TABLE_HIVES + "(" +
                    KEY_HIVE_ID + " INTEGER PRIMARY KEY," +
                    KEY_HIVE_NUMBER + " INTEGER UNIQUE," +
                    " FOREIGN KEY (" + KEY_HIVE_NUMBER + ") REFERENCES " + TABLE_APIARIES + " (" + KEY_APIARY_ID + ")" +
                ")";
        db.execSQL(CREATE_APIARIES_TABLE);
        db.execSQL(CREATE_HIVES_TABLE);
        System.out.println("CREATING DATABASE");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_APIARIES);
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_HIVES);
        // Creates tables again
        onCreate(db);
    }


    public void addApiary(Apiary apiary)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_APIARY_NAME, apiary.getName());
      //  System.out.println(values.toString());

        try {
            db.insertOrThrow(TABLE_APIARIES, null, values);
        } catch (SQLiteConstraintException e) {
            System.out.println("EXCEPTION ---------------> APIARY ALREADY EXISTS");
        }
        db.close();
    }

    public void addHive(Hive hive)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HIVE_NUMBER, hive.getNumber());

        db.insert(TABLE_HIVES, null, values);

        db.close();
    }

    public Apiary getApiary(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor: query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = db.query(
                TABLE_APIARIES, new String[] {KEY_APIARY_ID, KEY_APIARY_NAME}, KEY_APIARY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Apiary apiary = new Apiary(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

        return apiary;
    }

    public List<Apiary> getAllApiaries()
    {
        List<Apiary> apiaryList = new ArrayList<Apiary>();

        String selectQuery = "SELECT *FROM " + TABLE_APIARIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Apiary apiary = new Apiary();
                apiary.setID(Integer.parseInt(cursor.getString(0)));
                apiary.setName(cursor.getString(1));
                apiaryList.add(apiary);
            } while (cursor.moveToNext());
        }
        return apiaryList;
    }

    public List<Hive> getAllHives()
    {
        List<Hive> hiveList = new ArrayList<Hive>();

        String selectQuery = "SELECT *FROM " + TABLE_HIVES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Hive hive = new Hive();
                hive.setID(Integer.parseInt(cursor.getString(0)));
                hive.setName(cursor.getInt(1));
                hiveList.add(hive);
            } while (cursor.moveToNext());
        }
        return hiveList;
    }


}
