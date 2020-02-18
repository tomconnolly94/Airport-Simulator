/**
 * Class holding the main components of the simulation, capable of running without GUI
 * 
 * @author Josh Merritt
 * @author Tom Connolly
 * @version 2014.4.29
 */
package airportLevel1;

public class Simulator {

	/**
	 * Probability of a commercial flight being created
	 */
	private int p;
	
	/**
	 * Number of iterations of the primary while loop in {@link #simulate()}
	 */
	private int runtime;
	
	/**
	 * Number of aircraft that have crashed
	 */
	private static int crashedNum;
	
	/**
	 * AirTrafficControl which carries out and orders more detailed processes
	 * than Simulator
	 */
	private static AirTrafficControl atc;
	
	/**
	 * Queueing system selected by user
	 */
	private int trafficControlSystemOption;
	
	/**
	 * Main method required for level 2 submission but now defunct with implementation of GUI
	 */
	public static void main(String[] args) {
		/*default value for runtime*/
		int rtime = 1000; 
		if (args.length >= 1) {
			rtime = Integer.parseInt(args[0]);
		}
		/*ensures at least one tick*/
		if (rtime <= 0) 
			rtime = 1; 
		/*default value for p*/
		double prob = 0.1;
		if (args.length >= 2) {
			prob = Double.parseDouble(args[1]);
		}
		/*ensures probability cannot be over 1*/
		if (prob > 1.0) {
			prob = 1.0; 	
		}
		int trafficControlOption = 1;
		if(args.length >=3) {
			trafficControlOption = Integer.parseInt(args[2]);
		}
		Simulator s = new Simulator(prob, rtime, trafficControlOption);
		s.simulate();
		System.out.println("Results");
		System.out.println("");
		System.out.println("Number of Crashes: " + crashedNum);
		System.out.println("Number of Aircraft Sent to the Hangar: " + getHangarNum());
		System.out.println("Number of Take Offs: " + getTakeOffs());
		System.out.println("Number of Landings: " + getLandings());
		System.out.println("Average Total Waiting Time: " + getAverageTotalTicks());
		
	}
	
	/**
	 * Constructs a Simulator taking three parameters from the user via main method or GUI
	 * 
	 * @param prob Desired probability that a Commercial Aircraft is generated on each step of simulation
	 * @param runtime Desired length of simulation in number of steps
	 * @param trafficControlSystemOption Desired system by which aircraft in the air are queued
	 */
	public Simulator(double prob, int runtime, int trafficControlSystemOption) {
		/*convert probability to out of 1000 so type int can be used from here.*/
		prob = prob * 1000; 
		p = (int)prob;
		this.runtime = runtime;
		atc = new AirTrafficControl(p);
		this.trafficControlSystemOption = trafficControlSystemOption;
	}
	
	/**
	 * Method containing primary while loop for simulation
	 */
	public void simulate() {
		while (runtime > 0) {
			atc.setTypeOfTrafficControlSystem(trafficControlSystemOption);
			atc.createPlanes();
			atc.doHangarProcesses();
			atc.doAirProcesses();
			atc.doGroundProcesses();
			runtime --;
		}
		
		crashedNum = (atc.getCrashNo());
	}
	
	/**
	 * Returns field p
	 * 
	 * @return <code>int</code> probability of Commercial Aircraft created each step
	 */
	public int getP() {
		return p;
	}
	
	/**
	 * Returns field crashedNum
	 * 
	 * @return <code>int</code> of crashed aircraft
	 */
	public int getCrashed() {
		return crashedNum;
	}
	
	/**
	 * Returns field runtime
	 * 
	 * @return <code>int</code> of total steps user wishes to run the simulation for
	 */
	public int getRuntime() {
		return runtime;
	}
	
	/**
	 * Returns atc's field numOfPlanesAddedToHangar
	 * 
	 * @return <code>int</code> of planes broken down then added to hangar
	 */
	public static int getHangarNum() {
		return atc.getNumOfPlanesAddedToHangar();
	}
	
	/** 
	 * Returns atc's field numOfTakeOffs
	 * 
	 * @return <code>int</code> of planes successfully taken off from the runway
	 */
	public static int getTakeOffs() {
		return atc.getNumOfTakeOffs();
	}
	
	/**
	 * Returns atc's field numOfLandings
	 * 
	 * @return <code>int</code> of planes successfully landed on the runway
	 */
	public static int getLandings() {
		return atc.getNumOfLandings();
	}
	
	/**
	 * Returns average of ticks waited 
	 * 
	 * @return <code>int</code> showing the average number of steps each plane took to takeoff/land during simulation
	 */
	public static int getAverageTotalTicks(){
		int averageTotalTicks;
		int totalFlights;
		totalFlights = atc.getNumOfTakeOffs() + atc.getNumOfLandings();
		if(totalFlights != 0){
			averageTotalTicks = atc.getTotalTicks() / totalFlights;
		}
		else{
			averageTotalTicks = 0;
					}
		return averageTotalTicks;
	}
}
