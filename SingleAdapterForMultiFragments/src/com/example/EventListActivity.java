package com.example;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.example.domain.Datetime;
import com.example.domain.Event;

public class EventListActivity extends FragmentActivity {
	//Tags for fragments
	private static final String TODAY = "Today"; 
	private static final String TOMORROW = "Tomorrow"; 
	
	//Holds instance of Tab host used for tab
    protected TabHost mTabHost;
	
    //Hold the view pager adapter used for swipe functionality on tab contents
    private TabPagerAdapter adapterPager;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initilization
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		
		//Setup the tab
		mTabHost.setup();
		
		//Pager for swipe to change functionality on tabs
		final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		//Adapter for tab swipe
		adapterPager = new TabPagerAdapter(getSupportFragmentManager());
		//set the adapter on view pager
		viewPager.setAdapter(adapterPager);
		
		//Tab changes listener for tabs
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				mTabHost.setCurrentTabByTag(tabId);
				viewPager.setCurrentItem(mTabHost.getCurrentTab());
			}
		});
		
		//set the offscreen limit for pager
		viewPager.setOffscreenPageLimit(mTabHost.getChildCount());
		//page change listner on pager
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				mTabHost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
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
	
    /**
     * Add the tab into tab host
     * @param tag - Name of the tab
     * @param classFragment - fragment class used
     * @param fragment - fragment instance
     */
    @SuppressLint("InflateParams")
	private void addTabToTabHost(String tag, Class<?> classFragment, EventsFragment fragment) {
    	
    	adapterPager.fragments.add(fragment);
		adapterPager.notifyDataSetChanged();
		
		View tabview = LayoutInflater.from(mTabHost.getContext()).inflate(R.layout.tab_layout, null);
        TextView tv = (TextView) tabview.findViewById(R.id.tabsText);
        tv.setText(tag);

    	TabSpec tabSpec = mTabHost.newTabSpec(tag)
    			.setIndicator(tag)
    			.setIndicator(tabview)
    			.setContent(new TabContentFactory() {
					
					@Override
					public View createTabContent(String tag) {
						View v = new View(EventListActivity.this);
			            v.setMinimumWidth(0);
			            v.setMinimumHeight(0);
			            return v;
					}
				});

    	mTabHost.addTab(tabSpec);
	}
	
    /**
     * Tab pager adapter for setting fragment state on pager
     * @author Kunal Bhavsar
     */
    public static class TabPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<Fragment>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
