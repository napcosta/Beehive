package com.example.bee.beehive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bee.beehive.Activities.ListItem;

import java.util.List;


public class CustomCursorAdapter extends CursorAdapter {

	private LayoutInflater mInflater;

	Context activity;

	public CustomCursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		activity = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.list_item, parent, false);
	}


	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final int id = (int) cursor.getLong(cursor.getColumnIndex(((ListItem) context).getKeyId()));
		TextView textView = (TextView) view.findViewById(R.id.list_item_textview);
		textView.setText(cursor.getString(cursor.getColumnIndex(((ListItem) context).getKeyName())));
		ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
		deleteButton.setTag(id);

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println(id);
				((ListItem) activity).setClickedId(id);
				Intent intent = new Intent(activity, ((ListItem) activity).getGoToClass());
				intent.putExtra("apiary_id", id);
				intent.putExtra("hive_id", -1);
				activity.startActivity(intent);
			}
		});
	}


	static class ListItemHolder
	{
		ImageButton deleteButton;
		TextView textTitle;
	}


}



















/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bee.beehive.Activities.ListItems;

import java.util.List;

public class BeehiveAdapter extends BaseAdapter {
	private List<String> items;
	private LayoutInflater inflater;
	Context context;

	public BeehiveAdapter(Context c, List<String> items) {
		this.items = items;
		inflater = LayoutInflater.from(c);
		context = c;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int location) {
	//	return items[location];
		return items.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = inflater.inflate(R.layout.list_item, null);
		TextView txt = (TextView) convertView.findViewById(R.id.list_item_textview);

		ImageButton button = (ImageButton) convertView.findViewById(R.id.deleteButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "clicked ",
						Toast.LENGTH_SHORT).show();
			}
		});
		txt.setText("" + items.get(position) );

		return convertView;
	}
}*/




















/*class BeehiveAdapter extends ArrayAdapter {

	Context context;
	int layoutResourceId;
	List<dbListEntry> items = null;
	LayoutInflater inflater;

	public BeehiveAdapter(Context context, int layoutResourceId,  List<dbListEntry> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		ListItemHolder listItemholder = null;

		if (row == null) {
			row = inflater.inflate(R.layout.list_item, parent, false);
			listItemholder = new ListItemHolder();
			listItemholder.deleteButton = (ImageButton)row.findViewById(R.id.deleteButton);
			listItemholder.textTitle = (TextView)row.findViewById(R.id.list_item_textview);

			row.setTag(listItemholder);

		} else {
			listItemholder = (ListItemHolder)row.getTag();
		}


		dbListEntry item = items.get(position);
		listItemholder.textTitle.setText(item.getName());

		row.findViewById(R.id.list_item_textview).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ((ListItem)  context).getGoToClass());
				intent.putExtra("apiary_id", (int) 1);
				intent.putExtra("hive_id", -1);
				context.startActivity(intent);
			}
		});

		ImageButton button = (ImageButton) row.findViewById(R.id.deleteButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "clicked ",
						Toast.LENGTH_SHORT).show();
			}
		});

		return row;
	}

	static class ListItemHolder
	{
		ImageButton deleteButton;
		TextView textTitle;
	}
}
*/