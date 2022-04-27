package blackjack;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/* 
FUNCTION

BlackJack() : 생성자, 속성들 초기화 및 플레이어, 딜러 카드 두장 씩 배분 (초기세팅)
drawCard() : 중복되지 않게 한장 씩 뽑아오기
hit() : 플레이어에게 한장주기
showScreen(), showFinalScreen() : 진행중 화면, 마지막 화면
play() : 게임 진행 중
judge() : play이후 점수 비교 (승패 판단)
playGame() : 실제 게임 진행 순서 명시 
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
