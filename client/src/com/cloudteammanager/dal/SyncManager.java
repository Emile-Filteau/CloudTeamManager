package com.cloudteammanager.dal;

import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.cloudteammanager.dal.network.TeamDAO;
import com.cloudteammanager.dal.network.UserDAO;
import com.cloudteammanager.utils.Pair;
import com.cloudteammanager.utils.PostTask;

public class SyncManager {
	
	public SyncManager() {
	}
	
	public void authenticate(Activity activity, final String username, final String password, Pair<String, String> alertMessages, PostTask postTask) {
		Task task = new Task() {
			public void run() {
				User user = UserDAO.authenticate(username, password);
				setResult(user);
			}
		};
		execAsync(activity, task, postTask, alertMessages);
	}
	
	public void register(Activity activity, final String username, final String password, final String email, Pair<String, String> alertMessages, PostTask postTask) {
		Task task = new Task() {
			public void run() {
				User user = UserDAO.register(username, password, email);
				setResult(user);
			}
		};
		execAsync(activity, task, postTask, alertMessages);
	}
	
	public void getUserTeams(Activity activity, final Integer user_id, Pair<String, String> alertMessages, PostTask postTask) {
		Task task = new Task() {
			public void run() {
				List<Team> teams = TeamDAO.getUserTeams(user_id);
				setResult(teams);
			}
		};
		execAsync(activity, task, postTask, alertMessages);
	}
	
	public void createTeam(Activity activity, final String team_name, final Integer user_id, Pair<String, String> alertMessages, PostTask postTask) {
		Task task = new Task() {
			public void run() {
				Team team = TeamDAO.createTeam(team_name, user_id);
				setResult(team);
			}
		};
		execAsync(activity, task, postTask, alertMessages);
	}
	
	public void getTeamMembers(Activity activity, final Integer team_id, Pair<String, String> alertMessages, PostTask postTask) {
		Task task = new Task() {
			public void run() {
				List<User> users = TeamDAO.getTeamMembers(team_id);
				setResult(users);
			}
		};
		execAsync(activity, task, postTask, alertMessages);
	}
	
	private class Task implements Runnable {
		private Object result;
		public Object getResult() {
			return result;
		}
		public void setResult(Object result) {
			this.result = result;
		}
		@Override
		public void run() {}
	}
	
	/**
	 * Method called to execute an Async task in the manager (All Async task should be called here)
	 * @param task The task object to run asynchronously
	 * @param postTask The Post Task object to run after when the main task is done (can be null)
	 * @param alertMessages Messages to show when doing the task (if null, the task is invisible and doesn't stop main process)
	 */
	private void execAsync(final Activity activity, final Task task, final PostTask postTask, final Pair<String, String> alertMessages) {
		new AsyncTask<Void,Void,Void>() {
			ProgressDialog dialog;
			
			protected void onPreExecute() {
				if(alertMessages != null)
					dialog = ProgressDialog.show(activity, alertMessages.key, alertMessages.value, true);
		    }
			
			protected void onPostExecute(Void result) {
				if(alertMessages != null)
					dialog.dismiss();
				if(postTask != null)
					postTask.run(task.getResult());
			}

			@Override
			protected Void doInBackground(Void... arg0) {
				task.run();
				return null;
			}
		}.execute();
	}
}
