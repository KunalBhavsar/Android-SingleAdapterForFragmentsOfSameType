package com.example;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.domain.Datetime;
import com.example.domain.Event;

public class EventListActivity extends FragmentActivity implements TabHost.OnTabChangeListener {
	//Tags for fragments
	private static final String TODAY = "Today"; 
	private static final String TOMORROW = "Tomorrow"; 
	
	//Holds instance of Tab host used for tab
    protected TabHost mTabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initilization
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		
		//Setup the tab
		mTabHost.setup();
		
		//Tab changes listener for tabs
		mTabHost.setOnTabChangedListener(this);
		
		//Hold the data for 1st tab
		ArrayList<Event> todaysEvents = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Event event = new Event("EVENT "+(i+1), new Datetime("TODAY", i+1+".00 PM"));			
			todaysEvents.add(event);
		}

		//add first tab
		addTabToTabHost(TODAY, EventsFragment.class, new EventsFragment(todaysEvents));
		
		//Hold the data for 2nd tab
		ArrayList<Event> tommorowsEvents = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Event event = new Event("EVENT "+(i+1), new Datetime("TOMMOROW", i+1+".00 PM"));			
			tommorowsEvents.add(event);
		}
		//add second tab
		addTabToTabHost(TOMORROW, EventsFragment.class, new EventsFragment(tommorowsEvents));
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		default :
				return super.onOptionsItemSelected(item);
    	}
    }
    
    /**
     * Add the tab into tab host
     * @param tag - Name of the tab
     * @param classFragment - fragment class used
     * @param fragment - fragment instance
     */
    @SuppressLint("InflateParams")
	private void addTabToTabHost(String tag, Class<?> classFragment, EventsFragment fragment) {
    	
		View tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.tab_layout, null);
        TextView tv = (TextView) tabview.findViewById(R.id.tabsText);
        tv.setText(tag);

    	TabSpec tabSpec = mTabHost.newTabSpec(tag)
    			.setIndicator(tag)
    			.setIndicator(tabview)
    			.setContent(new TabFactory(EventListActivity.this));

    	mTabHost.addTab(tabSpec);
    	
    	Log.i("EventListActivity", "inside add tab to tab host ");
	}
	
    class TabFactory implements TabContentFactory {

        private final Context mContext;

        /**
         * @param context
         */
        public TabFactory(Context context) {
            mContext = context;
        }

        /** (non-Javadoc)
         * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
         */
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

	@Override
	public void onTabChanged(String tabId) {
		mTabHost.setCurrentTabByTag(tabId);
	}
}
