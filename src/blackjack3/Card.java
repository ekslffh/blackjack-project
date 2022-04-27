package blackjack3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ī���� ����(CARD_TYPES)������ static ���·� �����ϰ� �ְ� ī���� ����(type)�� ������ ���� ���� �� ����(value)�� �����ϰ� �ִ� ī�尴ü
 */
public class Card {

	String type;	
	int value;		
	
	private static final ArrayList<String> CARD_TYPES = new ArrayList<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
	
	/**
	 * @param type : �ش� ī���� Ÿ�� (CARD_TYPES �� �ϳ�)
	 */
	public Card(String type) {
		this.type = type;
		this.value = substitution(type); 
	}

	/**
	 * @return ī�� Ÿ�Կ� ���� ���� ��
	 */
	public int getValue() {
		return value;
	}

	/**
	 * ġȯ : ī�� Ÿ�� -> ���� ��
	 * @param type : ī�� Ÿ��
	 * @return ī�� Ÿ�Կ� ���� ���� ��
	 */
	private int substitution(String type) {
		if(type == "A") return 1;
		else if(type == "J" || type == "Q" || type == "K") return 10;
		else return Integer.parseInt(type);
	}
	
	/**
	 * ���� �޼ҵ�� ī���� Ÿ�Ե��� �˷��ش�.
	 */
	public static ArrayList<String> getCardTypes() {
		return CARD_TYPES;
	}
	
	/**
	 * @return ī���� ���� ���� (���� 13����)
	 */
	public static int getCardSize() {
		return CARD_TYPES.size();
	}

	/**
	 * toString() ī���� Ÿ������ �����ϵ��� �������̵�
	 */
	@Override
	public String toString() {
		return this.type;
	}
	
}
