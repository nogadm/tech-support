package tech_support;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


import techSupport.Form;
import utils.DataBase;
import utils.Indicator;

public class DataBaseTests {
	
	private static Form testForm;
	private static String response;
	private static DataBase db;
	private static int formID;

	@Before
	public void initializeFormAndResponse() {
		// Create a test form
		testForm = new Form(1, "Test problem description.", "TEST-SERIAL-NUMBER-TEST-SERIAL-NUMBER-TEST-SERIAL-NUMBER-TEST-00");
		// ArrayList of 3 light indicators
    	ArrayList<Indicator> status = new ArrayList<>();
        status.add(Indicator.off);
		status.add(Indicator.on);
		status.add(Indicator.blinking);
		testForm.setLightsStatus(status);
		
		// Test response
		response = "this is a test response";
		
		// Instance of DataBase
		db = new DataBase();
		
		// Save the test data in the database
		db.saveDataToDB(testForm, response);
	}
	
	// Test if the data is saved correctly in the mysql database
	@Test
	public void testSaveDataToDB() throws SQLException {     
		// Test that the test form is equal to the last row in the database
		Form tableForm = new Form();
		tableForm = getLastestRowOfFormTbl();
		assertEquals(testForm, tableForm);
		
		// Test that the form saved in the data base equals to the test response
		assertEquals(response, getLastestRowOfFormResultTbl(formID));	
    }
	
	
	 // Helper method that returns the last row from FormTbl in the database as a Form object
	 public Form getLastestRowOfFormTbl() throws SQLException {
		 	// Connect to the database
		    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechSupport", "username", "password");
		    
		    // Get last row of FormTbl
		    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FormTbl ORDER BY formID DESC LIMIT 1");
		    ResultSet rs = stmt.executeQuery();

		    // Check if there's at least one row returned by the query
		    if (rs.next()) {
		        // Map the columns to the properties of the Form object
		        Form form = new Form();
		        formID = rs.getInt("formID");
		        form.setUserID(rs.getInt("userID"));
		        form.setProblemDescription(rs.getString("ProblemDescription"));
		        form.setDeviceSerialNumber(rs.getString("SerialNum"));

		        // Create an ArrayList for the lightsStatus indicators
		        ArrayList<utils.Indicator> lightsStatus = new ArrayList<>();
		        if (rs.getString("light1") != null) {
		            lightsStatus.add(utils.Indicator.valueOf(rs.getString("light1")));
		        }
		        if (rs.getString("light2") != null) {
		            lightsStatus.add(utils.Indicator.valueOf(rs.getString("light2")));
		        }
		        if (rs.getString("light3") != null) {
		            lightsStatus.add(utils.Indicator.valueOf(rs.getString("light3")));
		        }
		        form.setLightsStatus(lightsStatus);

		        // Close the database connection
		        conn.close();
		        return form;
		    } else {
		        // If no rows were returned
		        conn.close();
		        return null;
		    }
		}
	 

	 // Helper method that returns the last row from FormResultTbl in the database as a Response object
	 public String getLastestRowOfFormResultTbl(int formID) throws SQLException {
			// Connect to the database
		    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechSupport", "username", "password");
		    
		    // Get last row of FormResultTbl
		    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FormResultTbl WHERE formID = ? ORDER BY formID DESC, currentDatetime DESC LIMIT 1");
		    stmt.setInt(1, formID);
		    ResultSet rs = stmt.executeQuery();

		    // Check if there's at least one row returned by the query
		    if (rs.next()) {
		    	String responseFromTable = rs.getString("responseStatus");
		    	conn.close();
		    	return responseFromTable;
		    } else {
		        // If no rows were returned, close the database connection and return null
		        conn.close();
		        return null;
		    }
		}

}
