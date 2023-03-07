package tech_support;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import strategyPattern.ResponseTo24X;
import strategyPattern.ResponseTo36X;
import strategyPattern.ResponseTo51B;
import strategyPattern.ResponseToUnknown;
import techSupport.Form;
import utils.Consts;
import utils.Indicator;

public class InputFormTests {
	private static Form testForm;

	// Test the problem description length is up to 300
	@Test
    public void testProblemDescriptionIsUpTo300CharsFromConstructor() {
		String descriptionOver300Chars = "Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. ";
	 	testForm = new Form(0, descriptionOver300Chars, "53-X");
	 	assertTrue(testForm.getProblemDescription().length() <= Consts.MAX_LENGTH_OF_DESCRIPTION);
    }
	
	@Test
    public void testProblemDescriptionIsUpTo300CharsFromGetMethod() {
		String descriptionOver300Chars = "Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. Problem description. ";
	 	testForm = new Form();
	 	testForm.setProblemDescription(descriptionOver300Chars);
	 	assertTrue(testForm.getProblemDescription().length() <= Consts.MAX_LENGTH_OF_DESCRIPTION);
    }
	
	
	// Test the device serial number is a string in length of 64
	@Test
    public void testSerialNumberIs64CharsLongFromConstructor() {
		String serialNumberLessThan64 = "SERIAL-NUMBER-0-SERIAL-NUMBER-0-SERIAL-NUMBER-0-SERIAL-NUMBER";
	 	testForm = new Form(0, "desc", serialNumberLessThan64);
	 	assertTrue(testForm.getDeviceSerialNumber().length() == Consts.LENGTH_OF_DEVICE_SERIAL_NUMBER);
    }
	
	@Test
    public void testSerialNumberIs64CharsLongFromGetMethod() {
		String serialNumberLessThan64 = "SERIAL-NUMBER-0-SERIAL-NUMBER-0-SERIAL-NUMBER-0-SERIAL-NUMBER";
	 	testForm = new Form();
	 	testForm.setDeviceSerialNumber(serialNumberLessThan64);
	 	assertTrue(testForm.getDeviceSerialNumber().length() == Consts.LENGTH_OF_DEVICE_SERIAL_NUMBER);
    }
	
	// Test that the light arraylist contains only 3 light indicators
	@Test
    public void testOnlyThreeLightIndicatorsCase1() {
		testForm = new Form();
		ArrayList<Indicator> moreThan3Lights = new ArrayList<>();
		moreThan3Lights.add(Indicator.off);
		moreThan3Lights.add(Indicator.off);
		moreThan3Lights.add(Indicator.off);
		moreThan3Lights.add(Indicator.off);
		testForm.setLightsStatus(moreThan3Lights);
		assertTrue(testForm.getLightsStatus().size() == Consts.ALL_LIGHTS);
	}
	
	@Test
    public void testOnlyThreeLightIndicatorsCase2() {
		testForm = new Form();
		ArrayList<Indicator> lessThan3Lights = new ArrayList<>();
		lessThan3Lights.add(Indicator.off);
		lessThan3Lights.add(Indicator.off);
		testForm.setLightsStatus(lessThan3Lights);
		assertTrue(testForm.getLightsStatus().size() == Consts.ALL_LIGHTS);
	}
	
	
}
