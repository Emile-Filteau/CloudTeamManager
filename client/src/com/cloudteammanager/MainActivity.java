package com.cloudteammanager;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.dal.network.UserDAO;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
		// get EditText by id
		EditText username = (EditText) findViewById(R.id.main_login_username);
		EditText password = (EditText) findViewById(R.id.main_login_password);
		
		SyncManager manager = new SyncManager();
		manager.authenticate(this, username.getText().toString(), password.getText().toString(), 
				new Pair<String, String>("Authentication", "logging in..."), 
				new PostTask() {
					public void run(Object obj) {
						User user = ((User)obj);
						if(user != null) {
							Intent i = new Intent(getApplicationContext(), ManagementMenuActivity.class);
							i.putExtra("user", user);
							startActivity(i);
							Intent i = new Intent(getApplicationContext(), ManagementMenuActivity.class);
							startActivity(i);
						} else {
							new AlertDialog.Builder(MainActivity.this)
					        .setIcon(android.R.drawable.ic_dialog_alert)
					        .setTitle("Authentification fail")
					        .setMessage("bad username or password")
					        .setPositiveButton("OK", new DialogInterface.OnClickListener()
					         {
					        	public void onClick(DialogInterface arg0, int arg1) {
					        	}
					         }).show();
						}
					}
				}
		);
		
		//UserDAO.authenticate(username.getText().toString(), password.getText().toString());
	}
	
	public void createNewAccount(View v) {
		Intent i = new Intent(getApplicationContext(), CreateAccountActivity.class);
		startActivity(i);
	}

}
