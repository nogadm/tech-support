package tech_support;

public interface ResponseStrategy {
	
	// This interface implements the Strategy design pattern.
	// Which will allow the server to select the algorithm for generating
	// response to the client at runtime, based on the client's input.
	
	String generateResponse(Form form);
}
