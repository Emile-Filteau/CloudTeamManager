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
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

public class TeamsManagementActivity extends Activity {
	private User user;
	private List<Team> teams;
	private LinearLayout teamsLayout;
	private LinearLayout teamsDeleteLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teams_management);
		
		user = getIntent().getExtras().getParcelable("user");
		teams = new ArrayList<Team>();
		
		new SyncManager().getUserTeams(this, user.getId(), new Pair<String, String>("Loading teams", "Loading teams"), new PostTask() {
			@Override
			public void run(Object obj) {
				teamsLayout = (LinearLayout) findViewById(R.id.teamsLayout);
				teamsDeleteLayout = (LinearLayout) findViewById(R.id.teamsDeleteLayout);
				
				for (final Team team : (List<Team>) obj) {
					addTeam(team);			
				}
			}			
		});
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
						new SyncManager().createTeam(
								TeamsManagementActivity.this, 
								teamNameInput.getText().toString(), 
								user.getId(), 
								new Pair<String, String>("Creating team", "Creating team..."), 
								new PostTask() {
									@Override
									public void run(Object obj) {
										Team team = (Team) obj;
										
										addTeam(team);
										
										Intent i = new Intent(getApplicationContext(), TeamActivity.class);
										i.putExtra("team", team);
										i.putExtra("user", user);
										startActivity(i);
									}
						});
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
	
	private void addTeam(final Team team) {
		teams.add(team);
			
		Button button = new Button(this);
		button.setText(team.getName());
		
		teamsLayout.addView(button);
		
		Button deleteButton = new Button(this);
		deleteButton.setText("X");
		teamsDeleteLayout.addView(deleteButton);	
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), TeamActivity.class);
				i.putExtra("team", team);
				i.putExtra("user", user);
				startActivity(i);
			}				
		});
		
		deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}				
		});		
	}
}
