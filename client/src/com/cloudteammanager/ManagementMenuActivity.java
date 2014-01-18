package com.cloudteammanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ManagementMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.management_menu, menu);
		return true;
	}

}
