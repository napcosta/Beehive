package com.example.bee.beehive.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bee.beehive.ListItems;
import com.example.bee.beehive.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HiveActivity extends ActionBarActivity implements ListItems {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hive);
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

    public List<String> getData()
    {
        String[] data = {
                "001",
                "002",
                "003"
        };

        return new ArrayList<String>(Arrays.asList(data));
    }

    public Class getGoToClass()
    {
        return ApiaryActivity.class;
    }

}
