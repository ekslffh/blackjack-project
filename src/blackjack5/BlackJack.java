package blackjack5;

import java.util.Scanner;

import javax.swing.JTextArea;

/**
 * 블랙잭 게임을 순서대로 구현한 클래스로 dealer, player의 정보를 갖는다.
 * 1. 딜러와 플레이어를 서로 매핑 시켜준다.
 * 2. 딜러가 각 플레이어에게 2장씩 분배한다.
 * 3. 플레이어는 힛, 스탠드를 통해 딜러로부터 카드를 더 받거나 게임을 멈출 수 있다.
 * 4. 점수를 집계하여 게임의 승패를 가린다.
 * play()를 통해 게임을 진행할 수 있다.
 */
public class BlackJack {
	
	private final int SCREEN_WIDTH = 53;
	private final int LEFT_PADDING = 1;
	private final int SCREEN_HEIGHT = 20;
	
	private Dealer dealer;
	private Player player;
	private Card hiddenCard;
	
	private JTextArea textArea;
	private char[][] buffer;
	private char key;
	public boolean isGameOver;
	public int betMoney;
	int earendMoney;
	
	public void setKey(char key) {
		this.key = key;
		System.out.println(key);
	}
	
	/**
	 * dealer, player 인스턴스 생성 및 관계설정
	 */
	public BlackJack(JTextArea ta) {
		textArea = ta;
		dealer = new Dealer(10000);
		player = new Player(100);
		buffer = new char[SCREEN_WIDTH][SCREEN_HEIGHT];
	}
	
	public void initData() {
		isGameOver = false;
		for (int y = 0; y < SCREEN_HEIGHT; y++) {
			for (int x = 0; x < SCREEN_WIDTH; x++) {
				buffer[x][y] = '.';
			}
		}
		drawBuffer(3, 2, "------------ Jack's BlackJack Game ------------");
		drawBuffer(3, 9, "-----------------------------------------------");
	}
	
	public void start() {
		drawBuffer(5, 4, "1. START NEW GAME");
		drawBuffer(12, 12, "Select :");
		render();
	}
	
	public void bet() {
		this.initData();
		player.initCards();
		dealer.initCards();
		drawBuffer(4, 7, "# Dealer($" + dealer.money + ")");
		drawBuffer(4, 8, "# Player($" + player.money + ")");
		drawBuffer(9, 12, "How much money do you want to bet?");
		drawBuffer(15, 14, betMoney + " (Up/Down/Enter)");
		render();
	}
	
	public void play() {
		if (player.getCards().size() < 2) {
			setUpGame();
		}
		else {
			player.addCard(dealer.drawCard());
		}
		initData();
		drawBuffer(4, 3, "# Betting : $" + betMoney);
		String s = dealer.getCardsToString();
		String a = player.getCardsToString();
		drawBuffer(4, 7, "# Dealer($" + dealer.money + ")" + dealer.getCardsToString());
		System.out.println(dealer.getCardsToString());
		drawBuffer(4, 8, "# Player($" + player.money + ")" + player.getCardsToString());
		drawBuffer(9, 12, "Hit or Stand? (H/S):");
		render();
	}
	
	
	public void betUp() {
		if (betMoney < player.money) {
			betMoney += 10;
		}
		bet();
	}
	
	public void betDown() {
		if (betMoney > 0) {
			betMoney-=10;
		}
		bet();
	}
	
	public void hitOrStand(char key) {
		if (key == 'h') {
			play();
			if(player.isOver()) {
				if(player.totalScore == 21) judge();
				
				while(player.changedValueA() && player.isOver()) {}
				if(player.isOver()) judge();
			}
			
		} else if (key == 's') {
			judge();
		}
	}
	
	public void clearBuffer() {
		for (int y = 0; y < SCREEN_HEIGHT; y++) {
			for (int x = 0; x < SCREEN_WIDTH; x++) {
				buffer[x][y] = ' ';
			}
		}
	}
	
	public void drawBuffer(int px, int py, String c) {
		for (int x = 0; x < c.length(); x++) {
			buffer[px + x][py] = c.charAt(x);
		}
	}
	
