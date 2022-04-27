package blackjack;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/* 
FUNCTION

BlackJack() : ������, �Ӽ��� �ʱ�ȭ �� �÷��̾�, ���� ī�� ���� �� ��� (�ʱ⼼��)
drawCard() : �ߺ����� �ʰ� ���� �� �̾ƿ���
hit() : �÷��̾�� �����ֱ�
showScreen(), showFinalScreen() : ������ ȭ��, ������ ȭ��
play() : ���� ���� ��
judge() : play���� ���� �� (���� �Ǵ�)
playGame() : ���� ���� ���� ���� ��� 
*/


public class BlackJack {
	
	private Dealer dealer;
	private Player player;
	private List<String> cards;
	
	public BlackJack() {
		dealer = new Dealer();
		player = new Player();
		cards = Card.getCardTypes();
		for(int i = 0; i < 2; i++) {
			player.addCard(drawCard());
			dealer.addCard(drawCard());
		}
	}
	
	private Card drawCard() {
		Random random = new Random();
		String cardType = cards.get(random.nextInt(cards.size()));
		Card card = new Card(cardType);
		cards.remove(cardType);
		return card;
	}
	
	private void hit() {
		player.addCard(drawCard());
	}
	
	private void showScreen() {
		System.out.println("\n------------ Jack's BlackJack Game ------------");
		System.out.println(" # Dealer : " + dealer.getCards().get(0) + " X");
		System.out.print(" # Player : ");
		player.getCards().forEach(card -> System.out.print(card + " "));
		System.out.println("\n-----------------------------------------------");
	}
	
	private void showFianlScreen() {
		System.out.println("\n------------ Jack's BlackJack Game ------------");
		System.out.print(" # Dealer : ");
		dealer.getCards().forEach(card -> System.out.print(card + " "));
		System.out.println();
		System.out.print(" # Player : ");
		player.getCards().forEach(card -> System.out.print(card + " "));
		System.out.println("\n-----------------------------------------------");
	}
	
	private void play() {
		char input;
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			if(player.isOver()) return;
			showScreen();
			System.out.print("Hit or Stand? (H/S) : ");
			input = scanner.next().charAt(0);
			if(input == 'S' || input == 's') return;
			else if(input == 'H' || input == 'h') hit();
			else {
				System.out.println("Please re-enter");
			}
		}
		
	}
	
	private void judge() {
		if(player.getTotalScore() > 21) {
			System.out.println("Player busted...");
		}
		else {
			while(!(dealer.isOver())) {
				dealer.addCard(drawCard());
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
		showFianlScreen();
	}
	
	public void playGame() {
		play();
		judge();
	}
	
}
