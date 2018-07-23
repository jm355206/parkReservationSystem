package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO{
    
    private JdbcTemplate jdbcTemplate; // Added this - JM = JDBC template run the statement that
    //read/write the data statement which are in JDBC database
    
    public JDBCParkDAO(DataSource dataSource) { // Added this - JM
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Park> getAllParks() { // Added this - JM
        List<Park> parkNames = new ArrayList<>();
        String sqlGetAllParkNames = "Select * FROM park";
        
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParkNames);
        while(results.next()) {
            Park parkResult = mapRowToPark(results);
            parkNames.add(parkResult);
        }
        return parkNames;
        }
    
    public Park listParkDetailsByParkId(Long park_id) { // Added this -JM
    	Park park = new Park();
        String sqlListParkDetails = "SELECT * FROM park WHERE park_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlListParkDetails, park_id);
        while (results.next()) {
             park = mapRowToPark(results);
        }
        return park;
    }
    public Park listParkDetailsByParkName(String park_name) { // Added this -JM
    	Park park = new Park();
        String sqlListParkDetails = "SELECT * FROM park WHERE name = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlListParkDetails, park_name);
        while (results.next()) {
            park  = mapRowToPark(results);
        }
        return park;
    }
    
    private Park mapRowToPark(SqlRowSet results) { // Added this - JM
        Park park = new Park();
        park.setPark_id(results.getLong("park_id"));
        park.setName(results.getString("name"));
        park.setLocation(results.getString("location"));
        park.setEstablished_date(results.getDate("establish_date"));
        park.setArea(results.getLong("area"));
        park.setVisitors(results.getLong("visitors"));
        park.setDescription(results.getString("description"));
        return park;
    }
}