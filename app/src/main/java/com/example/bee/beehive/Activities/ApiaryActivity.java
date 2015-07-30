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
import com.example.bee.beehive.ListFragment;
import com.example.bee.beehive.R;
import com.example.bee.beehive.dbListEntry;

import java.util.List;


public class ApiaryActivity extends ListItem {



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


    public List<dbListEntry> getData(int apiary_id, int hive_id)
    {
        return db.getAllApiaries();
        //return convertToListOfStrings(db.getAllApiaries());
    }

    public void deleteButton(View view) {
        System.out.println(view.getTag());
        db.deleteApiary(Integer.valueOf(view.getTag().toString()));
        cursorAdapter.changeCursor(getCursor(1, 1));
        //cursorAdapter.notifyDataSetChanged();
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
       // System.out.println();
       // return db.getHivesCursor(11);
        return db.getApiariesCursor();
    }

}
