/**
 * Instantiates all the aircraft with appropriate probabilities and adds them to their respective queues.
 *
 * @author Tom Connolly
 * @version 2014.05.05
 */

package airportLevel1;

import java.util.Random;
import Aircraft.*;

public class AircraftFactory {

	/**
	 * Declares random number generator that is used for the probabilities of creation of a plane.
	 */
	private Random randCreate;
	/**
	 * Declares random number generator that is used for generating the fuel time.
	 */
	private Random randFlying;
	/**
	 * Parameter sent down from a higher class to give commercial flight a creation probability.
	 */
	private int p;
	/**
	 * Light aircraft creation probability.
	 */
	private static final int LIGHT_AIRCRAFT_PROB = 5;
	/**
	 * Glider creation probability.
	 */
	private static final int GLIDER_PROB = 2;

	/**
	 * Instantiating AircraftFactory with a parameter for random number generators.
	 *
	 * @param <code>int</code> of seed to be inputed into random number generators.
	 */
	public AircraftFactory(int p){
		this.p = p;
		//assigns p (passed into the constructor by 'Simulator') to global variable p
		randCreate = new Random(p);
		randFlying = new Random(p);
	}

	/**
	 * Creates an aircraft using generated probabilities instantiated at the top of method, using {@link #randCreate} and gives them fuel time generated using {@link #randFlying}.
	 * 
	 * @return <code>Aircraft</code> created during this method
	 */
	public Aircraft createAircraft(){

		int randNumCF = randFlying.nextInt(40) + 40;
		int randNumLA = randFlying.nextInt(20) + 20;
		int randNum = randCreate.nextInt(1000);
		//random number generator.

		Aircraft aircraft = null;

		if(randNum <= GLIDER_PROB){
			Aircraft plane = new Glider();
			aircraft = plane;
		}
		/*if the number generated is less than or equal to the glider probability, assigned above, 
		 * a glider is created.*/			 

		else if(randNum <= LIGHT_AIRCRAFT_PROB + GLIDER_PROB){
			Aircraft plane = new LightAircraft(randNumLA);
			aircraft = plane;
		}
		/*if the number generated is less than or equal to the glider probability added to the Light Aircraft 
		 * probability, a Light Aircraft is created.*/

		else if(randNum <= p + (LIGHT_AIRCRAFT_PROB + GLIDER_PROB)){
			Aircraft plane = new CommercialFlight(randNumCF);
			aircraft = plane;
		}
		/*if the number generated is less than or equal to the glider probability added to the Light Aircraft
		 * probability, added to p, a Commercial Flight is created.*/
		return aircraft;
		//method returns an Aircraft of type Aircraft.
	}
}