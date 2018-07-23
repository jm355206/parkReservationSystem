package com.techelevator.campground.model;

import java.time.LocalDate;

public interface ReservationDAO {
	/**
	 * Create reservations int the datastore.
	 * 
	 * @return  reservation_id as reservation objects in a List
	 */
    public Long createReservation(Long site_id, String name, LocalDate from_date, LocalDate to_date);
}
