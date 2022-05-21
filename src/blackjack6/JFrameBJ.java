package blackjack6;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.MenuKeyEvent;

import blackjack6.BlackJack.Status;

public class JFrameBJ extends JFrame {
	
	private BlackJack blackJack;
	private JTextArea textArea = new JTextArea();
	boolean isNPressed;
	
	public JFrameBJ() {
		setTitle("Let's play BlackJack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 620);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = (JPanel) getContentPane();
		textArea.setFont(new Font("courier New", Font.PLAIN, 30));
		panel.add(textArea);
		textArea.setEditable(false);
			
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "one");
		panel.getActionMap().put("one", onePressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "two");
		panel.getActionMap().put("two", twoPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		panel.getActionMap().put("up", upPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
		panel.getActionMap().put("down", downPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		panel.getActionMap().put("enter", enterPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
		panel.getActionMap().put("h", hPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "s");
		panel.getActionMap().put("s", sPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "y");
		panel.getActionMap().put("y", yPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "n");
		panel.getActionMap().put("n", nPressed);
		
		setVisible(true);		
		blackJack = new BlackJack(textArea);
		new Thread(new GameThread()).start();
	}
	
	Action onePressed = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (blackJack.status == Status.START) blackJack.bet();
			}
	};
	Action twoPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.START) {
				blackJack.continueGame();
				blackJack.bet();
			}
		}
	};
	Action upPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.BET) blackJack.betUp();
		}
	};
	Action downPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.BET) blackJack.betDown();		}
	};
	Action enterPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.BET && blackJack.betMoney != 0) {
				blackJack.play();
				if (blackJack.player.isOver()) blackJack.judge();
			}
		}
	};
	Action hPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.PLAY) {
				blackJack.play();
				if (blackJack.player.isOver()) blackJack.judge();
			}
		}
	};
	Action sPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.PLAY) blackJack.judge();
		}
	};
	Action yPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.RESULT && !blackJack.isGameOver) blackJack.bet();
		}
	};
	Action nPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (blackJack.status == Status.RESULT) {
				blackJack.gameOver();
				blackJack.save();
			}
		}
	};
	
	public static void main(String[] args) {
		new JFrameBJ();
	}

	class GameThread implements Runnable {

		public void run() {
			blackJack.start();
		}
		
	}

}
