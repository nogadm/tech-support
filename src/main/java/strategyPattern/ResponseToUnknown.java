package strategyPattern;

import techSupport.Form;

public class ResponseToUnknown implements ResponseStrategy {

	@Override
	public String generateResponse(Form form) {
		return "Unknown device";
	}

}
