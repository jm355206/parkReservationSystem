package com.techelevator.campground.model;



public class Site {
	private Long site_id;
	private Long campground_id;
	private Long site_number;
	private Long max_occupancy;
	private boolean isAccessible;
	private Long max_rv_length;
	private boolean hasUtilities;
	private String accessibleYesNo;
	private String utilitiesYesNo;
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public Long getCampground_id() {
		return campground_id;
	}
	public void setCampground_id(Long campground_id) {
		this.campground_id = campground_id;
	}
	public Long getSite_number() {
		return site_number;
	}
	public void setSite_number(Long site_number) {
		this.site_number = site_number;
	}
	public Long getMax_occupancy() {
		return max_occupancy;
	}
	public void setMax_occupancy(Long max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	public Long getMax_rv_length() {
		return max_rv_length;
	}
	public void setMax_rv_length(Long max_rv_length) {
		this.max_rv_length = max_rv_length;
	}
	public boolean isHasUtilities() {
		return hasUtilities;
	}
	public void setHasUtilities(boolean hasUtilities) {
		this.hasUtilities = hasUtilities;
	}
	
	public String getAccessibleYesNo(){
		if(isAccessible) {
		    accessibleYesNo = "Yes";
		    } else {
		    accessibleYesNo = "No";
		    }
		    return accessibleYesNo;
	}

	public String getUtilitiesYesNo() {
		if(hasUtilities) {
		    utilitiesYesNo = "Yes";
		    }
		    else {
		    utilitiesYesNo = "No";
		    }
		    return utilitiesYesNo;
	}
	
}
