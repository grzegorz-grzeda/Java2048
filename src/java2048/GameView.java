package java2048;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GameView extends JFrame implements IGameView, KeyListener {
	private static final int DIMENSION = 50;
	private int SIDE = 4;

	IGameControllerFeedback feedback = null;

	JTextField[] gameField;
	JPanel gamePanel = new JPanel();
	JPanel statusPanel = new JPanel();
	JLabel scoreLabel = new JLabel("Score:");
	JLabel scoreResultLabel = new JLabel("");
	JLabel turnLabel = new JLabel("Turn:");
	JLabel turnResultLabel = new JLabel("");
	JLabel statusLabel = new JLabel("Status:");
	JLabel statusResultLabel = new JLabel("");

	public GameView(int side) {
		SIDE = side;
		gameField = new JTextField[SIDE * SIDE];

		setLayout(new BorderLayout());
		configureGamePanel();
		configureStatusPanel();
		setMinimumSize(new Dimension(SIDE * DIMENSION, (SIDE + 1) * DIMENSION));
		setResizable(false);
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void attatchInputListener(IGameControllerFeedback feedback) {
		this.feedback = feedback;
	}

	private void configureGamePanel() {
		gamePanel.setPreferredSize(new Dimension(SIDE * DIMENSION, SIDE * DIMENSION));
		gamePanel.setLayout(new GridLayout(SIDE, SIDE));
		for (int i = 0; i < gameField.length; i++) {
			gameField[i] = new JTextField();
			gameField[i].setEnabled(false);
			gameField[i].setHorizontalAlignment(JTextField.CENTER);
			gameField[i].setText("");
			gamePanel.add(gameField[i]);
		}
		add(gamePanel, BorderLayout.CENTER);
	}

	private void configureStatusPanel() {
		statusPanel.setPreferredSize(new Dimension(SIDE * DIMENSION, DIMENSION));
		statusPanel.setLayout(new GridLayout(3, 2));
		statusPanel.add(scoreLabel);
		statusPanel.add(scoreResultLabel);
		statusPanel.add(turnLabel);
		statusPanel.add(turnResultLabel);
		statusPanel.add(statusLabel);
		statusPanel.add(statusResultLabel);
		add(statusPanel, BorderLayout.PAGE_END);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (null == feedback)
			return;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			feedback.reactForUp();
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			feedback.reactForDown();
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			feedback.reactForLeft();
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			feedback.reactForRight();
			break;
		case KeyEvent.VK_R:
			feedback.reactForReset();
		case KeyEvent.VK_U:
			feedback.reactForUndo();
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void setGameField(String[] numbers) {
		for (int i = 0; (i < numbers.length) && (i < (SIDE * SIDE)); i++) {
			gameField[i].setText(numbers[i]);
		}
	}

	@Override
	public void setScore(int score) {
		scoreResultLabel.setText("" + score);
	}

	@Override
	public void setStatus(String text) {
		statusResultLabel.setText(text);
	}

	@Override
	public void setTurn(int turn) {
		turnResultLabel.setText("" + turn);
	}
}
