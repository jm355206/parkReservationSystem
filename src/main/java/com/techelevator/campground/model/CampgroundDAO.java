package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {
	/**
	 * Get all Campground from the datastore.
	 * 
	 * @return all Campground as Campground objects in a List
	 */
	
public List<Campground> getAllCampgrounds(String park_name);
	

}
