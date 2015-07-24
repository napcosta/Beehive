package com.example.bee.beehive.Activities;

import android.app.FragmentManager;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;

import com.example.bee.beehive.Apiary;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.dbListEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nuno on 13/06/2015.
 */
public abstract class ListItems extends ActionBarActivity
{
    public DatabaseHandler db = new DatabaseHandler(this);

    protected List<Object> db_entries;

    public abstract List<String> getData(int apiary_id, int hive_id);

    public abstract Class getGoToClass();

    public abstract Cursor getCursor(int apiary_id, int hive_id);

    protected List<String> convertToListOfStrings(List<dbListEntry> list_entries)
    {
        ArrayList<String> strings = new ArrayList<String>();

        for(dbListEntry a : list_entries) {
            strings.add(a != null ? a.getName() : null);
        }
        return strings;
    }

    public void add(String apiary_name)
    {
        db.addApiary(new Apiary(apiary_name));
        reloadActivity();
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

    public abstract String getColumnName();
}
