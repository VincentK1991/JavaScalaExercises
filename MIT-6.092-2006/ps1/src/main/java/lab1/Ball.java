/**
 * This is part of the Problem Set 0: Introduction for 6.170 Fall 2005.
 */
package lab1;

/**
 * This is a simple object that has a capacity.
 */
public class Ball {

	//private double capacity;
	public double capacity;
	/**
	 * Constructor that creates a new ball object with the specified weight and
	 * capacity.
	 * 
	 * @param capacityX
	 *            Capacity of the new object.
	 */
	public Ball(double capacityX) {

		capacity = capacityX;
	}

	/**
	 * Returns the capacity of the Ball.
	 * 
	 * @return the capacity of the Ball.
	 */
	public double getCapacity() {

		return capacity;
	}

}
