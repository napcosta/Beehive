package com.example.bee.beehive.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import com.example.bee.beehive.Apiary;
import com.example.bee.beehive.Fragments.AddApiaryOverlay;
import com.example.bee.beehive.R;
import com.example.bee.beehive.dbListEntry;

import java.util.List;


public class ApiaryActivity extends ListItem {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiary);

        setTitle("Apiaries");

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
        AddApiaryOverlay addOverlay = new AddApiaryOverlay();
        addOverlay.show(getFragmentManager(), "Add Overlay");

    }

    public void deleteButton(View view) {
       // System.out.println("GET TAG -> " + view.getTag().toString());
        db.deleteApiary(Integer.valueOf(view.getTag().toString()));
        cursorAdapter.changeCursor(getCursor(1, 1));
    }

    public String getKeyName()
    {
        return db.getKeyApiaryName();
    }

    @Override
    public String getSubText(int apiary_id)
    {
        return db.getHiveCount(apiary_id) + " apiaries";
    }

    public String getKeyId()
	{
		return db.getKeyApiaryId();
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
