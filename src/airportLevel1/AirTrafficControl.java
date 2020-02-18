package airportLevel1;

import java.util.ArrayList;

import Aircraft.*;

public class AirTrafficControl {
	
	/**field to hold true or false depending on whether any aircraft
	 * is currently using the run way.*/
	private boolean airRunwayInUse;
	/**field to hold true or false depending on whether any aircraft
	 * is currently using the run way.*/
	private boolean groundRunwayInUse;
	/**field of type Q to be responsible for carrying out all activities
	 * relating to aircraft waiting to be repaired in the hangar.*/
	private Q hangar;
	/**field of type Q to be responsible for carrying out all activities
	 * relating to aircraft waiting to take off.*/
	private Q groundQ;
	/**field of type Q to be responsible for carrying out all activities
	 * relating to aircraft waiting to land.*/
	private Q airQ;
	/**field to hold the number of crashes occurring to due lack of fuel.*/
	private int crashCounter;
	/**field of type AircraftFactory to build and return objects of type 
	 * Aircraft.*/
	private AircraftFactory factory;
	/**field to hold the number of take off that occur during the life of the program.*/
	private int numOfTakeOffs;
	/**field to hold the number of landings that occur during the life of the program.*/
	private int numOfLandings;
	/**field to hold the number of aircraft that break down and so are sent to the hangar
	 * to be repaired.*/
	private int numOfPlanesAddedToHangar;
	/**field to hold which type of traffic control system has been activated to be in use.*/
	private int typeOfTrafficControlSystem;
	/**field to hold whether the runway is currently being used by the groundQ overriding
	 * the airQ which would otherwise have priority.*/
	private boolean groundQTakeOffOverride;
	/**field to hold the total amount of time aircraft have been waiting in queues.*/
	private int totalTicks;
	/**probability of commercialFlight creation and used for RNG seed.*/
	private int p;
	
	
	/** 
	 * constructor sets all fields to their initial values. all the runway 'in use' value's are set to false, all the queue's 
	 * and the factory are instantiated, the crash counter, the number of take offs and landings are all set to 0 and p is passed 
	 * to the global varibale p situated in this class.
	 * */
	public AirTrafficControl(int p){
		airRunwayInUse = false;
		groundRunwayInUse = false;
		hangar = new Q(p);
		groundQ = new Q(p);
		airQ = new Q(p);
		crashCounter = 0;
		factory = new AircraftFactory(p);
		numOfTakeOffs = 0;
		numOfLandings = 0;
		this.p = p;
	}
	
	/**
	 * method to return the number of take offs.
	 * @return <code>int</code> number of take offs that have occurred so far in this simulation.
	 * */
	public int getNumOfTakeOffs(){
		return numOfTakeOffs;
	}
	
	/**
	 * method to return the number of landings.
	 * @return <code>int</code> number of landings that have occurred so far in this simulation.
	 * */
	public int getNumOfLandings(){
		return numOfLandings;
	}
	
	/**
	 * <code>boolean</code> value to determine whether an aircraft from the <code>groundQ</code> is currently taking off.
	 * 
	 * @return <code>boolean</code> value to determine whether an aircraft from the <code>groundQ</code> is currently taking off.
	 *  */
	public boolean isGroundRunwayInUse(){
		return groundRunwayInUse;
	}
	
	/**
	 * changes the state of the runway if an aircraft from the <code>groundQ</code> is taking off/has taken off.
	 * 
	 * @param <code>boolean</code> value that describes whether an <code>Aircraft</code> in the <code>groundQ</code> is currently 
	 * using the runway.
	 * */
	public void setGroundRunwayInUse(boolean inUse){
		groundRunwayInUse = inUse;
	}
	
	/**
	 * returns a boolean value depending on whether the runway is free or not.
	 * 
	 * @return boolean value depending on whether the runway is free or not.
	 * */
	public boolean isAirRunwayInUse(){
		return airRunwayInUse;
	}
	
	/**
	 * changes the state of the runway if an aircraft is taking off.
	 * 
	 * @param boolean which changes the state of the runway if an aircraft is taking off.
	 * */
	public void setAirRunwayInUse(boolean inUse){
		airRunwayInUse = inUse;
	}
	
	/**
	 * sets take off Override to 'currently occurring'.
	 * 
	 * @param <code>boolean</code> value which determines if a groundTakeOffOverride is occurring.
	 * */
	public void setGroundQTakeOffOverride(boolean bool){
		groundQTakeOffOverride = bool;
	}
	
	/**
	 * method to return whether a take off Override is currently occurring.
	 * 
	 * @return <code>boolean</code> value which is true when a <code>groundQ</code> <code>Aircraft</code> is using the runway.
	 * */
	public boolean getGroundQTakeOffOverride(){
		return groundQTakeOffOverride;
	}
	
