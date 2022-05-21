package blackjack5;

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

public class JFrameBJ extends JFrame {
	
	private BlackJack blackJack;
	private JTextArea textArea = new JTextArea();
	boolean isNPressed;
	
	public JFrameBJ() {
		setTitle("Let's play BlackJack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 620);
		setLocationRelativeTo(null);
		
		JPanel panel = (JPanel) getContentPane();
		textArea.setFont(new Font("courier New", Font.PLAIN, 30));
//		add(textArea);
		panel.add(textArea);
		textArea.setEditable(false);
			
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "one");
		panel.getActionMap().put("one", onePressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
		panel.getActionMap().put("up", upPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
		panel.getActionMap().put("down", downPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), "h");
		panel.getActionMap().put("h", hPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "s");
		panel.getActionMap().put("s", sPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0), "y");
		panel.getActionMap().put("y", yPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "n");
		panel.getActionMap().put("n", nPressed);
			panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		panel.getActionMap().put("enter", enterPressed);
		isNPressed = false;
		setVisible(true);		
		blackJack = new BlackJack(textArea);
		GameThread gameThread = new GameThread();
		gameThread.start();
	}
	
	Action onePressed = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				textArea.append("one\n");
				blackJack.setKey('1');
				blackJack.bet();
			}
	};
	Action upPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("up Pressed\n");
			blackJack.setKey('u');
			blackJack.betUp();
		}
	};
	Action downPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("down Pressed\n");
			blackJack.setKey('d');
			blackJack.betDown();
		}
	};
	Action hPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("h Pressed\n");
			blackJack.setKey('h');
			blackJack.hitOrStand('h');
		}
	};
	Action sPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("s Pressed\n");
			blackJack.setKey('s');
			blackJack.hitOrStand('s');
		}
	};
	Action yPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("y Pressed\n");
			blackJack.setKey('y');
			blackJack.bet();
		}
	};
	Action nPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("n Pressed\n");
			blackJack.setKey('n');
			isNPressed = true;
			blackJack.stopGame();
		}
	};
	Action enterPressed = new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
//			textArea.append("enter Pressed\n");
			blackJack.setKey('e');
			System.out.println("Enter pressed");
//			new JFrameBJ();
			blackJack.play();
		}
	};
	
	public static void main(String[] args) {
		new JFrameBJ();
		System.out.println("program 종료");
	}
	
//	public synchronized void methodA() {
//		
//	}
	
	class GameThread extends Thread {

		public void run() {
//			blackJack.play();
//			initData();
//			setUpGame();
//			playGame();
//			judge();
			blackJack.initData();
			blackJack.start();
		}
	}

	
//	class InputThread extends Thread {
//
//		int total;
//		
//		@Override
//		public synchronized void run() {
//			synchronized (this) {
//				while(!isNPressed) {
//					System.out.println("nothing");
//				}
////				for (int i = 0; i < 10; i++) {
////					System.out.println("total에 " + i + " 더하기");
////					total += i;
////				}
////				try {
////					Thread.sleep(1000);
////				} catch(InterruptedException e) {
////					e.printStackTrace();
////				}
////				notify();
//			}
//		}
//		
//	}

}
