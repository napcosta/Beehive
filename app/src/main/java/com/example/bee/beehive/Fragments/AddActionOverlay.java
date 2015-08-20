package com.example.bee.beehive.Fragments;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bee.beehive.Action;
import com.example.bee.beehive.Activities.ActionActivity;
import com.example.bee.beehive.DatabaseHandler;
import com.example.bee.beehive.R;

import org.w3c.dom.Text;

import java.util.Date;

public class AddActionOverlay extends DialogFragment implements AdapterView.OnItemSelectedListener {


	String action_number;
	DatePicker datePicker;
	LinearLayout nucleus_layout;
	Action action = null;
	Activity activity;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
		final LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.action_add_overlay, null);
		final Spinner spinner = (Spinner)  view.findViewById(R.id.spinner);
		final EditText editText = (EditText) view.findViewById(R.id.nucleus_number);

		nucleus_layout = (LinearLayout) view.findViewById(R.id.nucleus_layout);


		datePicker = (DatePicker) view.findViewById(R.id.datePicker);

		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.action_array, R.layout.spinner_item);

		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		builder.setTitle(R.string.set_action_properties);
		builder.setView(view);

		if (action != null) {
			spinner.setSelection(Integer.valueOf(action.getName()));
			datePicker.updateDate(action.getYear(), action.getMonth()+1, action.getDay()); // Month indexes 0 for some reason...
			editText.setText(action.getTarget());
		}


		builder.setPositiveButton((action == null) ? getString(R.string.add) : getString(R.string.change), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

/*
				if (!editText.getText().toString().equals("")) {
					editText.setText("(to nucleus " + editText.getText().toString() + ")");
				}*/

				((ActionActivity) getActivity()).add(action_number, editText.getText().toString(), datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear());

			}
		}).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		Dialog dialog=builder.create();
		return dialog;
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

		if (id != 1) {
			nucleus_layout.setVisibility(View.GONE);
		} else {
			nucleus_layout.setVisibility(View.VISIBLE);
		}

		action_number = "" + id;

		//Toast.makeText(getActivity(), "You Selected " + id, Toast.LENGTH_SHORT).show();
	}

	//TODO: onAttach should go to a base fragment class
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		this.activity = activity;
	}

	public void setActionData(int id)
	{
		DatabaseHandler db = new DatabaseHandler(activity);

		action = db.getAction(id);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
