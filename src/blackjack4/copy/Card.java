package blackjack4.copy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ī���� ����(CARD_TYPES)������ static ���·� �����ϰ� �ְ� ī���� ����(type)�� ������ ���� ���� �� ����(value)�� �����ϰ� �ִ� ī�尴ü
 */
public class Card {

	private String suit, rank;	
	private int value;		
	
	private static final ArrayList<String> SUIT_LIST = new ArrayList<>(Arrays.asList("��", "��", "��", "��"));
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
	 * @param type : �ش� ī���� Ÿ�� (CARD_TYPES �� �ϳ�)
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
	 * @return ī�� Ÿ�Կ� ���� ���� ��
	 */
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * ġȯ : ī�� Ÿ�� -> ���� ��
	 * @param type : ī�� Ÿ��
	 * @return ī�� Ÿ�Կ� ���� ���� ��
	 */
	private int substitution(String type) {
		if(type == "A") return 11;
		else if(type == "J" || type == "Q" || type == "K") return 10;
		else return Integer.parseInt(type);
	}
	
	/**
	 * @return ī���� ���� ���� (���� 52����)
	 */
	public static int getCardSize() {
		return getCards().size();
	}

	/**
	 * toString() ī���� Ÿ������ �����ϵ��� �������̵�
	 */
	@Override
	public String toString() {
		return (this.value + ";" + this.rank + "(" + this.suit + ")");
	}
	
}
