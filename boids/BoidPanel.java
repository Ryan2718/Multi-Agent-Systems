package boids;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/** Defines an overall panel for the simulation.
 * @author Ryan Forsyth
 */
public class BoidPanel extends JPanel{
	
	// Data Fields
	private static final long serialVersionUID = 1L;
	private final int SKY_HEIGHT = 590;
	private final int CONTROL_HEIGHT = 20;
	private final int WIDTH = 800;
	
	/** Constructor for the BoidPanel. */
	public BoidPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setPreferredSize(new Dimension(WIDTH, SKY_HEIGHT + CONTROL_HEIGHT));
		Sky sky = new Sky(WIDTH, SKY_HEIGHT);
		sky.setPreferredSize(new Dimension(WIDTH, SKY_HEIGHT));
		ControlPanel control = new ControlPanel(sky);
		control.setPreferredSize(new Dimension(WIDTH, CONTROL_HEIGHT));
		add(control);
		add(sky);
	}
	
}
