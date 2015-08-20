package com.bee.beehive.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bee.beehive.Activities.HiveActivity;
import com.bee.beehive.DatabaseHandler;
import com.bee.beehive.Hive;
import com.bee.beehive.R;

public class AddHiveOverlay extends DialogFragment {

	Hive hive = null;
	Activity activity;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
		final LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.activity_add_hive_overlay, null);
		TextView overlayTitle = (TextView) view.findViewById(R.id.overlayAddApiary);
		builder.setTitle(getString(R.string.set_hive_properties));
		builder.setView(view);

		if (hive != null) {
			setEditText((EditText)view.findViewById(R.id.HiveNameInput), hive.getName());
			setEditText((EditText)view.findViewById(R.id.honeycombs), hive.getHoneycombCount());
			setEditText((EditText)view.findViewById(R.id.breedingcombs), hive.getBreedingcombCount());
		}

		builder.setPositiveButton((hive == null) ? getString(R.string.add) : getString(R.string.change), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText hive_name = (EditText) view.findViewById(R.id.HiveNameInput);
				EditText honeycomb_count = (EditText) view.findViewById(R.id.honeycombs);
				EditText breedingcomb_count = (EditText) view.findViewById(R.id.breedingcombs);


				if (hive == null) {
					((HiveActivity) getActivity()).add(
							hive_name.getText().toString(),
							honeycomb_count.getText().toString(),
							breedingcomb_count.getText().toString()
					);
				} else {
					((HiveActivity) getActivity()).changeHiveProperties(
							hive.getID(),
							hive_name.getText().toString(),
							honeycomb_count.getText().toString(),
							breedingcomb_count.getText().toString());
				}

			}
		}).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		Dialog dialog=builder.create();
		return dialog;
	}

	private void setEditText(EditText editText, String text)
	{
		editText.setText(text);
		editText.setSelection(editText.getText().length());
	}

	//TODO: onAttach should go to a base fragment class
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		this.activity = activity;
	}

	public void setHiveData(int id)
	{
		DatabaseHandler db = new DatabaseHandler(activity);
	//	System.out.println(activity);

		hive = db.getHive(id);
		//hive_number = db.getHiveNumber(id);


	}

}
