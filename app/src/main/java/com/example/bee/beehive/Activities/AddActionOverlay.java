package com.example.bee.beehive.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bee.beehive.R;

import org.w3c.dom.Text;

import java.util.Date;

public class AddActionOverlay extends DialogFragment implements AdapterView.OnItemSelectedListener {


	String action_name;
	DatePicker datePicker;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.action_add_overlay, null);
		final Spinner spinner = (Spinner)  view.findViewById(R.id.spinner);
		datePicker = (DatePicker) view.findViewById(R.id.datePicker);

		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.action_array, R.layout.spinner_item);
		//adapter.setDropDownViewResource(R.layout.spinner_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

		builder.setView(view);


		builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//EditText input = (EditText) view.findViewById(R.id.ApiaryNameInput);

				//System.out.println(datePicker.getMonth());
				((ActionActivity) getActivity()).add(spinner.getSelectedItem().toString(), datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());

			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		Dialog dialog=builder.create();
		return dialog;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	//	TextView text = (TextView) view;
	//	action_name = position;
	//	Toast.makeText(getActivity(), "You Selected " + id, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
