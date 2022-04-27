package blackjack;

import java.util.ArrayList;
import java.util.List;

/*
PROPERTY
cardType : 카드 타입
cardValue : 카드 타입에 따른 실제 값
cardTypes : 카드의 타입들

METHOD
Card(String cardType) : 생성자, 카드타입 넣어주기
getCardValue() : 카드 실제 값 리턴
substitution : 카드 타입을 실제 값으로 치환 
getCardTypes() : 전체 카드 타입들 리턴
getCardSize() : 카드 총 몇장인지 리턴
toString() : 카드 타입 리턴하도록 오버라이딩
*/

public class Card {

	String cardType;
	int cardValue;
	
	private static final ArrayList<String> CARD_TYPES = new ArrayList<>() {{
		add("A");
		add("2");
		add("3");
		add("4");
		add("5");
		add("6");
		add("7");
		add("8");
		add("9");
		add("10");
		add("J");
		add("Q");
		add("K");
	}};
	
	public Card(String cardType) {
		this.cardType = cardType;
		this.cardValue = substitution(cardType); 
	}

	public int getCardValue() {
		return cardValue;
	}

	private int substitution(String card) {
		if(card == "A") return 1;
		else if(card == "J" || card == "Q" || card == "K") return 10;
		else return Integer.parseInt(card);
	}
	
	public static ArrayList<String> getCardTypes() {
		return CARD_TYPES;
	}
	
	public static int getCardSize() {
		return CARD_TYPES.size();
	}

	@Override
	public String toString() {
		return this.cardType;
	}
	
}
