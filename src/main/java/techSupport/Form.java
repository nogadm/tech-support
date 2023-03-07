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
		
		// Serial number must be 64 chars long
		if (deviceSerialNumber != null) {
			if (deviceSerialNumber.length() == Consts.LENGTH_OF_DEVICE_SERIAL_NUMBER)
				this.deviceSerialNumber = deviceSerialNumber;
			else 
				this.deviceSerialNumber = "0000000-0000000-0000000-0000000-0000000-0000000-00000000-0000000";
		}
		else {
			this.deviceSerialNumber = "0000000-0000000-0000000-0000000-0000000-0000000-00000000-0000000";
		}
		lightsStatus = new ArrayList<>();
	}
	
	// Empty constructor for tests
	public Form() {
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
		if (problemDescription.length() <= Consts.MAX_LENGTH_OF_DESCRIPTION)
			this.problemDescription = problemDescription;
		else 
			this.problemDescription = problemDescription.substring(0, Consts.MAX_LENGTH_OF_DESCRIPTION);
	}

	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		if (deviceSerialNumber != null) {
			if (deviceSerialNumber.length() == Consts.LENGTH_OF_DEVICE_SERIAL_NUMBER)
				this.deviceSerialNumber = deviceSerialNumber;
			else 
				this.deviceSerialNumber = "0000000-0000000-0000000-0000000-0000000-0000000-00000000-0000000";
		}
		else {
			this.deviceSerialNumber = "0000000-0000000-0000000-0000000-0000000-0000000-00000000-0000000";
		}
	}

	public ArrayList<utils.Indicator> getLightsStatus() {
		return lightsStatus;
	}

	public void setLightsStatus(ArrayList<utils.Indicator> lightsStatus) {
		if (lightsStatus.size() >= Consts.ALL_LIGHTS) {
			ArrayList<utils.Indicator> shortenedlightsStatus = new ArrayList<>(lightsStatus.subList(0, 3));
			this.lightsStatus = shortenedlightsStatus;
		} 
		else {
			ArrayList<utils.Indicator> defaultLightsList = new ArrayList<>();
			for (int i = 0; i < Consts.ALL_LIGHTS; i++)
				defaultLightsList.add(Indicator.NotSpecified);
			this.lightsStatus = defaultLightsList;
		}
	}
	
	@Override
	public String toString() {
		return "Form [userID = " + userID + ", problemDescription=" + problemDescription + ", deviceSerialNumber="
				+ deviceSerialNumber + ", lightsStatus=" + lightsStatus.get(Consts.FIRST_LIGHT) + " " +
				lightsStatus.get(Consts.SECOND_LIGHT) + " " + lightsStatus.get(Consts.THIRD_LIGHT) + "]";
	}

}


