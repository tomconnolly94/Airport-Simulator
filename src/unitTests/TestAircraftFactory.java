package unitTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Aircraft.Aircraft;
import airportLevel1.AircraftFactory;


public class TestAircraftFactory {

	@Test
	public void testPlaneCreation() {
		AircraftFactory airfac = new AircraftFactory(100);
		ArrayList<Aircraft> arr = new ArrayList<Aircraft>();
		int i = 0;
		while(i < 7){
			Aircraft aircraft = airfac.createAircraft();
			if(aircraft != null){
				arr.add(aircraft);
			}
			i++;
		}
		
		assertEquals(arr.size(), 1);
	}
}