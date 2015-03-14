package boids;

import javax.swing.JFrame;

/** Class to run the simulation.
 * @author Ryan Forsyth
 */
public class BoidSimulation {
	
	/** Run the simulation!
	 * @param args Unused command line arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Boids");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BoidPanel());
		frame.pack();
		frame.setVisible(true);
	}
	
}
