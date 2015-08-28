package com.bee.beehive.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.bee.beehive.Activities.ListItem;
import com.bee.beehive.Activities.SettingsActivity;
import com.bee.beehive.CustomCursorAdapter;
import com.bee.beehive.R;


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
//		Toast.makeText(getActivity(), "HERE", Toast.LENGTH_LONG).show();
        if (id == R.id.action_add) {
            ((ListItem) getActivity()).popDialog();
            return true;
        } else if (id == R.id.action_settings) {
            Intent i = new Intent(getActivity(), SettingsActivity.class);
            startActivity(i);
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
