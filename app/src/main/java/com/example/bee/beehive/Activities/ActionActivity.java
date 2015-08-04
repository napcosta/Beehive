package com.example.bee.beehive.Activities;

import android.database.Cursor;
import android.os.Bundle;
import com.example.bee.beehive.Action;
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

	public void add(String action_name)
	{
	//	System.out.println("Apiary: " + getIntent().getIntExtra("apiary_id", -1) + "Hive: " +  getIntent().getIntExtra("hive_id", -1));
		db.addAction(new Action(Integer.parseInt(action_name), getIntent().getIntExtra("apiary_id", -1), getIntent().getIntExtra("hive_id", -1)));
		cursorAdapter.changeCursor(getCursor(1, 1));
	}

	@Override
	public Class getGoToClass()
	{
		return null;
	}

	@Override
	public Cursor getCursor(int apiary_id, int hive_id) {
		return db.getActionsCursor(apiary_id, hive_id);
	}

	@Override
	public String getKeyName() {
		return db.getKeyActionName();
	}

	@Override
	public String getKeyId() {
		return db.getKeyActionId();
	}
}
