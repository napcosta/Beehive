package com.example.bee.beehive;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.bee.beehive.Activities.ApiaryActivity;
import com.example.bee.beehive.Activities.HiveActivity;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment {

    private List exampleListItemList;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHandler db = new DatabaseHandler(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_item_scroll, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_item);

        //Maybe this should be placed on each class
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item, ((ListItems)getActivity()).getCursor(1,1), new String[] {((ListItems) getActivity()).getColumnName() /*"apiary_name"*/}, new int[] {R.id.list_item_textview}, 0);

        listView.setAdapter(adapter);
/*
        mListAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item, // The name of the layout ID.
                        R.id.list_item_textview, // The ID of the textview to populate.
                        ((ListItems)getActivity()).getData(1,-1)); // [apiary_id][hive_id]

        View rootView = inflater.inflate(R.layout.fragment_item_scroll, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_item);
        listView.setAdapter(mListAdapter);*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ((ListItems) getActivity()).getGoToClass());
                intent.putExtra("apiary_id",(int) id);
                intent.putExtra("hive_id", -1);
                startActivity(intent);

             //   Toast.makeText(getActivity(), getActivity().getClass().getSimpleName(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
       // return inflater.inflate(R.layout.fragment_item_scroll, container, false);
    }
}
