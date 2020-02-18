package programTests;

import org.junit.Test;

import airportLevel1.*;


public class TestLevel2Program {

	@Test
	public void test() {
		Simulator sim1 = new Simulator(0.1, 80, 2);
		
		System.out.println("");
		System.out.println("************************************************************");
		System.out.println("");
		System.out.println("START");
		System.out.println("");
		System.out.println("************************************************************");
		
		sim1.simulate();
		
		System.out.println("");
		System.out.println("************************************************************");
		System.out.println("");
		System.out.println("END");
		System.out.println("");
		System.out.println("************************************************************");
	}

}
