package com.techelevator;

import java.awt.Choice;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
import javax.sql.DataSource;
import javax.tools.DocumentationTool.Location;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mockito.cglib.core.Local;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;

public class CampgroundCLI {
	//Start of Menu's
	private static final String PARK_OPTIONS_VIEW_CAMPGROUNDS = "View Campgrounds";
	private static final String PARK_OPTIONS_SEARCH_FOR_RESERVATION = "Search for a Reservation";
	private static final String PARK_OPTIONS_RETURN = "Return to previous screen";
	private static final String[] PARK_OPTIONS = new String[] { PARK_OPTIONS_VIEW_CAMPGROUNDS, 
																    PARK_OPTIONS_SEARCH_FOR_RESERVATION, 
																    PARK_OPTIONS_RETURN };
	
	private static final String CAMPGROUND_RESERVATION = "Search for Available Reservation";
	private static final String CAMPGROUNG_RETURN_TO_MENU = "Return to Park Selection";
	private static final String[] CAMPGROUND_OPTIONS = new String[] { CAMPGROUND_RESERVATION,  
																 	  CAMPGROUNG_RETURN_TO_MENU};
	
			
	// End of Menu's
			//DAO contains methods to read/write data we want, DAO needs to knwo the da
	private Menu menu;
	private JDBCParkDAO parkDAO; // read 4 different table so we need 4 DAO, each DAO has SQL statement which we
									// pass into JDBC database
	private JDBCSiteDAO siteDAO;// SAFE HERE SO EVERYBODY CAN ACCESS
	private JDBCCampgroundDAO campgroundDAO;
	private JDBCReservationDAO reservationDAO;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();// we need datasource(define where the data can be found)
		// and send it to jdbcDAO
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");//
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource dataSource) {
		this.menu = new Menu(System.in, System.out);

		campgroundDAO = new JDBCCampgroundDAO(dataSource);// IF WE DO THIS, DON'T NEED TOP PART
		siteDAO = new JDBCSiteDAO(dataSource);
		parkDAO = new JDBCParkDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);

	}

	public void run() {
		Scanner campScanner = new Scanner(System.in);
		displayApplicationBanner();
		String choice = (String) menu.getChoiceFromOptions(listOfParksForMenu());
		System.out.println();
		System.out.println("***You have chosen  " + choice + " National Park***");
		printOutParkInfo(choice);
		String choiceTwo = (String) menu.getChoiceFromOptions(PARK_OPTIONS);
		if (choiceTwo.equals(PARK_OPTIONS_VIEW_CAMPGROUNDS)) {
			viewCampgroundsByPark(choice);
		} else if (choiceTwo.equals(PARK_OPTIONS_SEARCH_FOR_RESERVATION)) {
			viewCampgroundsByPark(choice);
			///// Method for reservation process....
		}else {
			run();
		}
		choiceTwo = (String) menu.getChoiceFromOptions(CAMPGROUND_OPTIONS);
		if (choiceTwo.equals(CAMPGROUND_RESERVATION)) {
			printHeading("Campground Reservation Process");
				reservationProcess();

		}else {
			run();
		}
		System.out.println("all done");

	}

	public String[] listOfParksForMenu() {
		printHeading("Which Park Would You Like to View?");
		List<Park> allParkNames = parkDAO.getAllParks();
		int i = 0;
		String[] parkNamesString = new String[allParkNames.size()];
		for (Park currentPark : allParkNames) {
			parkNamesString[i] = currentPark.getName();
			i++;
		}
		return parkNamesString;
	}


