package com.example.bee.beehive;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Nuno on 13/06/2015.
 */
public abstract class ListItems extends ActionBarActivity
{

    protected List<Object> db_entries;

    public abstract List<String> getData(int apiary_id, int hive_id);

    public abstract Class getGoToClass();

    public abstract Cursor getCursor();

    protected List<String> convertToListOfStrings(List<dbListEntry> list_entries)
    {
        ArrayList<String> strings = new ArrayList<String>();

        for(dbListEntry a : list_entries) {
            strings.add(a != null ? a.getName() : null);
        }
        return strings;
    }
}
