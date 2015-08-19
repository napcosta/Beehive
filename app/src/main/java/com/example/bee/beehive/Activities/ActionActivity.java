package com.example.bee.beehive.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.bee.beehive.Action;
import com.example.bee.beehive.Fragments.AddActionOverlay;
import com.example.bee.beehive.Fragments.AddHiveOverlay;
import com.example.bee.beehive.R;


/**
 * Created by Nuno on 03/08/2015.
 */
public class ActionActivity extends ListItem
{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hive);
//		setTitle(getIntent().getStringExtra("apiary_name"));

		//db.addAction(new Action(1,1,1));


	}

	public void add(String action_name, String target, int day, int month, int year)
	{
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

		db.addAction(new Action(action_name, target, day, month, year, mSharedPreferences.getInt("hive_id", 0)));

		cursorAdapter.changeCursor(getCursor(1, 1));
	}

	public void deleteButton(View view)
	{
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
		db.deleteAction(Integer.valueOf(view.getTag().toString()), mSharedPreferences.getInt("hive_id", 0));
		cursorAdapter.changeCursor(getCursor(1, 1));
	}

	@Override
	public Class getGoToClass()
	{
		return ActionActivity.class;
	}

	@Override
	public Cursor getCursor(int apiary_id, int hive_id) {
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
		return db.getActionsCursor(mSharedPreferences.getInt("apiary_id", 0), (mSharedPreferences.getInt("hive_id", 0)));
	//	return db.getActionsCursor(apiary_id, hive_id);
	}

	@Override
	public String getKeyName() {
		return db.getKeyActionName();
	}

	@Override
	public String getSubText(int id) {
		return db.getActionDate(id);
	}
/*
	public void showDialog()
	{
		AddActionOverlay addOverlay = new AddActionOverlay();
		addOverlay.show(getFragmentManager(), "Add Overlay");

	}
*/
	public void popDialog()
	{
		AddActionOverlay addOverlay = new AddActionOverlay();
		addOverlay.show(getFragmentManager(), "Add Overlay");

	}

	public void popDialog(int id)
	{
		AddActionOverlay addOverlay = new AddActionOverlay();
		addOverlay.onAttach(this);
		addOverlay.show(getFragmentManager(), "AddOverlay");
		addOverlay.setActionData(id);
	}

	@Override
	public String getKeyId() {
		return db.getKeyActionId();
	}
}