	/**
	 * 게임의 순서 (1~3)
	 */
//	public void play() {
//		System.out.println(textArea.getWidth());
//		initData();
//		setUpGame();
//		playGame();
//		judge();
//	}
	
	/**
	 * 1. dealer의 카드분배 및 hiddenCard 저장
	 */
	public void setUpGame() {
		player.addCard(dealer.drawCard());
		dealer.addCard();
		player.addCard(dealer.drawCard());
		hiddenCard = dealer.drawCard();
	}
	
	public void stopGame() {
		initData();
		drawBuffer(4, 4, "You earned $" + earendMoney);
		render();
	}
	
	public void draw() {
		
	}
	
	/**
	 * 2. player hit or stand 선택과정
	 * @throws InterruptedException 
	 */
	public void playGame() {
		
		char input = 0;
		Scanner scanner = new Scanner(System.in);
				
		while(true) {
			// busted
			if(player.isOver()) {
				if(player.totalScore == 21) return;
				
				while(player.changedValueA() && player.isOver()) {}
				if(player.isOver()) return;
			}
			showScreen();
			System.out.print("Hit or Stand? (H/S) : ");
			if(input == 'S' || input == 's') return;
			else if(input == 'H' || input == 'h') player.addCard(dealer.drawCard());
			else {
				System.out.println("Please re-enter");
			}
		}
		
	}
	
	/**
	 * 3. 게임 이후 승패 결정
	 */
	public void judge() {
		dealer.addCard(hiddenCard);
		initData();
		drawBuffer(4, 3, "# Betting : $" + betMoney);
		
		// player blackjack
		if(player.getTotalScore() == 21) {
			drawBuffer(9, 12, "Player Wins...");
			player.money += betMoney;
			dealer.money -= betMoney;
		}
		// player busted
		else if(player.getTotalScore() > 21) {
			drawBuffer(9, 12, "Player busted...");
			player.money -= betMoney;
			dealer.money += betMoney;
//			System.out.println("Player busted...");
		}
		else {
			while(!(dealer.isOver())) {
				dealer.addCard();
				if(dealer.isOver()) {
					while(dealer.changedValueA() && dealer.isOver()) {}
				}
			}
			// dealer blackjack
			if(dealer.getTotalScore() == 21) {
				drawBuffer(9, 12, "Dealer Wins...");
				player.money -= betMoney;
				dealer.money += betMoney;
			}
			// dealer busted
			else if(dealer.getTotalScore() > 21) {
				drawBuffer(9, 12, "Dealer busted...");
				player.money += betMoney;
				dealer.money -= betMoney;
//				System.out.println("Dealer busted...");
			}
			// 둘다 21 미만인 상태이므로 totalScore가 더 큰 쪽이 이긴다.
			else {
				if(player.getTotalScore() > dealer.getTotalScore()) {
						drawBuffer(9, 12, "Player Wins...");
//						System.out.println("Player Wins...");
						player.money += betMoney;
						dealer.money -= betMoney;
					}
				else if(player.getTotalScore() < dealer.getTotalScore()) {
						drawBuffer(9, 12, "Dealer Wins...");

	//					System.out.println("Dealer Wins...");
						player.money -= betMoney;
						dealer.money += betMoney;
					}
				else {System.out.println("Equal points...");
				drawBuffer(9, 12, "Equal points...");

				}
			}
		}
		String s = dealer.getCardsToString();
		String a = player.getCardsToString();
		drawBuffer(4, 7, "# Dealer($" + dealer.money + ")" + dealer.getCardsToString());
		drawBuffer(4, 8, "# Player($" + player.money + ")" + player.getCardsToString());
		betMoney = 0;
		if (player.money > 0) {
			drawBuffer(9, 14, "Play Again? (Y/N):");
		} else {
			drawBuffer(9, 14, "Game Over");
		}
		render();
	}
	
	/**
	 * player, dealer 카드출력
	 */
	private void showScreen() {
		
		
//		
		System.out.print(" # Dealer : ");
		if(dealer.getCards().size() == 1)
			System.out.println(dealer.getCards().get(0) + " XX");
		else {
			dealer.getCards().forEach(card -> System.out.print(card + " "));
			System.out.println();
		}	
		System.out.print(" # Player : ");
		player.getCards().forEach(card -> System.out.print(card + " "));
		render();
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
