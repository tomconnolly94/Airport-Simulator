package unitTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Aircraft.Aircraft;
import Aircraft.CommercialFlight;
import Aircraft.LightAircraft;


public class TestCommercialFlight {

	@Test
	public void testTakeOffTime() {
		Aircraft comflight = new CommercialFlight(62);
		assertEquals(comflight.getTakeOffTime(), 4);
	}
	
	@Test
	public void testLandingTime() {
		Aircraft comFlight = new CommercialFlight(62);
		Aircraft lightCraft = new LightAircraft(1);
		ArrayList<Aircraft> al = new ArrayList<Aircraft>();
		al.add(comFlight);
		al.add(lightCraft);
		System.out.println(al.get(0));
	}
	
	@Test
	public void testFlyingTime() {
		Aircraft comflight = new CommercialFlight(62);
		assertEquals(comflight.getFlyingTime(), 62);
	}

}
