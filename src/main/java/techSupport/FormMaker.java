package techSupport;

import java.util.ArrayList;

import utils.Indicator;

public class FormMaker {
	
	public Form createAnExampleForm() {
		
		// Constructor for a new Form object (int userID, String problemDescription, String deviceSerialNumber)
        Form form = new Form(4678, "My laptop won't turn off.", "24-X0F-QO5O3X-VW28D8-ADREDC-0J92MA-ES0WNY-8IAYLM-DCJ61C");
		
        // ArrayList of 3 light indicators
    	ArrayList<Indicator> status = new ArrayList<>();
        status.add(Indicator.off);
		status.add(Indicator.on);
		status.add(Indicator.blinking);
        form.setLightsStatus(status);
		
		return form;
    }

}
