package com.example.bee.beehive;

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

		//	System.out.println(getActivity().getClass().getSimpleName().toString().equals(ApiaryActivity.class.getSimpleName().toString()));
			//if (getActivity().getClass().getSimpleName().toString().equals(ApiaryActivity.class.getSimpleName().toString()))
           		((ListItem) getActivity()).showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_item_scroll, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_item);

 /*       SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item,
                ((ListItem)getActivity()).getCursor(1,1),
                new String[] {((ListItem) getActivity()).getColumnName()},
                new int[] {R.id.list_item_textview}, 0);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ((ListItem) getActivity()).getGoToClass());
                intent.putExtra("apiary_id", (int) id);
                intent.putExtra("hive_id", -1);
                startActivity(intent);

            }
        });*/


        cursorAdapter = new CustomCursorAdapter(getActivity(), ((ListItem)getActivity()).getCursor(1,1), 0);
        listView.setAdapter(cursorAdapter);

		((ListItem)getActivity()).setCursorAdapter(cursorAdapter);

        return rootView;

    }

}
