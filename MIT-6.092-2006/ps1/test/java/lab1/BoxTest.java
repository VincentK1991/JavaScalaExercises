/**
 * This is part of the Problem Set 0: Introduction for 6.170 Fall 2005.
 */

package lab1;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * BoxTest is a glassbox test of the Box class. 
 *
 * Recall that like a BallContainer, the Box is a container for Balls and you
 * can only put a Ball into a Box once. After you put the Ball into
 * the Box, further attempts to do so will fail, since the Ball is
 * already in the Box! Similarly, you cannot expect to remove a Ball
 * from a Box if it is not inside the Box.
 *
 * In addition, a Box differs from a ballcontainer because it only has a finite
 * volume. As Balls get put into a Box, it gets filled up. Once a Box
 * is full, further attempts to put Balls into the Box will also fail.
 *
 * @see lab1.Ball
 * @see lab1.BallContainer
 * @see lab1.Box
 */
public class BoxTest<b> {

    private Box box = null;
    private int NUM_BALLS_TO_TEST = 5;
    private int BOX_CAPACITY = NUM_BALLS_TO_TEST - 1;
    private Ball[] b = null; // array of Ball
    private double BALL_UNIT_VOLUME = 10.0;
    private double JUNIT_DOUBLE_DELTA = 0.0001;
    private int TRIES_FOR_BALLS_TEST = 3;

	double box_volume = 0;

	public BoxTest(){
		b = new Ball[NUM_BALLS_TO_TEST];
		for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
			if (i<BOX_CAPACITY) {
				box_volume += (i+1)*BALL_UNIT_VOLUME;
			}
			b[i] = new Ball((i+1)*BALL_UNIT_VOLUME);
		}
		box = new Box(box_volume);
	}

    @Test
    protected void setUp(){

		//assertEquals("Test case error, you must test at least 1 Ball!!", true, NUM_BALLS_TO_TEST > 0);
		//assertEquals("This test case is set up assuming that the box cannot contain all the balls, please check and change parameters!", true, NUM_BALLS_TO_TEST > BOX_CAPACITY);

		double box_volume = 0;

		// Let's create the balls we need.
//		b = new Ball[NUM_BALLS_TO_TEST];
//		for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
//			if (i<BOX_CAPACITY) {
//				box_volume += (i+1)*BALL_UNIT_VOLUME;
//			}
//			b[i] = new Ball((i+1)*BALL_UNIT_VOLUME);
//		}
//
//		// Now, we create the box once we figure out how big a box we
//		// need.
//		box = new Box(box_volume);

		assertEquals(true, NUM_BALLS_TO_TEST > 0,"Test case error, you must test at least 1 Ball!!");
		assertEquals(true, NUM_BALLS_TO_TEST > BOX_CAPACITY, "This test case is set up assuming that the box cannot contain all the balls, please check and change parameters!");
	}
    
    /** Test to check that Box.add(Ball) is implemented correctly */
    @Test
    public void testAdd() {
//		double box_volume = 0;
//
//		b = new Ball[NUM_BALLS_TO_TEST];
//		for (int i=0; i<NUM_BALLS_TO_TEST; i++) {
//			if (i<BOX_CAPACITY) {
//				box_volume += (i+1)*BALL_UNIT_VOLUME;
//			}
//			b[i] = new Ball((i+1)*BALL_UNIT_VOLUME);
//		}
//
//		box = new Box(box_volume);
		box.clear();
		for (int i=0; i<BOX_CAPACITY; i++) {
			assertEquals(true, box.add(b[i]),"Box.add(Ball) failed to add a new Ball!");
			assertEquals(false, box.add(b[i]),"Box.add(Ball) seems to allow the same Ball to be added twice!");
			assertEquals(true, box.contains(b[i]),"Box does not contain a ball after it is supposed to have been added!");
		}
		for (int i=BOX_CAPACITY; i<NUM_BALLS_TO_TEST; i++) {
			assertEquals(false, box.add(b[i]), "Box.add(Ball) allows a Ball to be added even though it is already full!");
		}
    }

    /** Test to check that Box.getBallsFromSmallest() is implemented correctly */
    @Test
    public void testGetBalls() {
		Random rnd = new Random();

		for (int k=0; k<TRIES_FOR_BALLS_TEST; k++) {

			box.clear();

			// Let's put all the balls into a list.
			LinkedList<Ball> list = new LinkedList<Ball>();
			for (int i=0; i<BOX_CAPACITY; i++) {
			list.add(b[i]);
			}

			// First we add the balls to the box in some random order.
			for (int i=0; i<BOX_CAPACITY; i++) {
			box.add(list.remove(rnd.nextInt(list.size())));
			}

			// Next we call the iterator and check that the balls come out in the correct order.
			Iterator<Ball> it = box.getBallsFromSmallest();
			int count = 0;
			while (it.hasNext() && count < BOX_CAPACITY) {
				Ball ball = it.next();
				//System.out.println(ball.getCapacity());
				assertEquals( true, b[count] == ball, "Balls are not returned by Box.getBallsFromSmallest() iterator in the correct order");
				if (b[count] != ball) {
					break;
				}
				count++;
			}
			assertEquals(BOX_CAPACITY, count, "Box.getBallsFromSmallest() did not return all the balls");
		}
    }

    /**
     * Test to check that Box.remove(Ball) is implemented
     * correctly. Depending on how <code>getBallsFromSmallest()</code>
     * is implemented, remove() might have to be overridden and this
     * test helps ensure that remove() is not broken in the process.
     */
    @Test
    public void testRemove() {
	box.clear();
        assertEquals(false, box.remove(b[0]), "Box.remove(Ball) should fail because box is empty, but it didn't!");
	for (int i=0; i<BOX_CAPACITY; i++) {
	    box.clear();
	    for (int j=0; j<i; j++) {
		box.add(b[j]);
	    }
	    for (int j=0; j<i; j++) {
		assertEquals(true, box.remove(b[j]), "Box.remove(Ball) failed to remove a Ball that is supposed to be inside");
		assertEquals(false, box.contains(b[j]), "Box still contains a ball after it is supposed to have been removed!");
	    }
	    for (int j=i; j<NUM_BALLS_TO_TEST; j++) {
		assertEquals(false, box.remove(b[j]), "Box.remove(Ball) did not fail for a Ball that is not inside");
	    } 
	}
    }

}
