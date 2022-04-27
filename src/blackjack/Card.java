package blackjack;

import java.util.ArrayList;
import java.util.List;

/*
PROPERTY
cardType : ī�� Ÿ��
cardValue : ī�� Ÿ�Կ� ���� ���� ��
cardTypes : ī���� Ÿ�Ե�

METHOD
Card(String cardType) : ������, ī��Ÿ�� �־��ֱ�
getCardValue() : ī�� ���� �� ����
substitution : ī�� Ÿ���� ���� ������ ġȯ 
getCardTypes() : ��ü ī�� Ÿ�Ե� ����
getCardSize() : ī�� �� �������� ����
toString() : ī�� Ÿ�� �����ϵ��� �������̵�
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
