package boids;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

// Got some pointers from http://www.vergenet.net/~conrad/boids/pseudocode.html
/** Class that defines the sky the boids will fly in.
 * @author Ryan Forsyth
 */
public class Sky extends JPanel {
	
	// Data Fields
	private static final long serialVersionUID = 1L;
	private static final int BOID_SIZE = 3;
	private ArrayList<Boid> boids;
	private final int WIDTH;
	private final int HEIGHT;
	private Timer timer;
	
	/** Constructor
	 * @param width Width of the sky
	 * @param height Height of the sky
	 */
	public Sky(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		/* Notes:
		 * Larger x => further to the right
		 * Larger y => further down page
		 */
		if (boids != null) {
			for (Boid boid : boids) {
				page.fillRect(boid.col, boid.row, BOID_SIZE, BOID_SIZE);
			}
		}
	}
	
	/** Implements the centering force on the boids.
	 * @param cohesion The degree of cohesion
	 */
	private void center(double cohesion) {
		int colCenter = 0;
		int rowCenter = 0;
		for (Boid boid: boids) {
			colCenter += boid.col;
			rowCenter += boid.row;
		}
		colCenter /= boids.size();
		rowCenter /= boids.size();
		for (Boid boid: boids) {
			int col = boid.col;
			int row = boid.row;
			double probability = Math.random();
			if (probability < cohesion) {
				if (colCenter > col) {
					boid.col = (boid.col + 1) % WIDTH;
				} else if (colCenter < col) {
					if (boid.col != 0) {
						boid.col = (boid.col - 1) % WIDTH;
					} else {
						boid.col = WIDTH - 1;
					}
				}
				if (rowCenter > row) {
					boid.row = (boid.row + 1) % HEIGHT;
				} else if (rowCenter < row) {
					if (boid.row != 0) {
						boid.row = (boid.row - 1) % HEIGHT;
					} else {
						boid.row = HEIGHT - 1;
					}
				}
			} else {
				double p1 = Math.random();
				if (p1 < 0.50) {
					boid.row = (boid.row + 1) % HEIGHT;
				} else {
					if (boid.row != 0) {
						boid.row = (boid.row - 1) % HEIGHT;
					} else {
						boid.row = HEIGHT - 1;
					}
				}
				double p2 = Math.random();
				if (p2 < 0.50) {
					boid.col = (boid.col + 1) % WIDTH;
				} else {
					if (boid.col != 0) {
						boid.col = (boid.col - 1) % WIDTH;
					} else {
						boid.col = WIDTH - 1;
					}
				}
			}
		}
	}
	
	/** The separation force on the boids.
	 * @param separation How far apart the boids should be
	 */
	private void seperate(int separation) {
		for (Boid boid1: boids) {
			for (Boid boid2 : boids) {
				if (Math.abs(boid1.row - boid2.row) < separation) {
					if (boid1.row > boid2.row) {
						boid1.row = (boid1.row + 1) % HEIGHT;
					} else {
						if (boid1.row != 0) {
							boid1.row = (boid1.row - 1) % HEIGHT;
						} else {
							boid1.row = HEIGHT - 1;
						}
					}
				}
				if (Math.abs(boid1.col - boid2.col) < separation) {
					if (boid1.col > boid2.col) {
						boid1.col = (boid1.col + 1) % WIDTH;
					} else {
						if (boid1.col != 0) {
							boid1.col = (boid1.col - 1) % WIDTH;
						} else {
							boid1.col = WIDTH - 1;
						}
					}
				}
			}
		}
	}
	
	/** Initialize the sky.
	 * @param numBoids The number of boids
	 * @param cohesion The degree of cohesion
	 * @param separation How far apart the boids should be
	 */
	public void initialize(int numBoids, double cohesion, int separation) {
		boids = new ArrayList<Boid>();
		for (int i = 0; i < numBoids; i++) {
			int randomRow = (int) (Math.random() * HEIGHT);
			int randomCol = (int) (Math.random() * WIDTH);
			boids.add(new Boid(randomRow, randomCol));
		}
		
	}
	
	/** Run the simulation.
	 * @param delay The delay between updates
	 * @param cohesion The degree of cohesion
	 * @param separation How far apart the boids should be
	 */
	public void run(int delay, double cohesion, int separation) {
		if (timer != null) {
			timer.stop();
		}
		timer = new Timer(delay, new TimeListener(cohesion, separation));
		timer.setDelay(delay);
		timer.start();
	}
	
	/** Class that defines a listener for the timer.
	 * @author Ryan Forsyth
	 */
	private class TimeListener implements ActionListener {
		
		// Data Fields
		private double cohesion;
		private int seperation;
		
		/** Constructor for a TimeListener.
		 * @param cohesion
		 * @param separation
		 */
		public TimeListener(double cohesion, int separation) {
			this.cohesion = cohesion;
			this.seperation = separation;
		}
		
		public void actionPerformed(ActionEvent event) {
			center(cohesion);
			seperate(seperation);
			revalidate();
			repaint();
		}
	}
}
