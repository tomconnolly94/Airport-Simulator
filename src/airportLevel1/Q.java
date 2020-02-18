/**
 * Expanded Queue-esque class to handle processes waiting aircraft undergo
 * 
 * @author Tom Connolly
 * @version 2014.4.29
 */
package airportLevel1;
import java.util.*;

import Aircraft.Aircraft;

public class Q {

	/**
	 * Field to store a random number generator.
	 * 
	 * @see makeAndReturnBrokenDown()
	 */
	private Random rand;
	/**
	 * Field to hold a LinkedList of Aircraft objects that will be manipulated.
	 */
	private LinkedList<Aircraft> queue;
	/**
	 * Array to temporarily hold the crashed planes waiting to be removed from the Air Q.
	 */
	private ArrayList<Aircraft> crashedPlanes;
	
	/**
	 * Creates an empty queue, ready to hold Aircraft types. Also instantiates the random number generator
	 * with a seed 'p' passed in through the constructor.
	 * 
	 * @param <code>int</code> of probability 'p' being used as a seed 
	 * 
	 * @see #makeAndReturnBrokenDown
	 */
	public Q(int p){
		queue = new LinkedList<Aircraft>();
		rand = new Random(p);
	}

	/**
	 * Add an aircraft to back of queue.
	 * @param <code>Aircraft</code> to be added to queue
	 */
	public void addToQ(Aircraft aircraft){
		queue.add(aircraft);
	}

	/**
	 * Add an aircraft to a specific point in queue.
	 * @param <code>int</code> of index for aircraft to be placed at
	 * @param <code>Aircraft</code> to be placed into queue
	 */
	public void addToQ(int index, Aircraft aircraft){
		queue.add(index, aircraft);
	}

	/**
	 * Removes aircraft at front of queue.
	 */	
	public void remove(){
		queue.remove();
	}

	/**
	 * Removes a specific aircraft from queue.
	 * @param <code>Aircraft</code> to be removed
	 */
	public void removeSpecific(Aircraft aircraft){
		queue.remove(aircraft);
	}

	/**
	 * Method to get aircraft at a specific position.
	 * @param <code>int</code> of position to be returned
	 * @return <code>Aircraft</code> at desired position
	 */	
	public Aircraft getSpecific(int i){
		return queue.get(i);
	}

	/**
	 * Removes and returns aircraft at front of queue.
	 * @return <code>Aircraft</code> at front of queue
	 */
	public Aircraft getAndRemoveHead(){
		return queue.poll();
	}

	/**
	 * Method to get aircraft at front of queue.
	 * @return <code>Aircraft</code> at front of queue
	 */
	public Aircraft getHead(){
		return queue.peekFirst();
	}

	/**
	 * Method to get size of queue.
	 * @return <code>int</code> of size of queue
	 */
	public int getSize(){
		return queue.size();
	}

	/**
	 * Checks the queue for an aircraft.
	 * @param <code>Aircraft</code> to be searched for in queue
	 * @return <code>Boolean</code> true if input aircraft is found in queue
	 */
	public boolean contains(Aircraft aircraft){
		return queue.contains(aircraft);
	}

	/**
	 * Method to check if the queue is empty.
	 * @return <code>Boolean</code> true if queue is empty
	 */
	public boolean queueEmpty(){
		boolean answer = false;
		if(getSize() == 0){answer = true;}
		return answer;
	}

	/**
	 * Increment the Waiting Ticks of all aircraft from input starting position in queue.
	 * @param <code>int</code> of starting position
	 */
	public void incrementWaitingTicks(int iStartPoint){
		for(int i = iStartPoint; i < queue.size(); i++){
			queue.get(i).incrementWaitingTicks();
		}
	}
	
	/**
	 * AirQ specific method to decrement the flyingTime of all aircraft in the air by one.
	 */
	public void decrementFlyingTime(){
		for(int i = 0; i < queue.size(); i++){
			queue.get(i).decrementFlyingTime();
		}
	}

	/**
	 * Hangar specific method to increment the repair ticks of all broken down aircraft by one.
	 */
	public void incrementRepairTicks(){
		if(queue != null){
			for(Aircraft aircraft : queue){
				aircraft.incrementRepairTicks();
			}
		}
	}

	/**
	 * AirQ specific method to check for any aircraft with a flyingTime of zero, removes them from the queue, and returns the number of crashed aircraft.
	 * @return <code>int</code> of number of crashed aircraft
	 */
	public int checkAndRemoveCrashedPlanes(){
		int numOfCrashedPlanes = 0;
		crashedPlanes = new ArrayList<Aircraft>();
		for(Aircraft aircraft : queue){
			if(aircraft.getFlyingTime() == 0){
				crashedPlanes.add(aircraft);
				numOfCrashedPlanes++;
			}
		}
		removeCrashedPlanes();
		return numOfCrashedPlanes;
	}
	
	/**
	 * Removes crashed aircraft. This method serves {@link #checkAndRemoveCrashedPlanes}.
	 */
	private void removeCrashedPlanes(){
		for(Aircraft aircraft : crashedPlanes){
			queue.remove(aircraft);
		}
	}

	/**
	 * Hangar specific method to check for any aircraft that have been repaired fully in the hangar. 
	 * @return <code>ArrayList<Aircraft></code> of aircraft that have been repaired
	 */
	public ArrayList<Aircraft> checkForRepairedPlanes(){
		ArrayList<Aircraft> repairPlaneArrayList = new ArrayList<>();
		for(Aircraft aircraft : queue){
			if(aircraft.repaired() == true){
				repairPlaneArrayList.add(aircraft);
			}
		}
		return repairPlaneArrayList;
	}
	
	/**
	 * GroundQ specific method to randomly make waiting aircraft break down.
	 * @return <code>ArrayList<Aircraft></code> of aircraft that have broken down
	 */
	public ArrayList<Aircraft> makeAndReturnBrokenDown(){
		ArrayList<Aircraft> brokenDown = new ArrayList<Aircraft>();
		for(Aircraft aircraft : queue){
			int randNum = rand.nextInt(10000);
			if(randNum == 1){
				brokenDown.add(aircraft);
			}
		}
		return brokenDown;
	}	

	/**
	 * GroundQ specific method to see if aircraft on runway successfully takes off.
	 * @return <code>Boolean</code> true if aircraft takes off
	 */
	public boolean tryToTakeOff(){
		return queue.peek().tryToTakeOff();
	}

	/**
	 * AirQ specific method to see if aircraft landing successfully lands
	 * @return <code>Boolean</code> true if aircraft lands
	 */
	public boolean tryToLand(){
		return getHead().tryToLand();
	}
}