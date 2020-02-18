/**
 * Class implementing light aircraft constructor and specific feature of creating a fuelled light aircraft when a glider is created.
 * 
 * @author Tom Connolly
 * @version 2014.5.6
 */
package Aircraft;


public class LightAircraft extends PoweredAircraft{

	
    /**
     * Constructor creating a LightAircraft with a given flyingTime.
     * 
     * @param <code>int</code> of time the LightAircraft can stay in the air before crashing
     */
	public LightAircraft(int p){
		super();
		flyingTime = p;
				
	}
	
	/**
	 * Method to get flyingTime.
	 * 
	 * @return <code>int</code> of time the LightAircraft can stay in the air before crashing 
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
	 * Method to override flyingTime. FlyingTime is set to twenty, representing a full fuel tank.
	 */
	public void overrideFlyingTime(){
		flyingTime = 20;
	}
}
