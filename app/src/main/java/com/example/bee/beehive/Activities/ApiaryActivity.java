package com.example.bee.beehive.Activities;

import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bee.beehive.Apiary;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.R;
import com.example.bee.beehive.dbListEntry;

import java.util.List;


public class ApiaryActivity extends ListItems {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        db.addApiary(new Apiary("PORTO"));
        //db.deleteApiary(new Apiary(1,"PORTO"));
        db.addApiary(new Apiary("LISBOA"));
        List<dbListEntry> apiaries = db.getAllApiaries();
        for(dbListEntry a : apiaries) {
            System.out.println("GET ALL APIARIES ->>>>>>>>>>>>>>>>>> " + a.getName() + " -- " + a.getID());
        }
    }

    public void showDialog()
    {
        AddOverlay addOverlay = new AddOverlay();
        addOverlay.show(getFragmentManager(), "Add Overlay");

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
*/
  /*  @Override
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
*/
    public List<String> getData(int apiary_id, int hive_id)
    {

        return convertToListOfStrings(db.getAllApiaries());
    }

    public String getColumnName()
    {
        return db.getApiaryColumnName();
    }

	public Class getGoToClass()
	{
		return HiveActivity.class;
	}

    public Cursor getCursor(int apiary_id, int hive_id)
    {
        return db.getApiariesCursor();
    }

}