	/**
	 * returns the type of traffic control system.
	 * 
	 * @return <code>int</code> either 1 or 2 depending on the type of traffic control system in use.
	 * */
	public int getTypeOfTrafficControlSystem(){
		return typeOfTrafficControlSystem;
	}
	
	/**
	 * method to set the type of traffic control system.
	 * 
	 * @param <code>int</code> either 1 or 2 depending on the desired traffic control system.
	 * */
	public void setTypeOfTrafficControlSystem(int option){
		typeOfTrafficControlSystem = option;
	}
	
	/**
	 * returns how many crashes have occurred during the program life.
	 * 
	 * @return <code>int</code> to represent how many <code>Aircraft</code> have crashed during the life of the program.
	 */
	public int getCrashNo(){
		return crashCounter;
	}
	
	/**
	 * returns how many aircraft have broken down and so been transferred to the hangar.
	 * 
	 * @return <code>int</code> representing how many planes crashed in the groundQ or on the runway and have so been removed to the hangar
	 * */
	public int getNumOfPlanesAddedToHangar(){
		return numOfPlanesAddedToHangar;
	}
	
	/**
	 * method that uses the aircraft returned by a method in the aircraft factory and adds it to the relevant queue, if it returns a non null value.
	 */
	public void createPlanes(){		
		Aircraft aircraft1 = factory.createAircraft();
		if(aircraft1 != null){
			groundQ.addToQ(aircraft1);
		}
		Aircraft aircraft2 = factory.createAircraft();
		if(aircraft2 != null){
			airQ.addToQ(aircraft2);
		}
	}
	
	/**
	 * groups all the processes that need to be completed in the <code>airQ</code>. More specifically, it checks if the <code>airQ</code> is empty. If it is not empty,
	 * it then moves on to checking the type of traffic control system, if it is '1' then the sort method is used to order the <code>Aircraft</code> in terms of 
	 * remaining flying time. After this, it checks to see whether the runway is in use by an Aircraft from the <code>groundQ</code>. If the runway is not in use by 
	 * the <code>groundQ</code> it sets the air runway to busy and then runs the landing processes. It calls the <code>tryToLand</code> method. If this returns true 
	 * then the number of landings is increased, the runway is set to free, the ticks of the landed aircraft are absorbed into the total ticks and then the aircraft
	 * is removed from the <code>groundQ</code>. After this the ticks must be incremented. If no <code>Aircraft</code> are taking off, all <code>Aircraft</code> have
	 * their Waiting ticks incremented, however if an aircraft is taking off, its waiting ticks are not incremented. Finally all aircraft have their flying time decremented
	 * and the crashes are totalled and added to field <code>crashCounter</code>.
	 */
	public void doAirProcesses(){
		if(airQ.queueEmpty() == false){

			if(typeOfTrafficControlSystem == 1){
				sortList(airQ);
			}

			if(isGroundRunwayInUse() == false && getGroundQTakeOffOverride() == false){
				setAirRunwayInUse(true);
				if(airQ.tryToLand() == true){
					numOfLandings++;
					setAirRunwayInUse(false);
					totalTicks = totalTicks + airQ.getHead().getTotalTicks();
					airQ.remove();
				}
				airQ.incrementWaitingTicks(1);
			}
			else{
				airQ.incrementWaitingTicks(0);
			}
			airQ.decrementFlyingTime();
			crashCounter = crashCounter + airQ.checkAndRemoveCrashedPlanes();	
		}
	}
	
	/**
	 * groups all the processes that need to be completed involving the <code>groundQ</code>. More specifically, it checks if the <code>groundQ</code> is empty. If it is not empty,
	 * it then moves onto checking the type of traffic control system. 
	 * 
	 * If its '2' it checks if the <code>airQ</code> is empty or if an <code>Aircraft</code> is already taking off. If either of these are true, the <code>groundRunway</code> 
	 * is set 'in use' and a take off is attempted. Afterwards the waiting ticks for all the <code>Aircraft</code> are incremented. If an <code>Aircraft</code> its waiting
	 * ticks are not incremented.
	 * 
	 * If the type of traffic control system is '1', it checks to see whether a <code>groundQ</code> override is possible. If it is, then the <code>groundQOverride</code> 
	 * field is set to true and a take off begins. When it finishes the <code>groundQOverride</code> field is set to false and the ticks are incremented as before. If a 
	 * <code>groundQOverride</code> is not possible then a normal take off is attempted (as above). Finally a method called <code>sendBrokenDownToHangarAndRemoveFromGroundQ</code>
	 * is called. 
	 * 
	 * */
	public void doGroundProcesses(){
		if(groundQ.queueEmpty() == false){
			if(typeOfTrafficControlSystem == 2){
				if(airQ.queueEmpty() == true || isGroundRunwayInUse() == true){
					setGroundRunwayInUse(true);
					if(groundQ.tryToTakeOff() == true){
						takeOff();
					}
					else{
						groundQ.incrementWaitingTicks(1);
					}
				}
				else{
					groundQ.incrementWaitingTicks(0);
				}
			}
			else if(typeOfTrafficControlSystem == 1){
				if(airQ.queueEmpty() == false && 
						groundQ.getHead().getWaitingTicks() > airQ.getHead().getWaitingTicks() &&
						groundQ.getHead().getTakeOffTime() < airQ.getHead().getFlyingTime() && isAirRunwayInUse() == false || getGroundQTakeOffOverride() == true){
					setGroundQTakeOffOverride(true);
					if(groundQ.tryToTakeOff() == true){
						takeOff();
						setGroundQTakeOffOverride(false);
					}
					else{
						groundQ.incrementWaitingTicks(1);
					}
				}
				else if(airQ.queueEmpty() == true || isGroundRunwayInUse() == true){
					setGroundRunwayInUse(true);
					if(groundQ.tryToTakeOff() == true){
						takeOff();
					}
					groundQ.incrementWaitingTicks(1);
				}
				else{
					groundQ.incrementWaitingTicks(0);
				}
				sendBrokenDownToHangarAndRemoveFromGroundQ();
			}
		}
	}
	
