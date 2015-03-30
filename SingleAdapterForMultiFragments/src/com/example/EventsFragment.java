package com.example;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.EventsAdapter;
import com.example.domain.Event;

public class EventsFragment extends Fragment {
	private ArrayList<Event> events;
	private EventsAdapter eventsAdapter;
	
	public EventsFragment(ArrayList<Event> events) {
		super();
		this.events = events;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_event_list, container, false);
		
		ListView eventList = (ListView)rootView.findViewById(R.id.lst_adds);
		eventsAdapter = new EventsAdapter(getActivity(), events);
		eventList.setAdapter(eventsAdapter);
		
		eventList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Toast.makeText(getActivity().getApplicationContext(), 
						"You selected "+events.get(position).getEventName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		return rootView;
	}	
}
