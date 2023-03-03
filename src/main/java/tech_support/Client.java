package tech_support;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import utils.JsonUtils;

public class Client {
    public static void main(String[] args) throws Exception {
        // Create a new Person object with age 20
        Person person = new Person(7);
        
        // Convert the Person object to JSON
        String json = JsonUtils.toJson(person);
        
        // Send an HTTP POST request to the server
        URL url = new URL("http://localhost:8000/age");
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
