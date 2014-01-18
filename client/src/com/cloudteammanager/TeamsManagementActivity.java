package com.cloudteammanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TeamsManagementActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teams_management);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.teams_management, menu);
		return true;
	}

}
