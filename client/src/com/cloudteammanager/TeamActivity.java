package com.cloudteammanager;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

public class TeamActivity extends Activity {
	private TabHost tabHost;
	private User user;
	private Team team;
	private List<User> teamMembers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);

		user = getIntent().getParcelableExtra("user");
		team = getIntent().getParcelableExtra("team");
		
		new SyncManager().getTeamMembers(
				this, 
				team.getId(), 
				new Pair<String, String>("Retrieving team members", "Retrieving team members..."), 
				new PostTask() {
					@Override
					public void run(Object obj) {
						teamMembers = (List<User>) obj;
					}
		});
		
		tabHost = (TabHost) findViewById(R.id.tab_host);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("Tasks");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Tasks");

		TabSpec spec2 = tabHost.newTabSpec("Meetups");
		spec2.setContent(R.id.tab2);
		spec2.setIndicator("Meetups");

		TabSpec spec3 = tabHost.newTabSpec("Participants");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("Participants");
		
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team, menu);
		return true;
	}

	public void addUser(View v) {
		// TODO: Need to make the keyboard open automatically in the dialog
		LayoutInflater li = LayoutInflater.from(this);
		View addTeamDialog = li.inflate(R.layout.add_user_dialog, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(addTeamDialog);

		final EditText userNameInput = (EditText) addTeamDialog.findViewById(R.id.userNameInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						new SyncManager().addMemberToTeam(
								TeamActivity.this, 
								team.getId(),
								userNameInput.getText().toString(), 
								new Pair<String, String>("Adding user to team", "Adding user to team..."), 
								new PostTask() {
									@Override
									public void run(Object obj) {
										teamMembers.add((User)obj);
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
}
