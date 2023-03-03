package tech_support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.JsonUtils;

public class Server {
    public static void main(String[] args) throws Exception {
        // Create an HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Register a handler for the /age endpoint
        server.createContext("/form", new FormHandler());
        
        // Use the default executor
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
            
            // Convert the request JSON to a Person object
            Person person = JsonUtils.fromJson(requestString, Person.class);
            
            // Generate a response based on the person's age
            String response = person.getAge() < 18 ? "kid" : "adult";
            
            // Send the response
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
            System.out.println("Sent response: " + response);
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
