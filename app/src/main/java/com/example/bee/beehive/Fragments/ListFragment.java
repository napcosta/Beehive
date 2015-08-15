package com.example.bee.beehive.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.bee.beehive.Activities.ApiaryActivity;
import com.example.bee.beehive.Activities.ListItem;
import com.example.bee.beehive.CustomCursorAdapter;
import com.example.bee.beehive.R;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment {

	public CustomCursorAdapter cursorAdapter;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        menu.clear();
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();
        if (id == R.id.action_add) {
            ((ListItem) getActivity()).popDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_item_scroll, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_item);

        cursorAdapter = new CustomCursorAdapter(getActivity(), ((ListItem)getActivity()).getCursor(1,1), 0);
        listView.setAdapter(cursorAdapter);

		((ListItem)getActivity()).setCursorAdapter(cursorAdapter);

        return rootView;

    }

}
