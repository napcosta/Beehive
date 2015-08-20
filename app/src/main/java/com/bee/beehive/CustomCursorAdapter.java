package com.bee.beehive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bee.beehive.Activities.ActionActivity;
import com.bee.beehive.Activities.ApiaryActivity;
import com.bee.beehive.Activities.HiveActivity;
import com.bee.beehive.Activities.ListItem;

import org.w3c.dom.Text;


public class CustomCursorAdapter extends CursorAdapter {

	private LayoutInflater mInflater;

	Context activity;
	SharedPreferences.Editor mPreferencesEditor;

	public static final String PREFERENCES = "preferences";

	public CustomCursorAdapter(Context context, Cursor c, int flags) {
		super(context, c, flags);
		activity = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPreferencesEditor = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE).edit();
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return mInflater.inflate(R.layout.list_item, parent, false);
	}

	private String actionNumberToString(Context context, int i)
	{
		String action;

		switch(i) {
			case 0:
				action = context.getString(R.string.action_feed);
				break;
			case 1:
				action = context.getString(R.string.action_split);
				break;
			case 2:
				action = context.getString(R.string.action_harvest);
				break;
			default:
				action = "whatevs";
				break;
		}
		return action;

	}

	@Override
	public void bindView(View view, final Context context, Cursor cursor) {

		final String activity_name = context.getClass().getSimpleName();
		TextView subTextView = (TextView) view.findViewById(R.id.subText);
		final TextView textView = (TextView) view.findViewById(R.id.list_item_textview);
		final int item_id = (int) cursor.getLong(cursor.getColumnIndex(((ListItem) context).getKeyId()));

		if (activity_name.equals(ActionActivity.class.getSimpleName())) {
			textView.setText(actionNumberToString(context, Integer.valueOf(cursor.getString(cursor.getColumnIndex(((ListItem) context).getKeyName())))));
		} else {
			textView.setText(cursor.getString(cursor.getColumnIndex(((ListItem) context).getKeyName())));
		}
		subTextView.setText(((ListItem) context).getSubText(item_id));
		final String item_name = textView.getText().toString();

		ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteButton);
		deleteButton.setTag(item_id);



		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(activity, ((ListItem) activity).getGoToClass());

				if(activity_name.equals(ApiaryActivity.class.getSimpleName())) {
					mPreferencesEditor.putInt("apiary_id", item_id);
					mPreferencesEditor.putString("apiary_name",item_name);
				} else if (activity_name.equals(HiveActivity.class.getSimpleName())) {
					mPreferencesEditor.putInt("hive_id", item_id);
					mPreferencesEditor.putString("hive_name", item_name);
				} else if (activity_name.equals(ActionActivity.class.getSimpleName())) {
					mPreferencesEditor.putInt("action_id", item_id);
				}

				mPreferencesEditor.commit();
				activity.startActivity(intent);
			}
		});

		view.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View arg0) {

				if(activity_name.equals(ApiaryActivity.class.getSimpleName())) {
					((ApiaryActivity) context).popDialog(item_name, item_id); //TODO: should have the same approach as the other activities
//					Toast.makeText(context, ""+context.getSharedPreferences(PREFERENCES, context.MODE_PRIVATE).getInt("apiary_id", 0), Toast.LENGTH_SHORT).show();
				} else if (activity_name.equals(HiveActivity.class.getSimpleName())) {
					((HiveActivity) context).popDialog(item_id);
				} else if (activity_name.equals(ActionActivity.class.getSimpleName())) {
					((ActionActivity) context).popDialog(item_id);
				}

				//Toast.makeText(context, ""+item_id, Toast.LENGTH_SHORT).show();
				return true;
			}
		});
	}

}