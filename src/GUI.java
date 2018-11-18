import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Class that sets up the graphical representation of the game.
 * 
 * @author Carrie
 */
public abstract class GUI {
	// ABSTRACT METHODS:
	/** Renders the container based on the game state. */
	public abstract void renderStart(Graphics g);
	public abstract void render(Graphics g);
	public abstract void renderEnd(Graphics g);
	
	// Constants
	protected static final int FRAME_SIZE = 800;
	protected static final int CANVAS_SIZE = 600;
	
	// JSwing fields
	protected JFrame frame;
	protected JComponent container;
	protected Graphics drawingArea;
	
	
	// Game related fields
	protected State currentState;
	protected Game game;

	/** Sets up the basic skeletal framework of the table. */
	public GUI() {
		frame = new JFrame("SNAP");
		init();
	}
	
	/**
	 * Sets up the framework of the table. This includes the
	 * frame, and various components, panels, buttons, etc.
	 * All user response is handled and delegated to the
	 * Table class.
	 * Also sets up game state (initially at start state).
	 */
	private void init() {
		currentState = State.START;
		container = new JComponent() {
			// assign the graphics object first, so it's accessible
			@Override
			protected void paintComponent(Graphics g) {
				drawingArea = g;
				renderStart(drawingArea);	// renders initial state
				//container.repaint();
			}
		};
		container.repaint();
		
		// spacing between container and frame HERE
		frame.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE));
		
		frame.add(container);
		
		frame.pack();
		frame.setVisible(true);
	}

}
