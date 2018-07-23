package com.techelevator.campground.model;

import java.util.Date;

public class Park {
	private Long park_id;
	private String name;
	private String location;
	private Date established_date;
	private Long area;
	private Long visitors;
	private String description;
	
	public Long getPark_id() {
		return park_id;
	}
	public void setPark_id(Long park_id) {
		this.park_id = park_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getEstablished_date() {
		return established_date;
	}
	public void setEstablished_date(Date established_date) {
		this.established_date = established_date;
	}
	public Long getArea() {
		return area;
	}
	public void setArea(Long area) {
		this.area = area;
	}
	public Long getVisitors() {
		return visitors;
	}
	public void setVisitors(Long visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