private void printOutParkInfo(String parkName ) {
		printHeading("Park Information");
//find the park in the table with the same name as in park name
		Park parkInfo = new Park();   //listParkDetails(String choice);
		// if user choice is 1, we should display it
		parkInfo = parkDAO.listParkDetailsByParkName(parkName);
		  
		  	     	
		System.out.println(parkInfo.getName() + " National Park");
		System.out.println("Location:" + String.format("%11s", parkInfo.getLocation()));
		System.out.println("Established:"+ String.format("%11s", parkInfo.getEstablished_date()));
		System.out.println("Area:" + String.format("%11s",  parkInfo.getArea()));
		System.out.println("Annual Visitors:" +String.format("%11s", parkInfo.getVisitors()));
		int charCount = 0;
		for(String word: parkInfo.getDescription().split(" ")){
		  System.out.print(word+" ");
		  charCount+=word.length();
		  if(charCount>=55){
			  System.out.println();
			  charCount=0;
		  }}
	}

	
	private void viewCampgroundsByPark(String parkName) {
		printHeading(parkName + "National Park Campgrounds");
		System.out.println( "Id              Name          Open             Close     DailyFee");

		List <Campground> campgrounds = campgroundDAO.getAllCampgrounds(parkName);
		
		if(campgrounds.size() > 0) {
			for(Campground campList : campgrounds) {
				System.out.print(String.format("%-15s",campList.getCampground_id()));
				System.out.print(String.format("%-15s",campList.getName()));
				System.out.print(String.format("%-15s",campList.getOpen_month()));
				System.out.print(String.format("%-15s",campList.getClose_month()));
				System.out.print(String.format("%-15s\n",campList.getDaily_fee()));
			}}}

	private void reservationProcess() {
		Scanner campScanner = new Scanner(System.in);
		System.out.print("Please enter the ID for the Campground you would like to reserve (0 to return to main menu) ");
		String campgroundInput = campScanner.nextLine();
		Long length = Long.parseLong(campgroundInput);
		if (length == 0) {
			run();
		}
		System.out.print("What is the arrival date (enter in YYYY-MM-DD format)");
		String startInput = campScanner.nextLine();
		LocalDate startDate = LocalDate.parse(startInput);
		System.out.print("What is the departure date (enter in YYYY-MM-DD format) ");
		String departureInput = campScanner.nextLine();
		LocalDate endDate = LocalDate.parse(departureInput);
		listAvailableSites(length, startDate, endDate);
		System.out.print("Which site should be reserved?");
		String siteInput = campScanner.nextLine();
		Long siteInputLong = Long.parseLong(siteInput);
		System.out.print("What name should the reservation be made under?");
		String nameInput = campScanner.nextLine();
		createSiteReservation(nameInput, siteInputLong, startDate, endDate);
	}

	public void listAvailableSites(Long campgroundId, LocalDate reservationStart, LocalDate reservationEnd) {
		System.out.println( "Site No.    Max Occup.    Accessible   Max RV length     Utility");

		List<Site> availableSites = siteDAO.getAvailableSites(campgroundId, reservationStart, reservationEnd);
		if (availableSites.size() > 0) {
			for (Site siteList : availableSites) {
				System.out.print(String.format("%-15s",siteList.getSite_number()));
				System.out.print(String.format("%-15s",siteList.getMax_occupancy()));
				System.out.print(String.format("%-15s",siteList.getAccessibleYesNo()));
				System.out.print(String.format("%-15s",siteList.getMax_rv_length()));
				System.out.print(String.format("%-15s\n",siteList.getUtilitiesYesNo()));
//				System.out.println(siteList.getSite_number() + " " + siteList.getMax_occupancy() + " " + siteList.getAccessibleYesNo() + " "
//				+ siteList.getMax_rv_length() + " " + siteList.getUtilitiesYesNo());
//
//				//System.out.println(siteList.getSite_number() + " " + siteList.getMax_occupancy() + " " + siteList.getAccessibleYesNo() + " " + siteList.getMax_rv_length() + " " + siteList.getUtilitiesYesNo());
//				//System.out.println(siteList.getSite_number());
			}
		}
	}
	
	public void createSiteReservation(String name, Long siteId, LocalDate startDate, LocalDate endDate) {
		reservationDAO.createReservation(siteId, name, startDate, endDate);
	}

	private void printHeading(String headingText) { // JM added this
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	@SuppressWarnings("resource") // -JM added this
	private String getUserInput(String prompt) {
		System.out.print(prompt + " >>> ");
		return new Scanner(System.in).nextLine();
	}
	
	
	
	private void displayApplicationBanner() {
System.out.println("ÛÛÛÛÛÛ   ÛÛÛÛÛ   ÛÛÛÛÛÛÛÛÛ   ÛÛÛÛÛÛÛÛÛÛÛ ÛÛÛÛÛ    ÛÛÛÛÛÛÛ    ÛÛÛÛÛÛ   ÛÛÛÛÛ   ÛÛÛÛÛÛÛÛÛ   ÛÛÛÛÛ          ÛÛÛÛÛÛÛÛÛÛÛ    ÛÛÛÛÛÛÛÛÛ   ÛÛÛÛÛÛÛÛÛÛÛ   ÛÛÛÛÛ   ÛÛÛÛ");
System.out.println("°ÛÛÛÛÛÛ °°ÛÛÛ   ÛÛÛ°°°°°ÛÛÛ °Û°°°ÛÛÛ°°°Û°°ÛÛÛ   ÛÛÛ°°°°°ÛÛÛ °°ÛÛÛÛÛÛ °°ÛÛÛ   ÛÛÛ°°°°°ÛÛÛ °°ÛÛÛ          °°ÛÛÛ°°°°°ÛÛÛ  ÛÛÛ°°°°°ÛÛÛ °°ÛÛÛ°°°°°ÛÛÛ °°ÛÛÛ   ÛÛÛ° ");
System.out.println("°ÛÛÛ°ÛÛÛ °ÛÛÛ  °ÛÛÛ    °ÛÛÛ °   °ÛÛÛ  °  °ÛÛÛ  ÛÛÛ     °°ÛÛÛ °ÛÛÛ°ÛÛÛ °ÛÛÛ  °ÛÛÛ    °ÛÛÛ  °ÛÛÛ           °ÛÛÛ    °ÛÛÛ °ÛÛÛ    °ÛÛÛ  °ÛÛÛ    °ÛÛÛ  °ÛÛÛ  ÛÛÛ   ");
System.out.println("°ÛÛÛ°°ÛÛÛ°ÛÛÛ  °ÛÛÛÛÛÛÛÛÛÛÛ     °ÛÛÛ     °ÛÛÛ °ÛÛÛ      °ÛÛÛ °ÛÛÛ°°ÛÛÛ°ÛÛÛ  °ÛÛÛÛÛÛÛÛÛÛÛ  °ÛÛÛ           °ÛÛÛÛÛÛÛÛÛÛ  °ÛÛÛÛÛÛÛÛÛÛÛ  °ÛÛÛÛÛÛÛÛÛÛ   °ÛÛÛÛÛÛÛ   "); 
System.out.println("°ÛÛÛ °°ÛÛÛÛÛÛ  °ÛÛÛ°°°°°ÛÛÛ     °ÛÛÛ     °ÛÛÛ °ÛÛÛ      °ÛÛÛ °ÛÛÛ °°ÛÛÛÛÛÛ  °ÛÛÛ°°°°°ÛÛÛ  °ÛÛÛ           °ÛÛÛ°°°°°°   °ÛÛÛ°°°°°ÛÛÛ  °ÛÛÛ°°°°°ÛÛÛ  °ÛÛÛ°°ÛÛÛ   ");
System.out.println("°ÛÛÛ  °°ÛÛÛÛÛ  °ÛÛÛ    °ÛÛÛ     °ÛÛÛ     °ÛÛÛ °°ÛÛÛ     ÛÛÛ  °ÛÛÛ  °°ÛÛÛÛÛ  °ÛÛÛ    °ÛÛÛ  °ÛÛÛ      Û    °ÛÛÛ         °ÛÛÛ    °ÛÛÛ  °ÛÛÛ    °ÛÛÛ  °ÛÛÛ °°ÛÛÛ  ");
System.out.println("ÛÛÛÛÛ  °°ÛÛÛÛÛ ÛÛÛÛÛ   ÛÛÛÛÛ    ÛÛÛÛÛ    ÛÛÛÛÛ °°°ÛÛÛÛÛÛÛ°   ÛÛÛÛÛ  °°ÛÛÛÛÛ ÛÛÛÛÛ   ÛÛÛÛÛ ÛÛÛÛÛÛÛÛÛÛÛ    ÛÛÛÛÛ        ÛÛÛÛÛ   ÛÛÛÛÛ ÛÛÛÛÛ   ÛÛÛÛÛ ÛÛÛÛÛ °°ÛÛÛÛ");
System.out.println(" °°°°°    °°°°° °°°°°   °°°°°    °°°°°    °°°°°    °°°°°°°    °°°°°    °°°°° °°°°°   °°°°° °°°°°°°°°°°    °°°°°        °°°°°   °°°°° °°°°°   °°°°° °°°°°   °°°° ");
					     
	}
}
