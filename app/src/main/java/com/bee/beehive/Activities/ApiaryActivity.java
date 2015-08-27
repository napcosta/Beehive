package com.bee.beehive.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.bee.beehive.Fragments.AddApiaryOverlay;
import com.bee.beehive.R;




public class ApiaryActivity extends ListItem {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        setTitle(getString(R.string.apiary));
        startAdMob();


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

    public void deleteButton(View view)
    {
        final View view_arg = view;

        new AlertDialog.Builder(this)
                .setTitle(R.string.delete_apiary_overlay_title)
                .setMessage(getText(R.string.delete_overlay_message) + " " + "\""+view_arg.getTag(R.string.name).toString()+"\"?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteApiary(Integer.valueOf(view_arg.getTag(R.string.id).toString()));
                        cursorAdapter.changeCursor(getCursor(1, 1));
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    public String getKeyName()
    {
        return db.getKeyApiaryName();
    }

    @Override
    public String getSubText(int apiary_id)
    {
        return db.getHiveCount(apiary_id) + " "+getString(R.string.apiaries);
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
