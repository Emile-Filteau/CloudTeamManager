package com.cloudteammanager;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

public class CreateTaskActivity extends Activity {
	private Team team;
	private User user;
	private List<User> users;
	
	private Spinner memberSpinner;
	
	private SyncManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_task);
		manager = new SyncManager();
		user = getIntent().getParcelableExtra("user");
		team = getIntent().getParcelableExtra("team");
		manager.getTeamMembers(this, team.getId(), null, new PostTask() {
			public void run(Object obj) {
				memberSpinner = (Spinner)findViewById(R.id.create_task_memberSpinner);
				users = (List<User>)obj;
				String[] members = new String[users.size()];
				for(int i = 0;i<users.size();i++)
					members[i] = users.get(i).getUsername();
				
				ArrayAdapter<String> aa = new ArrayAdapter<String>(CreateTaskActivity.this, R.layout.member_row, R.id.memberNameTextView, members);
				memberSpinner.setAdapter(aa);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_task, menu);
		return true;
	}
	
	
	public void createTask(View v) {
		int pos = ((Spinner)findViewById(R.id.create_task_memberSpinner)).getSelectedItemPosition();
		int id = users.get(pos).getId();
		String name = ((EditText)findViewById(R.id.create_task_name)).getText().toString();
		int estimated_time = Integer.parseInt(((EditText)findViewById(R.id.create_task_estimated)).getText().toString());
		manager.createTask(this, team.getId(), name, estimated_time, id, 
				new Pair<String, String>("Task creation", "Creating task..."), 
				new PostTask() {
					public void run(Object obj) {
						finish();
					}
		});
	}
}
