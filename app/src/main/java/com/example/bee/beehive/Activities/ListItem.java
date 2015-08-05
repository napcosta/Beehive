package com.example.bee.beehive.Activities;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.bee.beehive.CustomCursorAdapter;

import com.example.bee.beehive.Apiary;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.dbListEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nuno on 13/06/2015.
 */
public abstract class ListItem extends ActionBarActivity
{

    public static final String PREFERENCES = "preferences";

	SharedPreferences mSharedPreferences;

    protected int clicked_id = -1;

    public CustomCursorAdapter cursorAdapter;

    public DatabaseHandler db = new DatabaseHandler(this);

    public abstract Class getGoToClass();

    public abstract Cursor getCursor(int apiary_id, int hive_id);

	public abstract void deleteButton(View view);

    public void add(String apiary_name)
    {
        db.addApiary(new Apiary(apiary_name));
        cursorAdapter.changeCursor(getCursor(1, 1));
    }

    private void reloadActivity()
    {
        finish();
        startActivity(getIntent());
    }

    public void showDialog()
    {
        FragmentManager manager = getFragmentManager();
        AddOverlay addOverlay = new AddOverlay();
        addOverlay.show(manager, "AddOverlay");
    }

    public void setClickedId(int id)
    {
        clicked_id = id;
    }

    public abstract String getKeyName();

	public abstract String getSubText(int id);

    public abstract String getKeyId();

	public void setCursorAdapter(CustomCursorAdapter adapter)
	{
		cursorAdapter = adapter;
	}
	
}
