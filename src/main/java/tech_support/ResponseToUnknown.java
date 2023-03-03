package tech_support;

public class ResponseToUnknown implements ResponseStrategy {

	@Override
	public String generateResponse(Form form) {
		return "Unknown device";
	}

}
