package blackjack3;

import java.util.Scanner;

/**
 * 블랙잭 게임을 순서대로 구현한 클래스로 dealer, player의 정보를 갖는다.
 * 1. 딜러와 플레이어를 서로 매핑 시켜준다.
 * 2. 딜러가 각 플레이어에게 2장씩 분배한다.
 * 3. 플레이어는 힛, 스탠드를 통해 딜러로부터 카드를 더 받거나 게임을 멈출 수 있다.
 * 4. 점수를 집계하여 게임의 승패를 가린다.
 * play()를 통해 게임을 진행할 수 있다.
 */
public class BlackJack {
	
	private Dealer dealer;
	private Player player;
	private Card hiddenCard;
	
	/**
	 * dealer, player 인스턴스 생성 및 관계설정
	 */
	public BlackJack() {
		dealer = new Dealer();
		player = new Player();
	}
	
	/**
	 * 게임의 순서 (1~3)
	 */
	public void play() {
		setUpGame();
		playGame();
		judge();
	}
	
	/**
	 * 1. dealer의 카드분배 및 hiddenCard 저장
	 */
	private void setUpGame() {
		player.addCard(dealer.drawCard());
		dealer.addCard();
		player.addCard(dealer.drawCard());
		hiddenCard = dealer.drawCard();
	}
	
	/**
	 * 2. player hit or stand 선택과정
	 */
	private void playGame() {
		
		char input;
		Scanner scanner = new Scanner(System.in);
				
		while(true) {
			// busted
			if(player.isOver()) return;
			showScreen();
			System.out.print("Hit or Stand? (H/S) : ");
			input = scanner.next().charAt(0);
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
	private void judge() {
		dealer.addCard(hiddenCard);
		if(player.getTotalScore() > 21) {
			System.out.println("Player busted...");
		}
		else {
			while(!(dealer.isOver())) {
				dealer.addCard();
			}
			if(dealer.getTotalScore() > 21) {
				System.out.println("Dealer busted...");
			}
			else {
				if(player.getTotalScore() > dealer.getTotalScore()) System.out.println("Player Wins...");
				else if(player.getTotalScore() < dealer.getTotalScore()) System.out.println("Dealer Wins...");
				else {System.out.println("Equal points...");}
			}
		}
		showScreen();
	}
	
	/**
	 * player, dealer 카드출력
	 */
	private void showScreen() {
		System.out.println("\n------------ Jack's BlackJack Game ------------");
		System.out.print(" # Dealer : ");
		if(dealer.getCards().size() == 1)
			System.out.println(dealer.getCards().get(0) + " X");
		else {
			dealer.getCards().forEach(card -> System.out.print(card + " "));
			System.out.println();
		}	
		System.out.print(" # Player : ");
		player.getCards().forEach(card -> System.out.print(card + " "));
		System.out.println("\n-----------------------------------------------");
	}
		
}
