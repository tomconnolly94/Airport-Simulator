/**
 * Class implementing commercial aircraft constructor and relevant methods for the class
 * 
 * @author Tom Connolly
 * @version 2014.5.6
 */
package Aircraft;


public class CommercialFlight extends PoweredAircraft {

	
	
	/**
	 * Constructor creating a CommercialAircraft with a given flyingTime.
	 * 
	 * @param <code>int</code> of time the CommercialAircraft can stay in the air before crashing
	 */
	public CommercialFlight(int p){
		super();
		flyingTime = p;		
	}
	
	/**
	 * Method to get flyingTime.
	 * 
	 * @param <code>int</code> of time the CommercialAircraft can stay in the air before crashing
	 */
	public int getFlyingTime(){
		return flyingTime;
	}
	
	/**
	 * Method to decrement flyingTime by 1 unit.
	 */
	public void decrementFlyingTime(){
		flyingTime--;
	}
	
	/** 
	 * Unused method required to prevent compiler error.
	 */
	public void overrideFlyingTime(){}
}