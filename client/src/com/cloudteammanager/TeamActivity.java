package com.cloudteammanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TeamActivity extends Activity {
	private TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);

		tabHost = (TabHost) findViewById(R.id.tab_host);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("TAB 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("TAB 1");

		TabSpec spec2 = tabHost.newTabSpec("TAB 2");
		spec2.setIndicator("TAB 2");
		spec2.setContent(R.id.tab2);

		TabSpec spec3 = tabHost.newTabSpec("TAB 3");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("TAB 3");
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

}
