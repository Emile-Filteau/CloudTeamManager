package com.cloudteammanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;

public class TeamsManagementActivity extends Activity {
	private User user;
	private List<Team> teams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teams_management);
		
		user = getIntent().getExtras().getParcelable("user");
		
		// TODO: Populate the team names with a query
		teams = new ArrayList<Team>();
		
		teams.add(new Team(1, "Team 1", null));
		teams.add(new Team(2, "Team 2", null));
		teams.add(new Team(3, "Team 3", null));

		LinearLayout teamsLayout = (LinearLayout) findViewById(R.id.teamsLayout);

		for (final Team team : teams) {
			Button button = new Button(this);
			button.setText(team.getName());

			teamsLayout.addView(button);
			
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), TeamActivity.class);
					i.putExtra("team", team);
					startActivity(i);
				}				
			});			
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
						// TODO: Actually add the team in the database						
						Intent i = new Intent(getApplicationContext(), TeamActivity.class);
						i.putExtra("team", new Team(10, teamNameInput.getText().toString(), null));
						startActivity(i);
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
