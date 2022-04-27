package blackjack2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
	
	private Dealer dealer;
	private Player player;
	
	public BlackJack() {
		dealer = new Dealer();
		player = new Player();
		dealer.setPlayer(player);
	}
	
	public void setUp() {
		for(int i = 0; i < 2; i++) {
			player.addCard(dealer.drawCard());
			dealer.addCard();
		}
	}
	
	private void hit() {
		player.addCard(dealer.drawCard());
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
	
	public void play() {
				
		char input;
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			// busted
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
		showFianlScreen();
	}
	
	public void playGame() {
		setUp();
		play();
		judge();
	}
	
}
