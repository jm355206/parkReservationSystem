package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {
	private Long campground_id;
	private Long park_id;
	private String name;
	private int open_from;
	private int open_to;
	private BigDecimal daily_fee;
	private String open_month;
	private String close_month;

	public Long getCampground_id() {
		return campground_id;
	}

	public void setCampground_id(Long campground_id) {
		this.campground_id = campground_id;
	}

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

	public int getOpen_from() {
		return open_from;
	}

	public void setOpen_from(int open_from) {
		this.open_from = open_from;
	}



	public int getOpen_to() {
		return open_to;
	}

	public void setOpen_to(int open_to) {
		this.open_to = open_to;
	}

	public BigDecimal getDaily_fee() {
		return daily_fee;
	}

	public void setDaily_fee(BigDecimal daily_fee) {
		this.daily_fee = daily_fee;
	}

	//@SuppressWarnings("unused") 
	public String getOpen_month() {
		       if (open_from == 1) {
			open_month = "January";
		} else if (open_from == 2) {
			open_month = "February";
		} else if (open_from == 3) {
			open_month = "March";
		} else if (open_from == 4) {
			open_month = "April";
		} else if (open_from == 5) {
			open_month = "May";
		} else if (open_from == 6) {
			open_month = "June";
		} else if (open_from == 7) {
			open_month = "July";
		} else if (open_from == 8) {
			open_month = "August";
		} else if (open_from == 9) {
			open_month = "September";
		} else if (open_from == 10) {
			open_month = "October";
		} else if (open_from == 11) {
			open_month = "November";
		} else if (open_from == 11) {
			open_month = "December";
		}
		return open_month;
	}
	public String getClose_month() {
	       if (open_to == 1) {
		close_month = "January";
	} else if (open_to == 2) {
		close_month = "February";
	} else if (open_to == 3) {
		close_month = "March";
	} else if (open_to == 4) {
		close_month = "April";
	} else if (open_to == 5) {
		close_month = "May";
	} else if (open_to == 6) {
		close_month = "June";
	} else if (open_to == 7) {
		close_month = "July";
	} else if (open_to == 8) {
		close_month = "August";
	} else if (open_to == 9) {
		close_month = "September";
	} else if (open_to == 10) {
		close_month = "October";
	} else if (open_to == 11) {
		close_month = "November";
	} else if (open_to == 12) {
		close_month = "December";
	}
	return close_month;
}

}
