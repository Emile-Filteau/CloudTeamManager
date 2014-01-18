package com.cloudteammanager;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ManagementMenuActivity extends Activity {
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management_menu);
		
		user = (User)this.getIntent().getExtras().getParcelable("user");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.management_menu, menu);
		return true;
	}

	public void startCalendarManagement(View v) {
		Intent i = new Intent(getApplicationContext(), CalendarManagementActivity.class);
		i.putExtra("user", user);
		startActivity(i);
	}
	
	public void startTeamsManagement(View v) {
		Intent i = new Intent(getApplicationContext(), TeamsManagementActivity.class);
		i.putExtra("user", user);
		startActivity(i);
	}
}
