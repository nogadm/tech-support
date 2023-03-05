package strategyPattern;

import techSupport.Form;
import utils.Consts;
import utils.Indicator;

public class ResponseTo36X implements ResponseStrategy {

	@Override
	public String generateResponse(Form form) {
		int onCount = 0;
		int offCount = 0;
		int blinkingCount = 0;
		
		// Loop through the lights array and count all lights in each status
		for (Indicator lightIndicator : form.getLightsStatus()) {
			if (lightIndicator == Indicator.on)
				onCount++;
			else {
				if (lightIndicator == Indicator.off)
					offCount++;
				else {
					if (lightIndicator == Indicator.blinking)
						blinkingCount++;
				}
						
			}
				
		}
		
		// Generate response based on the status of the lights
		if (onCount == Consts.ALL_LIGHTS)
			return "ALL is ok";
		if (offCount == Consts.ALL_LIGHTS)
			return "turn on the device";
		if (blinkingCount == Consts.TWO_LIGHTS)
			return "Please wait";
		// Other cases
		else 
			return "Unknown device";
	}
}
