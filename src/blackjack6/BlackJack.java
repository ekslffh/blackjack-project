package blackjack6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;

/**
 * @author 20170935 나성민
 */
public class BlackJack {

	// DATA 
	
	private final int SCREEN_WIDTH = 53;
	private final int LEFT_PADDING = 1;
	private final int SCREEN_HEIGHT = 20;

	public Dealer dealer;
	public Player player;
	private Card hiddenCard;

	private JTextArea textArea;
	private char[][] buffer;
	Status status;
	public int betMoney;
	int earnedMoney;
	GameResult gameResult;
	public boolean isGameOver;

	public File importData;

	enum GameResult { PLAYER_BUSTED, PLAYER_WINS, DEALER_BUSTED, DEALER_WINS, NOBODY;
		@Override
		public String toString() {
			String result = this.name();
			int index = result.indexOf('_');
			return (result.substring(0, index) + " " + result.subSequence(index + 1, result.length()));
		}
	}

	enum Status { START, BET, PLAY, JUDGE, RESULT, GAMEOVER }

	// COMPONENT

	public void baseComponent() {
		drawBuffer(3, 2, "------------ Jack's BlackJack Game ------------");
		drawBuffer(3, 9, "-----------------------------------------------");
	}

	public void menuComponent() {
		drawBuffer(5, 4, "1. START NEW GAME");
		if (importData.exists())
			drawBuffer(5, 6, "2. CONTINUE GAME");
		drawBuffer(12, 12, "Select :");
	}

	public void betComponent() {
		drawBuffer(4, 7, "# Dealer($" + dealer.money + ")");
		drawBuffer(4, 8, "# Player($" + player.money + ")");
		drawBuffer(9, 12, "How much money do you want to bet?");
		drawBuffer(15, 14, betMoney + " (Up/Down/Enter)");
	}

	public void playComponent() {
		drawBuffer(4, 3, "# Betting : $" + betMoney);
		drawBuffer(4, 7, "# Dealer($" + dealer.money + ")" + dealer.getCardsToString());
		drawBuffer(4, 8, "# Player($" + player.money + ")" + player.getCardsToString());
		drawBuffer(9, 12, "Hit or Stand? (H/S):");
	}

	public void resultComponent() {
		drawBuffer(4, 3, "# Betting : $" + betMoney);
		drawBuffer(4, 7, "# Dealer($" + dealer.money + ")" + dealer.getCardsToString());
		drawBuffer(4, 8, "# Player($" + player.money + ")" + player.getCardsToString());
		if (!isGameOver) {
			drawBuffer(9, 12, gameResult.toString() + "...");
			drawBuffer(9, 14, "Play Again? (Y/N):");
		} else {
			drawBuffer(9, 12, "You lost EVERYTHING! Quit Game!");
			isGameOver = true;
		}
	}

	public void gameOverComponent() {
		drawBuffer(4, 4, ((earnedMoney > 0) ? "You earned $" + earnedMoney : "You lost $" + (earnedMoney * -1)));
	}

	// CONTROLLER

	public void initData() {
		for (int y = 0; y < SCREEN_HEIGHT; y++) {
			for (int x = 0; x < SCREEN_WIDTH; x++) {
				buffer[x][y] = ' ';
			}
		}
		baseComponent();
	}

	public BlackJack(JTextArea ta) {
		textArea = ta;
		dealer = new Dealer(10000);
		player = new Player(100);
		buffer = new char[SCREEN_WIDTH][SCREEN_HEIGHT];
		importData = new File("text.txt");
	}

	public void start() {
		status = Status.START;
		isGameOver = false;
		initData();
		menuComponent();
		render();
	}

