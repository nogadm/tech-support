package strategyPattern;

import techSupport.Form;

public class ResponseTo24X implements ResponseStrategy {

	@Override
	public String generateResponse(Form form) {
		return "please upgrade your device";
	}

}
