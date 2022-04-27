package blackjack2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dealer {

	private List<String> deck;
	ArrayList<Card> cards;
	int totalScore;
	private Player player;
	
	public Dealer() {
		deck = Card.getCardTypes();
		cards = new ArrayList<>();
		totalScore = 0;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Card drawCard() {
		Random random = new Random();
		String cardType = deck.get(random.nextInt(deck.size()));
		deck.remove(cardType);
		return new Card(cardType);
	}
	
	public void addCard() {
		Card card = drawCard();
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
		if(totalScore >= 17) return true;
		else return false;
	}
	
}