	/**
	 * groups all the processes that need to be completed in the hangar. It checks to see if the hangar has any <code>Aircraft</code> in it. If it does, then it searches through
	 * the <code>hangar</code> to see if any <code>Aircraft</code> are repaired and so can be removed from the <code>hangar</code> and returned to the <code>groundQ</code>. It then
	 * increments the waiting and repair ticks of all <code>Aircraft</code> in the <code>hangar</code>.
	 * */
	public void doHangarProcesses(){
		if(hangar.queueEmpty() == false){
			for(Aircraft aircraft : hangar.checkForRepairedPlanes()){
				hangar.removeSpecific(aircraft);
				groundQ.addToQ(aircraft);
			}
			hangar.incrementRepairTicks();
			hangar.incrementWaitingTicks(0);
		}
	}
	
	/**
	 * causes aircraft to break down due to a random number and probability. It takes an array from the <code>groundQ</code> and searches through it removing all entries from the 
	 * <code>groundQ</code> and adding them to the <code>hangar</code>. It also keeps track of how many <code>Aircraft</code> have been added to the hangar in the life of the program.
	 * */
	private void sendBrokenDownToHangarAndRemoveFromGroundQ(){
		ArrayList<Aircraft> brokenDownQ = groundQ.makeAndReturnBrokenDown();
		if(brokenDownQ != null){
			for(Aircraft aircraft : brokenDownQ){
				groundQ.removeSpecific(aircraft);
				hangar.addToQ(aircraft);
				numOfPlanesAddedToHangar++;
			}
		}
	}
	
	/**
	 * sorts the list of aircraft based on how much flying time they have left.
	 * 
	 * @param q This method takes an object of type <code>Q</code>.
	 * */
	private void sortList(Q q){
		int i = 0;
		int j = 1;
		int swapsOccurred = 1;

		while(swapsOccurred != 0){
			swapsOccurred = 0;
			while(j < q.getSize()){
				if (q.getSpecific(i).getFlyingTime() > q.getSpecific(j).getFlyingTime()){
					Aircraft tempAircrafti = q.getSpecific(i);
					Aircraft tempAircraftj = q.getSpecific(j);
					q.removeSpecific(tempAircrafti);
					q.removeSpecific(tempAircraftj);
					q.addToQ(i, tempAircraftj);
					q.addToQ(j, tempAircrafti);
					swapsOccurred++;
				}
				i++;
				j++;
			}	
		}
	}
	
	/**
	 * Returns the total number of ticks of each <code>Aircraft</code> that has taken off or landed.
	 * */
	public int getTotalTicks(){
		return totalTicks;
	}

	/**
	 * Groups together all the taking off processes. It checks whether the <code>Aircraft</code> taking 
	 * off is a glider and if it is, creates a <code>LightAircraft</code> to tow the Glider down the runway. 
	 * The <code>LightAircraft</code> has a <code>flyingTime</code> of 20.
	 * */
	private void takeOff(){
		if(groundQ.getHead() instanceof Glider){
			numOfTakeOffs++;
			totalTicks = totalTicks + groundQ.getHead().getTotalTicks();
			groundQ.remove();
			groundQ.incrementWaitingTicks(0);
			Aircraft plane = new LightAircraft(p);
			plane.overrideFlyingTime();
			airQ.addToQ(plane);
		}
		else{
			numOfTakeOffs++;
			totalTicks = totalTicks + groundQ.getHead().getTotalTicks();
			groundQ.remove();
			groundQ.incrementWaitingTicks(0);
		}
		setGroundRunwayInUse(false);
	}
}