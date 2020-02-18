package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import airportLevel1.*;
import Aircraft.Aircraft;
import Aircraft.CommercialFlight;
import Aircraft.Glider;
import Aircraft.LightAircraft;


public class TestQ {
	private Q q;
	private Aircraft cf;
	private Aircraft la;
	private Aircraft g;
	private int seed = 62;
	
	@Before
	public void setUp(){
	cf = new CommercialFlight(seed);
	la = new LightAircraft(seed);
	g = new Glider();
	q = new Q(seed);
	q.addToQ(cf);
	q.addToQ(la);
	q.addToQ(g);
	}
	
	@Test
	public void testAddToQ() {
		Aircraft cf2 = new CommercialFlight(seed);
		q.addToQ(cf2);
		assertEquals(q.getSpecific(q.getSize() - 1), cf2);
	}
	/*Adds Aircraft cf2 to the list and checks to see if it is present at the end of the list.*/
	
	@Test
	public void testRemoveSpecific() {
		q.removeSpecific(la);
		assertEquals(q.getSpecific(1), g);		
	}
	/*removes Aircraft from the queue and checks that the aircraft behind it took its spot.*/

	@Test
	public void testGetAndRemoveHead() {
		q.getAndRemoveHead();
		assertEquals(q.getHead(), la);
	}
	
	@Test
	public void testContains() {
		assertEquals(q.contains(la), true);
	}
	
	@Test
	public void testQueueEmpty() {
		assertEquals(q.queueEmpty(), false);
	}
	
	@Test
	public void testIncrementWatingTicks(){
		q.incrementWaitingTicks(0);
		assertEquals(q.getSpecific(0).getWaitingTicks(), 1);
		assertEquals(q.getSpecific(1).getWaitingTicks(), 1);
		assertEquals(q.getSpecific(2).getWaitingTicks(), 1);
	}
	
	@Test
	public void testDecrementFlyingTime(){
		int a = q.getSpecific(0).getFlyingTime();
		int b = q.getSpecific(1).getFlyingTime();
		int c = q.getSpecific(2).getFlyingTime();
		q.decrementFlyingTime();
		assertEquals(q.getSpecific(0).getFlyingTime(), (a - 1));
		assertEquals(q.getSpecific(1).getFlyingTime(), (b - 1));
		assertEquals(q.getSpecific(2).getFlyingTime(), (c));
	}
	
	@Test
	public void testIncrementRepairTicks(){
		q.incrementRepairTicks();
		assertEquals(q.getSpecific(0).getTotalTicks(), 1);
		assertEquals(q.getSpecific(1).getTotalTicks(), 1);
		assertEquals(q.getSpecific(2).getTotalTicks(), 1);
	}
}
