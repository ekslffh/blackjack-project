package blackjack3;

import java.util.Scanner;

/**
 * ���� ������ ������� ������ Ŭ������ dealer, player�� ������ ���´�.
 * 1. ������ �÷��̾ ���� ���� �����ش�.
 * 2. ������ �� �÷��̾�� 2�徿 �й��Ѵ�.
 * 3. �÷��̾�� ��, ���ĵ带 ���� �����κ��� ī�带 �� �ްų� ������ ���� �� �ִ�.
 * 4. ������ �����Ͽ� ������ ���и� ������.
 * play()�� ���� ������ ������ �� �ִ�.
 */
public class BlackJack {
	
	private Dealer dealer;
	private Player player;
	private Card hiddenCard;
	
	/**
	 * dealer, player �ν��Ͻ� ���� �� ���輳��
	 */
	public BlackJack() {
		dealer = new Dealer();
		player = new Player();
	}
	
	/**
	 * ������ ���� (1~3)
	 */
	public void play() {
		setUpGame();
		playGame();
		judge();
	}
	
	/**
	 * 1. dealer�� ī��й� �� hiddenCard ����
	 */
	private void setUpGame() {
		player.addCard(dealer.drawCard());
		dealer.addCard();
		player.addCard(dealer.drawCard());
		hiddenCard = dealer.drawCard();
	}
	
	/**
	 * 2. player hit or stand ���ð���
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
	 * 3. ���� ���� ���� ����
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
	 * player, dealer ī�����
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
