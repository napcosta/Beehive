package com.example.bee.beehive;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment {

    ArrayAdapter<String> mListAdapter;

    private List exampleListItemList;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        mListAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item, // The name of the layout ID.
                        R.id.list_item_textview, // The ID of the textview to populate.
                        ((ListItems)getActivity()).getData());

        View rootView = inflater.inflate(R.layout.fragment_item_scroll, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_item);
        listView.setAdapter(mListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Hive.class); //Cada activity deve saber para que activity deve ir. Tenho que actualizar a interface.
                startActivity(intent);
            }
        });

        return rootView;
       // return inflater.inflate(R.layout.fragment_item_scroll, container, false);
    }
}
