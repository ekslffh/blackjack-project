package blackjack2;

import java.util.ArrayList;

public class Player {

	private ArrayList<Card> cards;
	private int totalScore;
	
	public Player() {
		cards = new ArrayList<>();
		totalScore = 0;
	}
	
	public void addCard(Card card) {
		cards.add(card);
		totalScore += card.getValue();
	}
	
	public int getTotalScore() {
		return totalScore;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public boolean isOver() {
		if(totalScore >= 21) return true;
		else return false;
	}
	
}
