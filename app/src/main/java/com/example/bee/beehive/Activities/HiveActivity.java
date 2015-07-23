package com.example.bee.beehive.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.Hive;
import com.example.bee.beehive.ListItems;
import com.example.bee.beehive.R;
import com.example.bee.beehive.dbListEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HiveActivity extends  ListItems {

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
        getMenuInflater().inflate(R.menu.menu_hive, menu);
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

    public List<String> getData(int apiary_id, int hive_id)
    {
        return convertToListOfStrings(db.getAllHives(apiary_id));

    }

    public String getColumnName()
    {
        return db.getHiveColumnName();
    }

    public Cursor getCursor(int i, int a)
    {
      //  long asd = getIntent().getLongExtra("current_apiary", -1);
      //  Toast.makeText(this, "" + asd, Toast.LENGTH_LONG).show();
        return db.getHivesCursor(getIntent().getIntExtra("apiary_id", -1));
    }

    public Class getGoToClass()
    {
        return ApiaryActivity.class;
    }

}
