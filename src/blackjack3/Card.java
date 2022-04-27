package blackjack3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 카드의 종류(CARD_TYPES)정보를 static 형태로 제공하고 있고 카드의 종류(type)와 종류에 따른 실제 값 정보(value)를 저장하고 있는 카드객체
 */
public class Card {

	String type;	
	int value;		
	
	private static final ArrayList<String> CARD_TYPES = new ArrayList<>(Arrays.asList("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
	
	/**
	 * @param type : 해당 카드의 타입 (CARD_TYPES 중 하나)
	 */
	public Card(String type) {
		this.type = type;
		this.value = substitution(type); 
	}

	/**
	 * @return 카드 타입에 따른 실제 값
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 치환 : 카드 타입 -> 실제 값
	 * @param type : 카드 타입
	 * @return 카드 타입에 따른 실제 값
	 */
	private int substitution(String type) {
		if(type == "A") return 1;
		else if(type == "J" || type == "Q" || type == "K") return 10;
		else return Integer.parseInt(type);
	}
	
	/**
	 * 정적 메소드로 카드의 타입들을 알려준다.
	 */
	public static ArrayList<String> getCardTypes() {
		return CARD_TYPES;
	}
	
	/**
	 * @return 카드의 종류 개수 (현재 13가지)
	 */
	public static int getCardSize() {
		return CARD_TYPES.size();
	}

	/**
	 * toString() 카드의 타입으로 리턴하도록 오버라이딩
	 */
	@Override
	public String toString() {
		return this.type;
	}
	
}
