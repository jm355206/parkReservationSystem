package com.techelevator.campground.model.jdbc;

import java.time.LocalDate;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {
    
    private JdbcTemplate JdbcTemplate; // Added this - JM
    
    public JDBCSiteDAO(DataSource dataSource) { // Added this - JM
        this.JdbcTemplate = new JdbcTemplate(dataSource); // ??? Why won't this take parameters
    }

    @Override
    public List<Site> getAvailableSites(Long campground_id, LocalDate from_date, LocalDate to_date) { 
        List<Site> availableSites = new ArrayList<>();
              String sqlGetAvailableSites = "SELECT *" + 
                                        " FROM site" + 
                                        " JOIN campground" + 
                                            " ON site.campground_id = campground.campground_id" +
                                        " WHERE campground.campground_id = ? AND site.site_id NOT IN (SELECT site_id FROM reservation WHERE (from_date >= ?) AND (to_date <= ?)) LIMIT 5"; 
        SqlRowSet results = JdbcTemplate.queryForRowSet(sqlGetAvailableSites, campground_id, from_date, to_date);
        while(results.next()) {
            Site siteResult = mapRowToSite(results);
            availableSites.add(siteResult);
        }
        return availableSites;
    }
    
    private Site mapRowToSite(SqlRowSet results) { // Added this - JM
        Site site = new Site();
        site.setSite_id(results.getLong("site_number"));
        site.setCampground_id(results.getLong("campground_id"));
        site.setSite_number(results.getLong("site_number"));
        site.setMax_occupancy(results.getLong("max_occupancy"));
        site.setMax_rv_length(results.getLong("max_rv_length"));
        return site;
    }

}