package boids;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Control Panel for the simulation.
 * @author Ryan Forsyth
 */
public class ControlPanel extends JPanel {
	
	// Data Fields
	private static final long serialVersionUID = 1L;
	private final Sky SKY;
	private final JLabel SLIDER_LABEL = new JLabel("Speed: ");
	private final JLabel NUMBER_LABEL = new JLabel("Number (int): ");
	private final JLabel COHESION_LABEL = new JLabel("Cohesion (double): ");
	private final JLabel SEPARATION_LABEL = new JLabel("Separation (int): ");
	private final JSlider SLIDER = new JSlider(JSlider.HORIZONTAL, 0, 10, 1);
	private final JTextField NUMBER = new JTextField();
	private final JTextField COHESION =  new JTextField();
	private final JTextField SEPARATION = new JTextField();
	private final JButton GO = new JButton("Go!");
	private int delay = 1;
	
	/** Constructor for a ControlPanel.
	 * @param sky The Sky object being controlled
	 */
	public ControlPanel(Sky sky) {
		SKY = sky;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		GO.addActionListener(new ButtonListener());
		SLIDER.addChangeListener(new SliderListener());
		add(SLIDER_LABEL);
		add(SLIDER);
		add(NUMBER_LABEL);
		add(NUMBER);
		add(COHESION_LABEL);
		add(COHESION);
		add(SEPARATION_LABEL);
		add(SEPARATION);
		add(GO);
	}
	
	/** Defines a listener to the GO button.
	 * @author Ryan Forsyth
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == GO) {
				try {
					int num = Integer.valueOf(NUMBER.getText());
					double cohesion = Double.valueOf(COHESION.getText());
					int separation = Integer.valueOf(SEPARATION.getText());
					SKY.initialize(num, cohesion, separation);
					SKY.run(delay, cohesion, separation);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, 
												"Please only enter numbers");
				}
			}
		}
	}
	
	/** Defines a listener to the speed slider.
	 * @author Ryan Forsyth
	 */
	private class SliderListener implements ChangeListener {
		public void stateChanged(ChangeEvent event) {
			int value = SLIDER.getValue();
			delay = 5*(10 - value);
		}
	}
}
