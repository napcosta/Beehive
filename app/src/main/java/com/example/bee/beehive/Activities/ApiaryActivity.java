package com.example.bee.beehive.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.bee.beehive.Apiary;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.ListItems;
import com.example.bee.beehive.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ApiaryActivity extends ActionBarActivity implements ListItems {

    public DatabaseHandler db = new DatabaseHandler(this);
    //db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        db.addApiary(new Apiary("PORTO"));

        List<Apiary> apiaries = db.getAllApiaries();
        for(Apiary a : apiaries) {
            System.out.println("GET ALL APIARIES ->>>>>>>>>>>>>>>>>> " + a.getName());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apiary, menu);
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
        List<String> str = convertToListOfStrings();
        //return new ArrayList<String>(Arrays.asList(data));
        return convertToListOfStrings();
    }

    private List<String> convertToListOfStrings()
    {
        ArrayList<String> strings = new ArrayList<String>();

       // List<Apiary> apiaries = db.getAllApiaries();

        for(Apiary a : db.getAllApiaries()) {
            strings.add(a != null ? a.getName() : null);
        }
        return strings;
    }

}
