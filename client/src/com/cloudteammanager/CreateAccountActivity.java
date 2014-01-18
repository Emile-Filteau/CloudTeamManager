package com.cloudteammanager;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_login, menu);
		return true;
	}

	public void createAccount(View v) {
		//  TODO: Add logic to actually create the account
		SyncManager manager = new SyncManager();
		String username = ((EditText)findViewById(R.id.create_account_username)).getText().toString();
		String password = ((EditText)findViewById(R.id.create_account_password)).getText().toString();
		String email = ((EditText)findViewById(R.id.create_account_email)).getText().toString();
		manager.register(this, username, password, email, 
				new Pair<String, String>("Registration", "registering account..."), 
				new PostTask() {
					public void run(Object obj) {
						Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();
						
						Intent i = new Intent(getApplicationContext(), ManagementMenuActivity.class);
						startActivity(i);
						finish();
					}
		});
		/*
		Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();
		
		Intent i = new Intent(getApplicationContext(), ManagementMenuActivity.class);
		startActivity(i);
		finish();*/
	}
}
