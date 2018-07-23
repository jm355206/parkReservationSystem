package com.techelevator.campground.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	/**
	 * Get all sites from the datastore based on arrival date and departure date of the user.
	 * 
	 * @return all sites as site objects in a List which are available.
	 */
	public List<Site> getAvailableSites(Long campground_id,LocalDate from_date, LocalDate to_date );// id, start date, depart date
}
