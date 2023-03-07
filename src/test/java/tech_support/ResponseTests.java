package tech_support;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import strategyPattern.ResponseStrategy;
import strategyPattern.ResponseTo24X;
import strategyPattern.ResponseTo36X;
import strategyPattern.ResponseTo51B;
import strategyPattern.ResponseToUnknown;
import techSupport.Server;

public class ResponseTests {
	private static Server server;
	
	
	@Before
	public void initializeServer() {
		server = new Server();
	}

    @Test
    public void testResponseTo24X() {
        String serialNumber = "24-X123";
        ResponseStrategy strategy = server.chooseResponseStrtegy(serialNumber);
        assertTrue(strategy instanceof ResponseTo24X);
    }

    @Test
    public void testResponseTo36X() {
    	 String serialNumber = "36-X123";
         ResponseStrategy strategy = server.chooseResponseStrtegy(serialNumber);
         assertTrue(strategy instanceof ResponseTo36X);
    }

    @Test
    public void testResponseTo51B() {
    	 String serialNumber = "51-B123";
         ResponseStrategy strategy = server.chooseResponseStrtegy(serialNumber);
         assertTrue(strategy instanceof ResponseTo51B);
    }

    @Test
    public void testResponseToUnknown() {
    	 String serialNumber = "99-X123";
         ResponseStrategy strategy = server.chooseResponseStrtegy(serialNumber);
         assertTrue(strategy instanceof ResponseToUnknown);
    }

}

