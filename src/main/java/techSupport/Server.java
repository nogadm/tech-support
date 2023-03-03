package techSupport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import strategyPattern.ResponseStrategy;
import strategyPattern.ResponseTo24X;
import strategyPattern.ResponseTo36X;
import strategyPattern.ResponseTo51B;
import strategyPattern.ResponseToUnknown;
import utils.JsonUtils;

public class Server {
    public static void main(String[] args) throws Exception {
        // Create an HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/form", new FormHandler());
        server.setExecutor(null);
        // Start the server
        server.start();
        System.out.println("Server started");
    }

    // Handler for the /form FormHandler
    static class FormHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            // Read the request body into a string
            InputStream inputStream = httpExchange.getRequestBody();
            String requestString = readInputStream(inputStream);
            System.out.println("Received request: " + requestString);
            
            // Convert the request JSON to a Form object
            Form clientForm = JsonUtils.fromJson(requestString, Form.class);
           
            // Generate a response 
            String response;
            String serialNumber = clientForm.getDeviceSerialNumber();
            if (isNumber(serialNumber))
            	response = "Bad serial number";
            else {
            	 ResponseStrategy responseStrategy = chooseResponseStrtegy(serialNumber);
            	 response = responseStrategy.generateResponse(clientForm);
			}
               
            // Send the response
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
            System.out.println("Sent response: " + response);
        }
        
     // Checks if serial number is a number
        public boolean isNumber(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        
        
        public ResponseStrategy chooseResponseStrtegy(String serialNumber) {
        	ResponseStrategy startegy;
        	if (serialNumber.startsWith("24-X"))
        		startegy = new ResponseTo24X();
        	else {
        		if (serialNumber.startsWith("36-X"))
        			startegy = new ResponseTo36X();
        		else {
    				if (serialNumber.startsWith("51-B"))
    					startegy = new ResponseTo51B();
    				else {
						startegy = new ResponseToUnknown();
					}
    			}
        	}
			return startegy;	
        }
         
    }

    // Read an input stream into a string
    public static String readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }   
    
}