	public void continueGame() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("text.txt"));
			String str;

			str = in.readLine();
			int dealerMoney = Integer.parseInt(str.substring(str.indexOf("$") + 1, str.indexOf(")")));
			str = in.readLine();
			int playerMoney = Integer.parseInt(str.substring(str.indexOf("$") + 1, str.indexOf(")")));

			dealer.money = dealerMoney;
			player.money = playerMoney;

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bet() {
		status = Status.BET;
		player.initCards();
		dealer.initCards();
		betMoney = 0;
		gameResult = GameResult.NOBODY;
		clearContents();
		betComponent();
		render();
	}

	public void betUp() {
		if (betMoney < player.money) {
			betMoney += 10;
		}
		clearContents();
		betComponent();
		render();
	}

	public void betDown() {
		if (betMoney > 0) {
			betMoney -= 10;
		}
		clearContents();
		betComponent();
		render();
	}

	public void setUpGame() {
		player.addCard(dealer.drawCard());
		dealer.addCard();
		player.addCard(dealer.drawCard());
		hiddenCard = dealer.drawCard();
	}

	public void play() {
		if (status == Status.BET) {
			setUpGame();
			status = Status.PLAY;
		} else { // hit
			player.addCard(dealer.drawCard());
		}

		if (player.isOver()) {
			if (player.totalScore == 21)
				return;
			while (player.changedValueA() && player.isOver()) {
			}
			if (player.isOver())
				return;
		}

		// view
		clearContents();
		playComponent();
		render();

	}

	public void calcBetMoney(char winner) {
		if (winner == 'p') {
			player.money += betMoney;
			dealer.money -= betMoney;
			earnedMoney += betMoney;
		} else { // 'd'
			dealer.money += betMoney;
			player.money -= betMoney;
			earnedMoney -= betMoney;
		}
	}

	public void judge() {
		status = Status.JUDGE;
		dealer.addCard(hiddenCard);

		// player blackjack
		if (player.getTotalScore() == 21) {
			gameResult = GameResult.PLAYER_WINS;

		}
		// player busted
		else if (player.getTotalScore() > 21) {
			gameResult = GameResult.PLAYER_BUSTED;
		}

		else {
			while (!(dealer.isOver())) {
				dealer.addCard();
				if (dealer.isOver()) {
					while (dealer.changedValueA() && dealer.isOver()) {
					}
				}
			}
			// dealer blackjack
			if (dealer.getTotalScore() == 21) {
				gameResult = GameResult.DEALER_WINS;
			}
			// dealer busted
			else if (dealer.getTotalScore() > 21) {
				gameResult = GameResult.DEALER_BUSTED;
			}

			// 둘다 21 미만인 상태이므로 totalScore가 더 큰 쪽이 이긴다.
			else {
				if (player.getTotalScore() > dealer.getTotalScore()) {
					gameResult = GameResult.PLAYER_WINS;
				} else if (player.getTotalScore() < dealer.getTotalScore()) {
					gameResult = GameResult.DEALER_WINS;
				} else {
					gameResult = GameResult.NOBODY;
				}
			}
		}

		if (gameResult == GameResult.PLAYER_WINS || gameResult == GameResult.DEALER_BUSTED) {
			calcBetMoney('p');
		} else if (gameResult == GameResult.DEALER_WINS || gameResult == GameResult.PLAYER_BUSTED) {
			calcBetMoney('d');
		}

		if (player.money <= 0) {
			isGameOver = true;
			importData.delete();
		}
		status = Status.RESULT;
		clearContents();
		resultComponent();
		render();
	}

	public void gameOver() {
		status = Status.GAMEOVER;
		clearContents();
		gameOverComponent();
		render();
	}
	
	public void save() {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("text.txt"));
			out.write("# Dealer($" + dealer.money + ")");
			out.newLine();
			out.write("# Player($" + player.money + ")");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearContents() {
		clearOutputBuffer();
		clearInputBuffer();
	}
	
	// x = 3~49, y = 3~8
	public void clearOutputBuffer() {
		for (int y = 3; y <= 8; y++) {
			for (int x = 3; x <= 49; x++) {
				buffer[x][y] = ' ';
			}
		}
	}

	// x = 9 ~ 42 , y = 12~14
	public void clearInputBuffer() {
		for (int y = 12; y <= 14; y++) {
			for (int x = 9; x <= 42; x++) {
				buffer[x][y] = ' ';
			}
		}
	}

	public void drawBuffer(int px, int py, String c) {
		for (int x = 0; x < c.length(); x++) {
			buffer[px + x][py] = c.charAt(x);
		}
	}

	public void render() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < SCREEN_HEIGHT; y++) {
			for (int x = 0; x < SCREEN_WIDTH; x++) {
				sb.append(buffer[x][y]);
			}
			sb.append("\n");
		}
		textArea.setText(sb.toString());
	}

}
