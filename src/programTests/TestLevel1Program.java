 package programTests;

import org.junit.Test;
import airportLevel1.Simulator;

public class TestLevel1Program {

	@Test
	public void test() {
		Simulator sim1 = new Simulator(0.1, 120, 1);
		
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
