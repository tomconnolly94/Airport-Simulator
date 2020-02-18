/**
 * Abstract class with generic methods and fields relevant to any aircraft
 * 
 * @author Tom Connolly
 * @version 2014.5.5
 */
package Aircraft;

public abstract class Aircraft {

	/**
	 * Integer to hold the time required to pass before an aircraft can take off.
	 */
	protected int takeOffTime;
	
	/**
	 * Integer to hold the time required to pass before an aircraft can land.
	 */
	protected int landingTime;
	
	/**
	 * Integer to hold how the time passed since the aircraft began taking off.
	 */
	private int takeOffTicks;
	
	/**
	 * Integer to hold how the time passed since the aircraft began landing.
	 */
	private int landingTicks;
	
	/**
	 * Integer to hold the time an aircraft has spent waiting in a queue.
	 */
	private int waitingTicks;
	
	/**
	 * Integer to hold the time an aircraft has been waiting in the hangar (as it is being repaired).*/
	private int repairTicks;
	
	/**
	 * Integer to hold the time an aircraft has until it crashes.
	 */
	protected int flyingTime;
	
	/**
	 * Constructor assigning initial values to necessary fields. 
	 */
	public Aircraft(){
		takeOffTicks = 0;
		waitingTicks = 0;
		repairTicks = 0;
		landingTicks = 0;
	}
	
	    /**
	     * Method to get the time passed while an aircraft has been waiting in a queue.
	     * 
	     * @return <code>int</code> of ticks passed while aircraft has been in queues
	     */
		public int getWaitingTicks(){
			return waitingTicks;
		}
		
		/**
		 * Method to get time required to pass before aircraft has take off from the runway.
		 * 
		 * @return <code>int</code> of ticks that must be passed before aircraft can take off
		 */
		public int getTakeOffTime(){
			return takeOffTime;
		}
		
		/**
		 * Method to get time required to pass before aircraft can land successfully.
		 * 
		 * @return <code>int</code> of ticks that must be passed before aircraft can successfully land
		 */
		public int getLandingTime(){
			return landingTime;
		}
		
		/**
		 * GroundQ specific method used for aircraft taking off from runway. {@link #takeOffTicks} is incremented on each step of the simulation via this method, e.g. it will take four calls of this method before a CommercialAircraft can take off.
		 * 
		 * @return <code>Boolean</code> true when aircraft has taken off. Returns false otherwise
		 */
		public boolean tryToTakeOff(){
			boolean end = false;
			if(takeOffTicks == takeOffTime){
				end = true;
			}
			takeOffTicks++;
			return end;
		}
		
		/**
		 * AirQ specific method used for aircraft landing. {@link #landingTicks} is incremented on each step of the simulation via this method, e.g. it will take six calls of this method before a CommercialAircraft can land successfully.
		 * 
		 * @return <code>Boolean</code> true when aircraft successfully lands. Returns false otherwise
		 * */
        public boolean tryToLand(){
			boolean end = false;
			if(landingTicks == landingTime){
				end = true;
			}
			landingTicks++;
			return end;
		}
		
		/**
		 * Method to increment the waiting ticks of an aircraft by one.
		 */
        public void incrementWaitingTicks(){
			waitingTicks++;
		}
		
		/**
		 * Hangar specific method to check whether an aircraft has spent enough time in the hangar to be repaired.
		 * 
		 * @return <code>Boolean</code> true if aircraft is repaired. Returns false otherwise
		 */
		public boolean repaired(){
			boolean end = false;
			if(repairTicks == 120){
				end = true;
			}
			return end;
		}
		
		/**
		 * Method to increment the repair ticks of an aircraft by one.
		 */
	    public void incrementRepairTicks(){
			repairTicks++;
		}
		
		/**
		 * Method to return the ticks an aircraft has been existed in the simulation. The total is the sum of the aircraft's {@link #waitingTicks}, {@link #takeOffTicks}, and {@link #repairTicks}.
		 * 
		 * @return <code>int</code> of the total time an aircraft has existed in the simulation
		 */
		public int getTotalTicks(){
			return waitingTicks + takeOffTicks + repairTicks;
		}
		
		/**
		 * Abstract method to get the time left an aircraft has before it crashes (i.e. falls out of the sky due to lack of fuel).
		 * 
		 * @return <code>int</code> of ticks the aircraft has left to fly before it crashes
		 */
        public abstract int getFlyingTime();
        
        /**
         * Abstract method to decrement the {@link #flyingTime} of an aircraft by one.
         */
		public abstract void decrementFlyingTime();
		
		/**
		 * Abstract method to override the given random value of {@link #flyingTime} at creation. Value assigned is twenty to represent a full fuel tank for a LightAircraft}.
		 */
		public abstract void overrideFlyingTime();
	

}
