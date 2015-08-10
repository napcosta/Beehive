package com.example.bee.beehive.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.wifi.SupplicantState;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.bee.beehive.Apiary;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.Hive;
import com.example.bee.beehive.R;
import com.example.bee.beehive.dbListEntry;

import java.util.List;


public class HiveActivity extends ListItem {

    public DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);

        //setTitle(getIntent().getStringExtra("apiary_name"));
		setTitle(mSharedPreferences.getString("apiary_name", ""));
        db.addHive(new Hive(1, 1));
        db.addHive(new Hive(2, 1));
        db.addHive(new Hive(2, 2));
        db.addHive(new Hive(3, 2));

		//String restoredText = mSharedPreferences.getString("text", null);
		//if (restoredText != nu)
	//	System.out.println("Apiary -> " + mSharedPreferences.getInt("apiary_id", 0) + "Hive -> " + mSharedPreferences.getInt("hive_id", 0));

     //   System.out.println("HIVE SIZE ----------->>>>>>>>> " + db.getAllHives(1).size());
/*
        for(dbListEntry a : db.getAllHives(1)) {
            System.out.println("GET ALL HIVES ->>>>>>>>>>>>>>>>>> " + a.getName());
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getKeyName()
    {
        return db.getKeyHiveName();
    }

	@Override
	public String getSubText(int id) {
		String[] action = db.getNextAction(id);
        return action[1] + " scheduled on " + action[0];
	}

	public String getKeyId()
	{
		return db.getKeyHiveId();
	}

    public Cursor getCursor(int i, int a)
    {
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
		return db.getHivesCursor(mSharedPreferences.getInt("apiary_id", 0));
       // return db.getHivesCursor(getIntent().getIntExtra("apiary_id", -1));
    }

    public Class getGoToClass()
    {
        return ActionActivity.class;
    }

	public void deleteButton(View view)
	{
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
		db.deleteHive(Integer.valueOf(view.getTag().toString()), mSharedPreferences.getInt("apiary_id", 0));
		cursorAdapter.changeCursor(getCursor(1, 1));
	}

    public void add(String hive_name)
    {
		mSharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

		db.addHive(new Hive(Integer.parseInt(hive_name), mSharedPreferences.getInt("apiary_id", 0)));
        cursorAdapter.changeCursor(getCursor(clicked_id, 1));
    }

}
