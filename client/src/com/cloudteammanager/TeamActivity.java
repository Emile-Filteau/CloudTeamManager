package com.cloudteammanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TeamActivity extends Activity {
	private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);

		tabHost = (TabHost) findViewById(R.id.tabHost);
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
		// TODO: Logic to add users to a group
	}
}
