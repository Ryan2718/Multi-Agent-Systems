package boids;

/** Defines a Boid object.
 * @author Ryan Forsyth
 */
public class Boid {
	
	// Data Fields
	public int row;
	public int col;
	
	/** Constructor for a Boid object.
	 * @param row The initial row
	 * @param col The initial column
	 */
	public Boid(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
}
