package com.cloudteammanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.cloudteammanager.dal.SyncManager;
import com.cloudteammanager.dal.Task;
import com.cloudteammanager.dal.Team;
import com.cloudteammanager.dal.User;
import com.cloudteammanager.dal.calendar.Event;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

public class TeamActivity extends Activity {
	private TabHost tabHost;
	private User user;
	private Team team;
	private List<User> teamMembers;
	private List<Task> tasks;

	
	private LinearLayout usersLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);

		user = getIntent().getParcelableExtra("user");
		team = getIntent().getParcelableExtra("team");
		
		teamMembers = new ArrayList<User>();
		
		this.setTitle("Team : " + team.getName());
		
		tabHost = (TabHost) findViewById(R.id.tab_host);
		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("Tasks");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Tasks");

		TabSpec spec2 = tabHost.newTabSpec("Meetups");
		spec2.setContent(R.id.tab2);
		spec2.setIndicator("Meetings");

		TabSpec spec3 = tabHost.newTabSpec("Participants");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("Members");
		
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}
	
	private void fillContent() {
		new SyncManager().getTeamMembers(
				this, 
				team.getId(), 
				new Pair<String, String>("Loading team", "Retrieving team members..."), 
				new PostTask() {
					@Override
					public void run(Object obj) {
						usersLayout = (LinearLayout) findViewById(R.id.users_layout);
						usersLayout.removeAllViews();
						teamMembers.clear();
						
						for (User user : (List<User>) obj) {
							addTeamMember(user);
						}
					}
		});
		
		new SyncManager().getTeamTasks(
				this, 
				team.getId(), 
				new Pair<String, String>("Loading team", "Retrieving tasks..."), 
				new PostTask() {
					@Override
					public void run(Object obj) {
						tasks = (List<Task>) obj;
						LinearLayout taskLayout = (LinearLayout) findViewById(R.id.tasks_layout);
						taskLayout.removeAllViews();
						for (Task task : tasks) {
							TextView userView = new TextView(TeamActivity.this);
							userView.setLayoutParams(new LayoutParams(
						            LayoutParams.MATCH_PARENT,
						            LayoutParams.WRAP_CONTENT));
							userView.setText(" - " + task.getName() + " ( " + task.getMemberName() + " ) ");
							userView.setTextSize(24);
							userView.setPadding(20, 0, 0, 10);
							
							taskLayout.addView(userView);
						}
					}
		});
		
		new SyncManager().getTeamEvents(
				this, 
				team.getId(), 
				new Pair<String, String>("Generating availabilities", "Generating availabilities..."),
				new PostTask() {
					@Override
					public void run(Object obj) {
						List<Event> availabilities = generateAvailabilities((List<Event>) obj);
						
						LinearLayout meetupLayout = (LinearLayout) findViewById(R.id.meetups_layout);
						meetupLayout.removeAllViews();
						for (Event event : availabilities) {
							TextView userView = new TextView(TeamActivity.this);
							LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					           LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
					        params.weight = 1.0f;
					        params.gravity = Gravity.CENTER;

							userView.setLayoutParams(params);
							//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							SimpleDateFormat df = new SimpleDateFormat("E, d/M/yyyy HH:mm");
							
							String startDate = df.format(event.getStart_date());
							String endDate = df.format(event.getEnd_date());
								
							userView.setText(startDate + " -- " + endDate);
							
							userView.setTextSize(12);
							userView.setPadding(20, 0, 0, 10);
							
							meetupLayout.addView(userView);
						}
					}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.team, menu);
		return true;
	}

	public void addUser(View v) {
		// TODO: Need to make the keyboard open automatically in the dialog
		LayoutInflater li = LayoutInflater.from(this);
		View addTeamDialog = li.inflate(R.layout.add_user_dialog, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(addTeamDialog);

		final EditText userNameInput = (EditText) addTeamDialog.findViewById(R.id.userNameInput);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						new SyncManager().addMemberToTeam(
								TeamActivity.this, 
								team.getId(),
								userNameInput.getText().toString(), 
								new Pair<String, String>("Adding user to team", "Adding user to team..."), 
								new PostTask() {
									@Override
									public void run(Object obj) {
										if (obj == null) {
											new AlertDialog.Builder(TeamActivity.this)
									        .setIcon(android.R.drawable.ic_dialog_alert)
									        .setTitle("Invalid username")
									        .setMessage("The username you entered is invalid")
									        .setPositiveButton("OK", new DialogInterface.OnClickListener()
									         {
									        	public void onClick(DialogInterface arg0, int arg1) {
									        	}
									         }).show();
										}
										else {
											addTeamMember((User) obj);
										}
									}
						});
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
	
	private void addTeamMember(User user) {
		if (!teamMembers.contains(user)) {
			teamMembers.add(user);
			
			TextView userView = new TextView(TeamActivity.this);
			userView.setLayoutParams(
					new LayoutParams(
		            LayoutParams.MATCH_PARENT,
		            LayoutParams.WRAP_CONTENT));
			userView.setText(" - " + user.getUsername());
			userView.setTextSize(24);
			userView.setPadding(20, 0, 0, 10);
			
			usersLayout.addView(userView);
		}
		else {
			new AlertDialog.Builder(TeamActivity.this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("The user is already in the team")
	        .setMessage("The user is already in the team")
	        .setPositiveButton("OK", new DialogInterface.OnClickListener()
	         {
	        	public void onClick(DialogInterface arg0, int arg1) {
	        	}
	         }).show();
		}
		
	}
	
	public void createTask(View v) {
		Intent i = new Intent(getApplicationContext(), CreateTaskActivity.class);
		i.putExtra("team", team);
		i.putExtra("user", user);
		startActivity(i);
	}
	
	private List<Event> generateAvailabilities(List<Event> events) {
		List<Event> availableEvents = new ArrayList<Event>();
		
		for(Event event : events)
		{
			Log.d("Adam", event.toString());			
		}
		
		
		GregorianCalendar testDate = new GregorianCalendar();
		GregorianCalendar testDate2 = new GregorianCalendar();
		
		testDate.set(Calendar.HOUR_OF_DAY, 14);
		testDate2.set(Calendar.HOUR_OF_DAY, 16);
		
		events.add(new Event("Unavailable", (Date)testDate.getTime().clone(), (Date)testDate2.getTime().clone(), "Not during availability period", 1));
		
		GregorianCalendar startDate = new GregorianCalendar();
		GregorianCalendar endDate = new GregorianCalendar();
		
		startDate.set(Calendar.HOUR_OF_DAY, 0);
		startDate.set(Calendar.MINUTE, 0);
		
		long startMinutes = startDate.getTimeInMillis() / 60 / 1000;
		
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		endDate.set(Calendar.MINUTE, 0);
		
		events.add(new Event("Unavailable", (Date)startDate.getTime().clone(), (Date)endDate.getTime().clone(), "Not during availability period", 1));
		
		startDate.set(Calendar.HOUR_OF_DAY, 22);
		endDate.add(Calendar.DAY_OF_MONTH, 1);
		endDate.set(Calendar.HOUR_OF_DAY, 7);
		
		for(int i = 1; i < 20; i++) {
			events.add(new Event("Unavailable", (Date)startDate.getTime().clone(), (Date)endDate.getTime().clone(), "Not during availability period", 1));
			
			startDate.add(Calendar.DAY_OF_MONTH, 1);
			endDate.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		long endMinutes = endDate.getTimeInMillis() / 60 / 1000;
		
		Collections.sort(events);
		
		List<Long> minutes = new ArrayList<Long>();
		
		for(long i = startMinutes; i <= endMinutes; i++) {
			minutes.add(i);
		}
		
		for(Event event : events) {
			for (int i = minutes.size() - 1; i >= 0; i--) {
				if (minutes.get(i) >= event.getStart_date().getTime() / 60 / 1000 && minutes.get(i) <= event.getEnd_date().getTime() / 60 / 1000) {
					minutes.remove(i);
				}
			}
		}
		
		long startMinute = minutes.get(0);
		
		for (int i = 1; i < minutes.size(); i++) {
			if (minutes.get(i) > minutes.get(i - 1) + 1) {
				availableEvents.add(new Event("Available", new Date(startMinute * 60 * 1000), new Date(minutes.get(i - 1) * 60 * 1000), "Everyone is available", 1));
				startMinute = minutes.get(i);
			}			
		}
		
		availableEvents.add(new Event("Available", new Date(startMinute * 60 * 1000), new Date(minutes.get(minutes.size() - 1) * 60 * 1000), "Everyone is available", 1));
				
		return availableEvents;
	}
	
	protected void onResume() {
		super.onResume();
		fillContent();
	}
}
