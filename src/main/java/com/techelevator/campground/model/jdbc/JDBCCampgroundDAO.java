package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO {

    
    private JdbcTemplate jdbcTemplate; // -Added -JM
    
    public JDBCCampgroundDAO(DataSource dataSource) { // Added this -JM
        this.jdbcTemplate = new JdbcTemplate(dataSource); // ??? why won't this take parameter
    }
    
    @Override
    public List<Campground> getAllCampgrounds(String park_name) { // Added this method -JM
        List <Campground> allCampgrounds = new ArrayList<>();
        String sqlGetAllCampgrounds = "SELECT * FROM campground JOIN park ON park.park_id = campground.park_id WHERE park.name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCampgrounds, park_name);
        while(results.next()) {
            Campground campgroundResult = mapRowToCampground(results);
            allCampgrounds.add(campgroundResult);
        }
        return allCampgrounds;
    }
    
    private Campground mapRowToCampground(SqlRowSet results) { // Added this mapping -JM
        Campground campground = new Campground();
        campground.setCampground_id(results.getLong("campground_id"));
        campground.setPark_id(results.getLong("park_id"));
        campground.setName(results.getString("name"));
        campground.setOpen_from(results.getInt("open_from_mm"));
        campground.setOpen_to(results.getInt("open_to_mm"));
        campground.setDaily_fee(results.getBigDecimal("daily_fee"));
        return campground;

    }

}