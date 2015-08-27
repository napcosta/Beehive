package com.bee.beehive.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.bee.beehive.Action;
import com.bee.beehive.Fragments.AddActionOverlay;
import com.bee.beehive.Fragments.AddHiveOverlay;
import com.bee.beehive.R;


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

		startAdMob();


	}

	public void add(String action_name, String target, int day, int month, int year)
	{
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

		db.addAction(new Action(action_name, target, day, month, year, mSharedPreferences.getInt("hive_id", 0)));

		cursorAdapter.changeCursor(getCursor(1, 1));
	}

	public void deleteButton(View view)
	{
		final View view_arg = view;

		new AlertDialog.Builder(this)
				.setTitle(R.string.delete_action_overlay_title)
				.setMessage(getText(R.string.delete_overlay_message) + " " + "\"" + view_arg.getTag(R.string.name).toString() + "\"?")
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
						db.deleteAction(Integer.valueOf(view_arg.getTag(R.string.id).toString()), mSharedPreferences.getInt("hive_id", 0));
						cursorAdapter.changeCursor(getCursor(1, 1));
					}
				})
				.setNegativeButton(R.string.cancel, null)
				.show();



	//	mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
	//	db.deleteAction(Integer.valueOf(view.getTag().toString()), mSharedPreferences.getInt("hive_id", 0));
	//	cursorAdapter.changeCursor(getCursor(1, 1));
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
