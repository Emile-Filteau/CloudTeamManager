package com.cloudteammanager.listAdapter;

import java.util.ArrayList;
import java.util.List;

import com.cloudteammanager.dal.Task;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TaskListAdapter extends BaseAdapter {
 
    private Activity activity;
    private ArrayList<Task> data;
    private List<View> viewList;
    private LayoutInflater inflater = null;
 
    private long selected_id;
    
    public TaskListAdapter(Activity act, ArrayList<Task> data) {
        this.activity = act;
        this.data = data;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewList = new ArrayList<View>();
    }
 
    public int getCount() {
        return data.size();
    }
 
    public Task getItem(int position) {
        return data.get(position);
    }
 
    public long getItemId(int position) {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;/*
        if(convertView==null)
            vi = inflater.inflate(R.layout.player_row, null);

        TextView username = (TextView)vi.findViewById(R.id.usernameTextView);
        TextView status = (TextView)vi.findViewById(R.id.statusTextView);
        TextView owner = (TextView)vi.findViewById(R.id.ownerTextView);
 
        Player p = data.get(position);

        username.setText(p.getUsername());
        username.setTextColor(activity.getResources().getColor(R.color.yellow));
        
    	if(p.getId() == selected_id) {
    		username.setTextColor(activity.getResources().getColor(R.color.green));
    	}
        
        String statusText = activity.getString(R.string.state_players_statusDisconnected);
        if(ctx.getPlayerDeviceCollection().getPlayerById(p.getId()) != null) {
        	statusText = activity.getString(R.string.state_players_statusConnected);
	 	}
        status.setText(statusText);

        String ownerText = "";
        if(p.isOwner())
        	ownerText = activity.getString(R.string.state_players_statusOwner);
        owner.setText(ownerText);
        viewList.add(vi);*/
        return vi;
    }
}