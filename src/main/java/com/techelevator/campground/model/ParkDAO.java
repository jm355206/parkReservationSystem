package com.techelevator.campground.model;

import java.util.List;

public interface ParkDAO {
	/**
	 * Get all Park from the datastore.
	 * 
	 * @return all Parks as Park objects in a List
	 */
	
	public List<Park> getAllParks();
}
