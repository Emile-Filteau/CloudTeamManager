package com.cloudteammanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void connect(View v) {
		EditText usernameTxt = (EditText) findViewById(R.id.main_login_username);
		EditText passwordTxt = (EditText) findViewById(R.id.main_login_password);

		// TODO: Add verification for the username / password combo before starting the next activity
		Intent i = new Intent(getApplicationContext(), ManagementMenuActivity.class);
		startActivity(i);
	}
}
