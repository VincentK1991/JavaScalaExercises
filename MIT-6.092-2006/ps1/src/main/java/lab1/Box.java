/**
 * This is part of the Problem Set 0: Introduction for 6.170 Fall 2005.
 */
package lab1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is a container can be used to contain Balls. The key difference between
 * a BallContainer and a Box is that a Box has a finite capacity. Once a box is
 * full, we cannot put in more Balls.
 */
public class Box extends BallContainer {

	private final double maxCapacity;

	/**
	 * Constructor that creates a new box.
	 *
	 * @param capacity Total capacity of balls that this box can contain.
	 */
	public Box(double capacity) {
		maxCapacity = capacity;
	}

	/**
	 * This method replaces (overrides) the <tt>add(Ball)</tt> method in
	 * <tt>BallContainer</tt>, because we have to check if the <tt>Box</tt>
	 * has enough remaining space when we try to put a Ball inside. Hence, this
	 * method returns true if a ball is successfully added to the box, i.e.,
	 * ball is not already in the box and if the box is not already full; and it
	 * returns false, if ball is already in the box or if the box is too full to
	 * contain the new ball.
	 *
	 * @param b Ball to be added.
	 * @return true if ball was successfully added to the box, i.e. ball is not
	 * already in the box and if the box is not already full. Returns
	 * false, if ball is already in the box or if the box is too full to
	 * contain the new ball.
	 */
	public boolean add(Ball b) {
		if (b.getCapacity() + super.getVolume() <= maxCapacity) {
			return super.add(b);
		}
		return false;
	}

	/**
	 * This method returns an iterator that returns all the balls in this box in
	 * ascending size, i.e., return the smallest Ball first, followed by Balls
	 * of increasing size.
	 *
	 * @return an iterator that returns all the balls in this box in ascending
	 * size.
	 */
	public Iterator<Ball> getBallsFromSmallest() {
		//throw new RuntimeException("implement me!");
		LinkedList<Ball> copyContent = new LinkedList<Ball>(contents);
		Collections.sort(copyContent, new Comparator<Ball>() {
			public int compare(Ball b1, Ball b2) {
				return (int) (b1.getCapacity() - b2.getCapacity());
			}
		});
		Iterator<Ball> tempIterator = copyContent.iterator();
		while (tempIterator.hasNext()){
			Ball ball = tempIterator.next();
			System.out.println("get from smallest > " + ball.getCapacity());
		}
		return copyContent.iterator();
	};
};
