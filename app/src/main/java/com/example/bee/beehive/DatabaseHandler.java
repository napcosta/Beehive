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
    private static final String KEY_APIARY_ID = "_id";
	private static final String KEY_HIVES_COUNT = "hives_count";
    private static final String KEY_HIVE_ID = "hive_id";
    private static final String KEY_APIARY_NAME = "apiary_name";
    private static final String KEY_HIVE_NUMBER = "hive_number";
    private static final String TABLE_ACTIONS = "table_actions";
    private static final String KEY_ACTION_ID = "_id";
    private static final String KEY_ACTION_NAME = "action_name";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String CREATE_APIARIES_TABLE = "CREATE TABLE " + TABLE_APIARIES + "(" +
                KEY_APIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_APIARY_NAME + " TEXT UNIQUE" +
            ")";
        String CREATE_HIVES_TABLE = "CREATE TABLE " + TABLE_HIVES + "(" +
                KEY_HIVE_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT," +
                KEY_HIVE_NUMBER + " INTEGER," +
                KEY_APIARY_ID + " INTEGER," +
                " FOREIGN KEY (" + KEY_APIARY_ID + ") REFERENCES " + TABLE_APIARIES + " (" + KEY_APIARY_ID + ")," +
                " UNIQUE (" + KEY_APIARY_ID + ", " + KEY_HIVE_NUMBER + ")" +
            ")";

        String CREATE_ACTIONS_TABLE = "CREATE TABLE " + TABLE_ACTIONS + "(" +
                KEY_ACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_ACTION_NAME + " Text," +
                KEY_HIVE_ID + " INTEGER," +
                " FOREIGN KEY (" + KEY_HIVE_ID + ") REFERENCES " + TABLE_HIVES + " (" + KEY_HIVE_ID + ")," +
                " UNIQUE (" + KEY_HIVE_ID + ")" +
            ")";
        db.execSQL(CREATE_APIARIES_TABLE);
        db.execSQL(CREATE_HIVES_TABLE);
        db.execSQL(CREATE_ACTIONS_TABLE);
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

        try {
            db.insertOrThrow(TABLE_APIARIES, null, values);
        } catch (SQLiteConstraintException e) {
            System.out.println("EXCEPTION ---------------> APIARY ALREADY EXISTS");
        }
        db.close();
    }

    public String getKeyApiaryName()
    {
        return KEY_APIARY_NAME;
    }

    public String getKeyApiaryId()
    {
        return KEY_APIARY_ID;
    }

    public String getKeyHiveId()
    {
        return KEY_HIVE_ID;
    }

	public String getKeyActionId()
	{
		return KEY_ACTION_ID;
	}

	public String getKeyActionName()
	{
		return KEY_ACTION_NAME;
	}

    public String getKeyHiveName()
    {
        return KEY_HIVE_NUMBER;
    }

    public void deleteApiary(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_APIARIES, KEY_APIARY_ID + " =?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteHive(int hive_id, int apiary_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HIVES, KEY_APIARY_ID + " =? AND " + KEY_HIVE_ID + " =?", new String[]{String.valueOf(apiary_id), String.valueOf(hive_id)});
    }

	public void deleteAction(int action_id, int hive_id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ACTIONS, KEY_HIVE_ID + " =? AND " + KEY_ACTION_ID + " =?", new String[]{String.valueOf(hive_id), String.valueOf(action_id)});
	}

    public void addHive(Hive hive)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HIVE_NUMBER, hive.getName());
        values.put(KEY_APIARY_ID, hive.getApiaryID());

        try {
            db.insertOrThrow(TABLE_HIVES, null, values);
        } catch (SQLiteConstraintException e) {
            System.out.println("EXCEPTION ----------------------> HIVE ALREADY EXISTS");
        }
		//db.execSQL("UPDATE " + TABLE_APIARIES + " SET " + KEY_HIVES_COUNT + " = " + KEY_HIVES_COUNT + " +1 WHERE " + KEY_APIARY_ID + " = " + hive.getApiaryID());

        db.close();
    }

	public String getHiveCount(int apiary_id)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_HIVES + " WHERE " + KEY_APIARY_ID + " = " + apiary_id, null);
		mCount.moveToFirst();
		String count = mCount.getString(0);
		db.close();

		return count;
	}


    public void addAction(Action action)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACTION_NAME, action.getName());
        values.put(KEY_HIVE_ID, action.getHiveID());

        try {
            db.insertOrThrow(TABLE_ACTIONS, null, values);
        } catch (SQLiteConstraintException e) {
            System.out.println("EXCEPTION ----------------------> ACTION ALREADY EXISTS");
        }

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

    public Cursor getApiariesCursor()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_APIARIES;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getHivesCursor(int apiary_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_HIVES + " WHERE " + KEY_APIARY_ID + " = " + apiary_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getActionsCursor(int apiary_id, int hive_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ACTIONS + " WHERE " + KEY_HIVE_ID + " = " + hive_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public List<dbListEntry> getAllApiaries()
    {
        List<dbListEntry> apiaryList = new ArrayList<dbListEntry>();

        String selectQuery = "SELECT * FROM " + TABLE_APIARIES;

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

    public List<dbListEntry> getAllHives(int apiary_id) {
        List<dbListEntry> hiveList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_HIVES + " WHERE " + KEY_APIARY_ID + " = " + apiary_id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {

            do {
                Hive hive = new Hive(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)));
                hiveList.add(hive);
            } while (cursor.moveToNext());

        }

        return hiveList;
    }

}
