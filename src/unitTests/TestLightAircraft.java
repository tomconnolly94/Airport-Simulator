package unitTests;
import static org.junit.Assert.*;

import org.junit.Test;

import Aircraft.Aircraft;
import Aircraft.CommercialFlight;
import Aircraft.LightAircraft;


public class TestLightAircraft {

	@Test
	public void testFlyingTime() {
		Aircraft plane1 = new LightAircraft(62);
		assertEquals(plane1.getFlyingTime(), 62);
	}

	@Test
	public void testLandingTime() {
		Aircraft plane1 = new CommercialFlight(62);
		assertEquals(plane1.getLandingTime(), 6);
	}
	
}
