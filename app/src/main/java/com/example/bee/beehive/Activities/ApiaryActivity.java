package com.example.bee.beehive.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.bee.beehive.Fragments.AddApiaryOverlay;
import com.example.bee.beehive.R;



public class ApiaryActivity extends ListItem {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        setTitle("Apiaries");

    }

    public void popDialog()
    {
        AddApiaryOverlay addOverlay = new AddApiaryOverlay();
        addOverlay.show(getFragmentManager(), "Add Overlay");

    }

    public void popDialog(String name, int id)
    {
        AddApiaryOverlay addOverlay = new AddApiaryOverlay();
        addOverlay.show(getFragmentManager(), "Add Overlay");
        addOverlay.setName(name, id);

    }

	public void changeName(String name, int id)
	{
		db.changeApiaryName(id, name);
		cursorAdapter.changeCursor(getCursor(1, 1));
	}

    public void deleteButton(View view) {
        db.deleteApiary(Integer.valueOf(view.getTag().toString()));
        cursorAdapter.changeCursor(getCursor(1, 1));
    }

    public String getKeyName()
    {
        return db.getKeyApiaryName();
    }

    @Override
    public String getSubText(int apiary_id)
    {
        return db.getHiveCount(apiary_id) + " apiaries";
    }

    public String getKeyId()
	{
		return db.getKeyApiaryId();
	}

	public Class getGoToClass()
	{
		return HiveActivity.class;
	}

    public Cursor getCursor(int apiary_id, int hive_id)
    {
       // System.out.println();
       // return db.getHivesCursor(11);
        return db.getApiariesCursor();
    }

}
