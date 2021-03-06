package code;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;

/**
 * Table acts like the 'board'. It keeps track of the cards being played and the
 * current play.
 * 
 * @author Carrie
 */
public class Table extends GUI {
	public static final int TABLE_TOP = 50;
	public static final int TABLE_LEFT = 100;
	public static final int TABLE_SIZE = 500;

	private List<Card> played; // list of cards that have already been played

	/** Creates a new game, with no cards played yet. */
	public Table() {
		game = new Game();
		played = new ArrayList<Card>();
	}

	/**
	 * Sets up the game, by allowing the user to determine no. of players and then
	 * setting up players' cards. Then it displays the ongoing game.
	 */
	private void setUpGame() {
		List<Player> players = new ArrayList<Player>();

		// get user name
		String playername = (String) JOptionPane.showInputDialog(frame, "Enter your name: ");

		// create the player instance for user
		players.add(new Player(new RealPlayerStrategy(playername)));

		// get no. of players
		String[] noOfPlayers = { "2", "3", "4" };
		String userInput = (String) JOptionPane.showInputDialog(frame, "Select no. of players:", "Players",
				JOptionPane.QUESTION_MESSAGE, null, noOfPlayers, noOfPlayers[0]);

		// add the rest of the players
		int np = Integer.parseInt(userInput);
		for (int i = 1; i < np; i++)
			players.add(new Player(new SimulatedPlayerStrategy("Player " + i)));

		game.setPlayers(players); // add players to game
		render(); // render play state
	}

	/**
	 * Play a card on the table.
	 * 
	 * @param c
	 *            The card that is played
	 */
	public void playCard(Card c) {
		played.add(c);
	}

	/**
	 * Gets the played cards. Usually used to add cards to the losing player's hand.
	 * 
	 * @return the cards that have already been played
	 */
	public List<Card> getPlayed() {
		List<Card> res = new ArrayList<Card>();
		while (!played.isEmpty())
			res.add(played.remove(0));

		return res;
	}

	/**
	 * If the game is in play state, then the current game will be rendered. It will
	 * render the ongoing game as long as the game isn't finished.
	 */
	public void render() {
		// render the starting status -- before game plays
		container.removeAll(); // clear the container of previous items
		// Graphics2D drawing = (Graphics2D) drawingArea;
		// Border tableEdge = BorderFactory.createCompoundBorder(
		// BorderFactory.createRaisedBevelBorder(),
		// BorderFactory.createLoweredBevelBorder());
		// canvas.setBorder(tableEdge);

		if (drawingArea != null) {
			System.out.println("graphics set color");
			drawingArea.setColor(Color.RED); // new Color(102,51,0) brown
			System.out.println("graphics color set successful");
			drawingArea.drawRect(TABLE_LEFT, TABLE_TOP, TABLE_SIZE, TABLE_SIZE);
		}

		canvas.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
		container.add(canvas); // add canvas back
		frame.repaint();
		
		// play game this method will render the game while it's not over
		game.playGame(this, drawingArea);
		// transition state -> if it reaches here, the game is over
		currentState = currentState.transition(game);
	}

	/**
	 * Displays the starting screen of the game. This includes the instructions of
	 * the game play as well as the start button. When the user decides to play, the
	 * game state transitions to the next state.
	 */
	public void renderStart() {
		container.setPreferredSize(new Dimension(CANVAS_SIZE, CANVAS_SIZE));
		container.setLayout(new GridBagLayout());

		// text that describes how the game works
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 1;
		cc.gridy = 0;
		JTextPane instructions = new JTextPane();
		instructions.setEditable(false);
		instructions.setPreferredSize(new Dimension(500, 250));
		String desc = "Aim: Get rid of all the cards in your hand.\n\n"
				+ "The first one to empty their hand does not need to continue "
				+ "with the game. The rest of the players may go ahead, "
				+ "and try to complete the aim of the game.\n\n" + "The game goes around the table, starting from a"
				+ " player chosen at random. The cards on your hand "
				+ "are facedown. As the play goes around, each player"
				+ " speaks out the possible rank of the card -- starting from ACE --" + " in ascending order.\n\n"
				+ "When your rank and the actual card's rank match,"
				+ " everyone on the table will place their hand on "
				+ "the cards played. The cards go to the one whose hand is on "
				+ "top, thus increasing the player's hand.";
		instructions.setText(desc);
		// TODO: style text pane HERE
		container.add(instructions, cc);

		// button that initiates the game
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentState = currentState.transition(game);
				setUpGame();
			}
		});
		start.setPreferredSize(new Dimension(200, 50));
		start.setFont(new Font("Arial", Font.BOLD, 25));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;

		container.setBackground(Color.GREEN.darker());
		container.add(start, c);
	}

	/**
	 * If the game is in end state, then the game over message will be displayed.
	 */
	@Override
	public void renderEnd() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Table();
	}

}
