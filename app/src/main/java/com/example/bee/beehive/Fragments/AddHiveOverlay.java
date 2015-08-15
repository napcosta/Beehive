package com.example.bee.beehive.Fragments;

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

import com.example.bee.beehive.Activities.HiveActivity;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.R;

public class AddHiveOverlay extends DialogFragment {

	int hive_number = -1;
	int honeycomb_count;
	int breedingcomb_count;
	Activity activity;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
		final LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.activity_add_hive_overlay, null);
		TextView overlayTitle = (TextView) view.findViewById(R.id.overlayAddApiary);
		builder.setTitle("Set hive properties");
		builder.setView(view);

		if (hive_number != -1) {
			EditText editNumberText = (EditText) view.findViewById(R.id.HiveNameInput);
			editNumberText.setText("" + hive_number);
			editNumberText.setSelection(editNumberText.getText().length());
		}

		builder.setPositiveButton((hive_number == -1) ? "Add" : "Change", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText hive_name = (EditText) view.findViewById(R.id.HiveNameInput);
				EditText honecomb_count = (EditText) view.findViewById(R.id.honeycombs);
				EditText breedingcomb_count = (EditText) view.findViewById(R.id.breedingcombs);

				((HiveActivity) getActivity()).add(
						Integer.parseInt(hive_name.getText().toString()),
						Integer.parseInt(honecomb_count.getText().toString()),
						Integer.parseInt(breedingcomb_count.getText().toString())
				);

			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		Dialog dialog=builder.create();
		return dialog;
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
		hive_number = db.getHiveNumber(id);

	}

}
