package blackjack4.copy;

import java.util.List;

public class cardsTest {

	public static void main(String[] args) {

		List<Card> deck = Card.getCards();
		for(Card card : deck) {
			System.out.println(card);
		}
		System.out.println(deck.size());
	}

}
