package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import techSupport.Form;

public class DataBase {
   
    public void saveDataToDB(Form form, String response) {
        Connection conn = null;
        PreparedStatement formTblStatment = null;
        ResultSet rs = null;
        int formID; 
        
        try {
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechSupport", "username", "password");
            System.out.println("Connected to TechSupport database");

            // Execute insert query to FormTbl
            formTblStatment = conn.prepareStatement(prepareInsertToFormTbl(form), Statement.RETURN_GENERATED_KEYS);
            int rowsInsertedToFormTbl = formTblStatment.executeUpdate();
            
            // Check if a new row was added to the table
            if (rowsInsertedToFormTbl > 0) {
                System.out.println(rowsInsertedToFormTbl + " rows inserted successfully to FormTbl.");
                
                // Get the generated formID since it is a AUTO_INCREMENT field
                rs = formTblStatment.getGeneratedKeys();
                if (rs.next()) {
                    formID = rs.getInt(1);
                    
                    // Execute insert query to FormResultTbl
                    PreparedStatement formResultTblStatment = conn.prepareStatement(prepareInsertToFormResultTbl(formID, response));
                    int rowsInsertedToFormResultTbl = formResultTblStatment.executeUpdate();
                    
                    // Check if a new row was added to the table
                    if (rowsInsertedToFormResultTbl > 0) {
                        System.out.println(rowsInsertedToFormResultTbl + " rows inserted successfully to FormResultTbl.");
                    
                    }
                    else {
                    	 System.out.println("No rows inserted to FormResultTbl.");
					}
                    if (formResultTblStatment != null) {
                    	formResultTblStatment.close();
                    }
                }    
            } else {
	            System.out.println("No rows inserted to FormTbl.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        } finally {
            // Close the connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (formTblStatment != null) {
                	formTblStatment.close();
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("Disconnected from the database");
                }
            } catch (SQLException e) {
                System.out.println("Error disconnecting from the database: " + e.getMessage());
            }
        }
       
    }

    // Prepare SQL query for adding new row to FormTbl 
    public String prepareInsertToFormTbl(Form form) {
    	return "INSERT INTO FormTbl (userID, ProblemDescription, SerialNum, light1, light2, light3)\r\n"
        		+ "VALUES (" + form.getUserID() +", '" + form.getProblemDescription() 
        		+ "', '" + form.getDeviceSerialNumber() + "', '" + form.getLightsStatus().get(Consts.FIRST_LIGHT) + "',"
        				+ " '" + form.getLightsStatus().get(Consts.SECOND_LIGHT) + "', '" 
        		+ form.getLightsStatus().get(Consts.THIRD_LIGHT) + "');";
		
	}
    
    // Prepare SQL query for adding new row to FormResultTbl 
    public String prepareInsertToFormResultTbl(int formID, String response) {
    	LocalDateTime dateTime = LocalDateTime.now();
    	return "INSERT INTO FormResultTbl (formID, currentDatetime, responseStatus)\r\n"
    			+ "VALUES (" + formID + ", '" + dateTime + "', '" + response + "');\r\n"
    			+ "";
		
	}
    
   
    
    
}
