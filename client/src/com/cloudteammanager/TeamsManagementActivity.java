package com.cloudteammanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TeamsManagementActivity extends Activity {
	private List<String> teamNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teams_management);

		// TODO: Populate the team names with a query
		teamNames = new ArrayList<String>();
		teamNames.add("Team 1");
		teamNames.add("Team 2");
		teamNames.add("Team 3");

		LinearLayout teamsLayout = (LinearLayout) findViewById(R.id.teamsLayout);

		for (String name : teamNames) {
			Button button = new Button(this);
			button.setText(name);

			teamsLayout.addView(button);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teams_management, menu);
		return true;
	}

	public void addTeam(View v) {
		// TODO: Need to make the keyboard open automatically in the dialog
		LayoutInflater li = LayoutInflater.from(this);
		View addTeamDialog = li.inflate(R.layout.add_team_dialog, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(addTeamDialog);

		final EditText teamNameInput = (EditText) addTeamDialog
				.findViewById(R.id.teamNameInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						teamNames.add(teamNameInput.getText().toString());
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
