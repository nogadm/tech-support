package tech_support;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import strategyPattern.ResponseStrategy;
import strategyPattern.ResponseTo24X;
import strategyPattern.ResponseTo36X;
import strategyPattern.ResponseTo51B;
import strategyPattern.ResponseToUnknown;
import techSupport.Form;
import utils.Indicator;

public class ResponseStringTests {
	private static ResponseStrategy responseStrategy36X;
	private static ResponseStrategy responseStrategy51B;
	private static ResponseStrategy responseStrategy24X;
	private static ResponseStrategy responseStrategyUnknown;
	private static Form testForm;
	private static ArrayList<Indicator> lights;

	@Before
	public void initializeResponseClasses() {
		responseStrategy36X = new ResponseTo36X();
		responseStrategy51B = new ResponseTo51B();
		responseStrategy24X = new ResponseTo24X();
		responseStrategyUnknown = new ResponseToUnknown();
		testForm = new Form();
		lights = new ArrayList<>();
	}
	
	// Test the strings that are generated as a response to serial numbers that start with 36-X
	@Test
    public void test36XAllIndicatorsAreOff() {
	 	lights.add(Indicator.off);
	 	lights.add(Indicator.off);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("turn on the device" == responseStrategy36X.generateResponse(testForm));
    }
	
	@Test
    public void test36XAnyTwoIndicatorsAreBlinkingCase1() {
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("Please wait" == responseStrategy36X.generateResponse(testForm));
	}
	
	@Test
    public void test36XAnyTwoIndicatorsAreBlinkingCase2() {
		lights.add(Indicator.off);
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.blinking);
	 	testForm.setLightsStatus(lights);
        assertTrue("Please wait" == responseStrategy36X.generateResponse(testForm));
	}
	
	@Test
    public void test36XAnyTwoIndicatorsAreBlinkingCase3() {
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.off);
	 	lights.add(Indicator.blinking);
	 	testForm.setLightsStatus(lights);
        assertTrue("Please wait" == responseStrategy36X.generateResponse(testForm));
	}
	
	@Test
    public void test36XAllIndicatorsAreOn() {
	 	lights.add(Indicator.on);
	 	lights.add(Indicator.on);
	 	lights.add(Indicator.on);
	 	testForm.setLightsStatus(lights);
        assertTrue("ALL is ok" == responseStrategy36X.generateResponse(testForm));
	}
	
	
	
	// Test the strings that are generated as a response to serial numbers that start with 51-B
	@Test
    public void test51BAllIndicatorsAreOff() {
	 	lights.add(Indicator.off);
	 	lights.add(Indicator.off);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("turn on the device" == responseStrategy51B.generateResponse(testForm));
    }
	
	@Test
    public void test51BEvenOneIsBlinkingCase1() {
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.off);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("Please wait" == responseStrategy51B.generateResponse(testForm));
    }
	
	@Test
    public void test51BEvenOneIsBlinkingCase2() {
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("Please wait" == responseStrategy51B.generateResponse(testForm));
    }
	
	@Test
    public void test51BEvenOneIsBlinkingCase3() {
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.blinking);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("Please wait" == responseStrategy51B.generateResponse(testForm));
    }
	
	@Test
    public void test51BMoreThanOneIndicatorIsOnCase1() {
	 	lights.add(Indicator.on);
	 	lights.add(Indicator.on);
	 	lights.add(Indicator.off);
	 	testForm.setLightsStatus(lights);
        assertTrue("ALL is ok" == responseStrategy51B.generateResponse(testForm));
    }
	
	@Test
    public void test51BMoreThanOneIndicatorIsOnCase2() {
	 	lights.add(Indicator.on);
	 	lights.add(Indicator.on);
	 	lights.add(Indicator.on);
	 	testForm.setLightsStatus(lights);
        assertTrue("ALL is ok" == responseStrategy51B.generateResponse(testForm));
    }
	
	
	// Test the strings that are generated as a response to serial numbers that start with 24-X
	@Test
    public void test24X() {
        assertTrue("please upgrade your device" == responseStrategy24X.generateResponse(testForm));
    }
	
	// Test the strings that are generated as a response to serial numbers that are unknown
	@Test
    public void testUnknownSerialNumber() {
        assertTrue("Unknown device" == responseStrategyUnknown.generateResponse(testForm));
    }
	
	
	
	
	
}
	
	