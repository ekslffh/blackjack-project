package blackjack2;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {

	String type;
	int value;
	
	private static final ArrayList<String> SUIT = new ArrayList<>(Arrays.asList("A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"));
	public Card(String type) {
		this.type = type;
		this.value = substitution(type); 
	}

	public int getValue() {
		return value;
	}

	private int substitution(String type) {
		if(type == "A") return 1;
		else if(type == "J" || type == "Q" || type == "K") return 10;
		else return Integer.parseInt(type);
	}
	
	public static ArrayList<String> getCardTypes() {
		return SUIT;
	}
	
	public static int getCardSize() {
		return SUIT.size();
	}

	@Override
	public String toString() {
		return this.type;
	}
	
}
