package techSupport;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import utils.JsonUtils;

public class Client {
	
    public static void main(String[] args) throws Exception {
     
    	// Convert the Form object to JSON
    	FormMaker formMaker = new FormMaker();
    	Form exampleForm = formMaker.createAnExampleForm();
        String json = JsonUtils.toJson(exampleForm);
        
        // Send an HTTP POST request to the server
        URL url = new URL("http://localhost:8000/form");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(json.getBytes());
        outputStream.flush();
        outputStream.close();
        
        // Read the server response
        String response = Server.readInputStream(connection.getInputStream());
        System.out.println("Server response: " + response);
        
        // Disconnect the HTTP connection
        connection.disconnect();
    } 
    
    
}
