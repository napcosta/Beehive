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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);


        DatabaseHandler db = new DatabaseHandler(this);
     //   db.addApiary(new Apiary("Lisboa"));
        List<Apiary> apiaries = db.getAllApiaries();
        for(Apiary a : apiaries) {
            System.out.println("GET ALL APIARIES ->>>>>>>>>>>>>>>>>> " + a.getName());
        }
     //   System.out.println("GET ALL APIARIES ->>>>>>>>>>>>>>>>>> " + db.getAllApiaries().size());
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
        String[] data = {
                "Braga",
                "Setubal",
                "Alentejo"
        };

        return new ArrayList<String>(Arrays.asList(data));
    }

}
