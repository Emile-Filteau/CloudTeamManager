package com.cloudteammanager;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.utils.PostTask;

public class CreateTaskActivity extends Activity {
	private Team team;
	private User user;
	
	private Spinner memberSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_task);
		
		user = getIntent().getParcelableExtra("user");
		team = getIntent().getParcelableExtra("team");
		new SyncManager().getTeamMembers(this, team.getId(), null, new PostTask() {
			public void run(Object obj) {
				memberSpinner = (Spinner)findViewById(R.id.create_task_memberSpinner);
				List<User> users = (List<User>)obj;
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

}
