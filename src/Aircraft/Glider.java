/**
 * Class to implement glider constructor and basic method to return flyingTime.
 * 
 * @author Tom Connolly
 * @version 2014.5.6
 */
package Aircraft;

public class Glider extends Aircraft{
    
	/**
	 * Constructor setting landing and take off times for Glider.
	 */
	public Glider(){
		super();
		landingTime = 8;
		takeOffTime = 6;
	}
	
	/**
	 * Method to return flyingTime left for a glider. Gliders can stay aloft indefinitely so its flyingTime is permanently set to 99.
	 * 
	 * @param <code>int</code> of time the Glider can stay in the air before crashing
	 */
	public int getFlyingTime(){
		return 99;
	}
	
	/**
	 * Unused method to prevent compiler error.
	 */
	public void decrementFlyingTime(){}
	
	/** 
	 * Unused method to prevent compiler error.
	 */
	public void overrideFlyingTime(){}
}
