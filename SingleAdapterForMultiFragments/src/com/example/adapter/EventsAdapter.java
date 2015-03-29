package com.example.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.R;
import com.example.domain.Event;

public class EventsAdapter extends BaseAdapter {
	//Holds the source data of this adapter
	private ArrayList<Event> events;
	
	//Holds the context required for layout inflater
	private Context context;
	
	/**
	 * @author Kunal Bhavsar.
	 * Hold the views for this adapter
	 */
	class EventsAdapterViewholder {
		TextView eventName;
		TextView eventDate;
		TextView eventTime;
	}
	
	/**
	 * Parameterized constructor for getting context and source data required.
	 * @param context - Context required for Layout Inflater
	 * @param events - Source data
	 */
	public EventsAdapter(Context context, ArrayList<Event> events) {
		this.events = events;		
		this.context = context;
	}

	@Override
	public int getCount() {
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		EventsAdapterViewholder viewHolder;
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.list_item_event, null);
			viewHolder = new EventsAdapterViewholder();
			viewHolder.eventName = (TextView)convertView.findViewById(R.id.txt_event_name);
			viewHolder.eventTime = (TextView)convertView.findViewById(R.id.txt_event_time);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder = (EventsAdapterViewholder)convertView.getTag();
		}
		
		Event event = (Event)getItem(position);
		
		viewHolder.eventName.setText(event.getEventName());
		viewHolder.eventTime.setText(event.getDatetime().toString());
				
		return convertView;
	}
}
