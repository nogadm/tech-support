package tech_support;

import java.util.ArrayList;

import utils.Consts;
import utils.Indicator;

public class Form {
	private static int formIdCounter = 1;
	private int formID;
	private int userID;
	private String problemDescription;
	private String deviceSerialNumber;
	private ArrayList<utils.Indicator> lightsStatus;
	
	// Form constructor
	public Form(int userID, String problemDescription, String deviceSerialNumber) {
		this.formID = formIdCounter++;
		this.userID = userID;
		if (problemDescription.length() <= Consts.MAX_LENGTH_OF_DESCRIPTION)
			this.problemDescription = problemDescription;
		else 
			this.problemDescription = problemDescription.substring(0, Consts.MAX_LENGTH_OF_DESCRIPTION);
		this.deviceSerialNumber = deviceSerialNumber;
		lightsStatus = new ArrayList<>();
	}
	
	// Getters and setters
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

	public ArrayList<utils.Indicator> getLightsStatus() {
		return lightsStatus;
	}

	public void setLightsStatus(ArrayList<utils.Indicator> lightsStatus) {
		this.lightsStatus = lightsStatus;
	}
	
	@Override
	public String toString() {
		return "Form [formID = " + formID + " userID = " + userID + ", problemDescription=" + problemDescription + ", deviceSerialNumber="
				+ deviceSerialNumber + ", lightsStatus=" + lightsStatus.get(0) + " " +
				lightsStatus.get(1) + " " + lightsStatus.get(2) + "]";
	}

	
	
	
	public static void main(String[] args) throws Exception {
		ArrayList<Indicator> status = new ArrayList<>();
		status.add(Indicator.on);
		status.add(Indicator.on);
		status.add(Indicator.blinking);
		
		Form form1 = new Form(4678, "my computer is broken", "65-x");
		form1.setLightsStatus(status);
		
		Form form2 = new Form(58, "my computer is broken", "54-x");
		form2.setLightsStatus(status);
		
		Form form3 = new Form(58, "my computer is broken", "54-x");
		form3.setLightsStatus(status);
		
		System.out.println(form1);
		System.out.println(form2);
		System.out.println(form3);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}


