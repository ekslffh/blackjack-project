package blackjack6;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {

	private String suit, rank;	
	private int value;		
	private static final ArrayList<String> SUIT_LIST = new ArrayList<>(Arrays.asList("♦", "♣", "♥", "♠"));
	private static final ArrayList<String> RANK_LIST = new ArrayList<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
	
	public static ArrayList<Card> getCards() {
		ArrayList<Card> cards = new ArrayList<>();
		
		for(String suit : SUIT_LIST) {
			for(String rank : RANK_LIST) {
				cards.add(new Card(suit, rank));
			}
		}
		
		return cards;
	}
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		this.value = substitution(rank); 
	}
	
	public String getSuit() {
		return suit;
	}

	public String getRank() {
		return rank;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	private int substitution(String type) {
		if(type.equals("A")) return 11;
		else if(type.equals("J") || type.equals("Q") || type.equals("K")) return 10;
		else return Integer.parseInt(type);
	}

	public static int getCardSize() {
		return getCards().size();
	}

	@Override
	public String toString() {
		return (this.rank + "(" + this.suit + ")");
	}
	
}
