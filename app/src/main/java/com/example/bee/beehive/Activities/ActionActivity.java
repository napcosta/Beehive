package com.example.bee.beehive.Activities;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bee.beehive.Action;
import com.example.bee.beehive.Hive;
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

	public void add(int action_id)
	{
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

		db.addAction(new Action(action_id, mSharedPreferences.getInt("apiary_id", 0), mSharedPreferences.getInt("hive_id", 0)));

		cursorAdapter.changeCursor(getCursor(1, 1));
	}

	public void deleteButton(View view)
	{
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
		db.deleteAction(Integer.valueOf(view.getTag().toString()), mSharedPreferences.getInt("apiary_id", 0));
		cursorAdapter.changeCursor(getCursor(1, 1));
	}

	@Override
	public Class getGoToClass()
	{
		return null;
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
		return "";
	}

	public void showDialog()
	{
		AddActionOverlay addOverlay = new AddActionOverlay();
		addOverlay.show(getFragmentManager(), "Add Overlay");

	}
/*
	@Override
	public void showDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.action_add_overlay, null);
		builder.setView(view);




		spinner = (Spinner) view.findViewById(R.id.spinner);

		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.action_array, android.R.layout.simple_spinner_item);
		spinner.setAdapter(adapter);

		builder.show();
	}
*/
	@Override
	public String getKeyId() {
		return db.getKeyActionId();
	}
}
