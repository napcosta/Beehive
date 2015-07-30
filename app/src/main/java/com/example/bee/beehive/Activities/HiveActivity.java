package com.example.bee.beehive.Activities;

import android.content.Intent;
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


public class HiveActivity extends  ListItem {

    public DatabaseHandler db = new DatabaseHandler(this);
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);

        db.addHive(new Hive(1, 1));
        db.addHive(new Hive(2, 1));
        db.addHive(new Hive(2, 2));
        db.addHive(new Hive(3, 2));
        System.out.println("HIVE SIZE ----------->>>>>>>>> " + db.getAllHives(1).size());

        for(dbListEntry a : db.getAllHives(1)) {
            System.out.println("GET ALL HIVES ->>>>>>>>>>>>>>>>>> " + a.getName());
        }

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

    public List<dbListEntry> getData(int apiary_id, int hive_id)
    {
        return db.getAllApiaries();
        //return convertToListOfStrings(db.getAllHives(apiary_id));

    }

    public String getKeyName()
    {
        return db.getKeyHiveName();
    }

	public String getKeyId()
	{
		return db.getKeyHiveId();
	}

    public Cursor getCursor(int i, int a)
    {
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + getIntent().getIntExtra("apiary_id", -1));
        return db.getHivesCursor(getIntent().getIntExtra("apiary_id", -1));
    }

    public Class getGoToClass()
    {
        return ApiaryActivity.class;
    }

	public void deleteButton(View view)
	{
		//System.out.println("GET TAG -> " + view.getTag().toString());
		db.deleteHive(Integer.valueOf(view.getTag().toString()), getIntent().getIntExtra("apiary_id", -1));
		cursorAdapter.changeCursor(getCursor(1, 1));
	}

    public void add(String hive_name)
    {
		//System.out.println(getIntent().getIntExtra("apiary_id", -1));
		db.addHive(new Hive(Integer.parseInt(hive_name), getIntent().getIntExtra("apiary_id", -1)));
        cursorAdapter.changeCursor(getCursor(clicked_id, 1));
    }

}
