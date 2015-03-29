package com.example.domain;


public class Event {
	private String eventName;
	private Datetime datetime;
	
	public Event(String eventName, Datetime datetime) {
		this.eventName = eventName;
		this.datetime = datetime;
	}
	
	public Datetime getDatetime() {
		return datetime;
	}

	public void setDatetime(Datetime datetime) {
		this.datetime = datetime;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEvent_name(String eventName) {
		this.eventName = eventName;
	}
}

