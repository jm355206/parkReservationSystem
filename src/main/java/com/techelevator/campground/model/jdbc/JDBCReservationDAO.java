package com.techelevator.campground.model.jdbc;

import java.sql.Date;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.mockito.cglib.core.Local;
import org.omg.CosNaming._BindingIteratorImplBase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {

    private JdbcTemplate JdbcTemplate; // Added this - JM
    
    public JDBCReservationDAO(DataSource dataSource) { // Added this - JM
        this.JdbcTemplate = new JdbcTemplate(dataSource); // ??? Why won't this take parameters
    }
	
	
    @Override
    public Long createReservation(Long site_id, String name, LocalDate from_date, LocalDate to_date) {
    	String sqlCreateReservation = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date)" +
    								 	" VALUES(?, ?, ?, ?, now()) RETURNING reservation_id";
    	Long newId = JdbcTemplate.queryForObject(sqlCreateReservation, Long.class, site_id, name, from_date, to_date);
    System.out.println();
    	System.out.println("Thanks for your reservation. Your reservation ID is " + newId);
    	return newId;
    	}
    
    private Reservation mapRowToReservation(SqlRowSet results) { // Added this - JM
        Reservation reservation = new Reservation();
        reservation.setReservation_id(results.getLong("reservation_id"));
        reservation.setSite_id(results.getLong("site_id"));
        reservation.setName(results.getString("name"));
        reservation.setFrom_date(results.getDate("from_date").toLocalDate());
        reservation.setTo_date(results.getDate("to_date").toLocalDate());
        reservation.setCreate_date(results.getDate("create_date").toLocalDate());
        return reservation;
    }


}



