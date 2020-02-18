/**
 * Abstract class for CommercialAircraft and LightAircraft implementing their flyingTime features and attributes
 * 
 * @author Tom Connolly
 * @version 2014.5.6
 */
package Aircraft;

public abstract class PoweredAircraft extends Aircraft {
	
	/**
	 * Constructor to set landing and take off times for poweredAircraft subclasses.
	 */
	public PoweredAircraft(){
		takeOffTime = 4;
		landingTime = 6;
	}
	
	/**
	 * Method to get flyingTime of an aircraft.
	 * 
	 * @return <code>int</code> of ticks left before an aircraft crashes due to lack of fuel
	 */
	public int getFlyingTime(){
		return flyingTime;
	}
	
	/**
	 * Method to decrement flyingTime by a given amount.
	 * 
	 * @param <code>int</code> of ticks to be decremented from flyingTime
	 */
	public void decrementFlyingTime(int num){
		flyingTime = flyingTime - num;
	}
	
}
