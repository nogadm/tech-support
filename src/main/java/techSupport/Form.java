package techSupport;

import java.util.ArrayList;

import utils.Consts;
import utils.Indicator;

public class Form {
	private int userID;
	private String problemDescription;
	private String deviceSerialNumber;
	private ArrayList<utils.Indicator> lightsStatus;
	
	// Form constructor
	public Form(int userID, String problemDescription, String deviceSerialNumber) {
		this.userID = userID;
		// Problem description is up to 300 chars
		if (problemDescription.length() <= Consts.MAX_LENGTH_OF_DESCRIPTION)
			this.problemDescription = problemDescription;
		else 
			this.problemDescription = problemDescription.substring(0, Consts.MAX_LENGTH_OF_DESCRIPTION);
		
		// Serial number must be 64 chars long and only contain capital letters, numbers or hyphens
		if (deviceSerialNumber.matches("^[A-Z0-9-]{64}$"))
			this.deviceSerialNumber = deviceSerialNumber;
		else 
			this.deviceSerialNumber = "00000000-00000000-00000000-00000000-00000000-00000000-00000000-00000000";
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
		return "Form [userID = " + userID + ", problemDescription=" + problemDescription + ", deviceSerialNumber="
				+ deviceSerialNumber + ", lightsStatus=" + lightsStatus.get(Consts.FIRST_LIGHT) + " " +
				lightsStatus.get(Consts.SECOND_LIGHT) + " " + lightsStatus.get(Consts.THIRD_LIGHT) + "]";
	}

}


