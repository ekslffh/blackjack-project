package blackjack4.copy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 카드의 종류(CARD_TYPES)정보를 static 형태로 제공하고 있고 카드의 종류(type)와 종류에 따른 실제 값 정보(value)를 저장하고 있는 카드객체
 */
public class Card {

	private String suit, rank;	
	private int value;		
	
	private static final ArrayList<String> SUIT_LIST = new ArrayList<>(Arrays.asList("◆", "♣", "♥", "♠"));
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
	
	/**
	 * @param type : 해당 카드의 타입 (CARD_TYPES 중 하나)
	 */
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

	/**
	 * @return 카드 타입에 따른 실제 값
	 */
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * 치환 : 카드 타입 -> 실제 값
	 * @param type : 카드 타입
	 * @return 카드 타입에 따른 실제 값
	 */
	private int substitution(String type) {
		if(type == "A") return 11;
		else if(type == "J" || type == "Q" || type == "K") return 10;
		else return Integer.parseInt(type);
	}
	
	/**
	 * @return 카드의 종류 개수 (현재 52가지)
	 */
	public static int getCardSize() {
		return getCards().size();
	}

	/**
	 * toString() 카드의 타입으로 리턴하도록 오버라이딩
	 */
	@Override
	public String toString() {
		return (this.value + ";" + this.rank + "(" + this.suit + ")");
	}
	
}
