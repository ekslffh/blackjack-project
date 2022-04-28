package blackjack4.copy;

import java.util.List;
import java.util.Random;

/**
 * 플레이어 객체는 Gamer 클래스를 상속받고 추가로 player에 대한 정보를 갖는다.
 * 기능으로는 초기카드분배(2장씩), 카드뽑기, 카드뽑고 저장 기능 추가, 추상메소드인 isOver()메소드 구현했다.
 */
public class Dealer extends Gamer {

	private List<Card> deck;
	
	/**
	 * 생성자
	 * deck : Card로부터 타입들 받아온다. (현재 13가지)
	 * cards, totalScore초기화
	 */
	public Dealer() {
		super();
		deck = Card.getCards();
	}
	
	/**
	 * 랜덤으로 카드를 뽑고 중복되지 않도록 deck에서 삭제한다. 
	 * @return 랜덤 카드 
	 */
	public Card drawCard() {
		Random random = new Random();
		Card card = deck.get(random.nextInt(deck.size()));
		deck.remove(card);
		return new Card(card.getSuit(), card.getRank());
	}
	
	/**
	 * 오버로딩 함수 
	 * 카드뽑기 + 카드저장
	 */
	public void addCard() {
		super.addCard(drawCard());
	}
	
	/**
	 * 17이상인지 판단하는 함수
	 * 딜러는 카드의 합이 17이상 될 때까지 계속 뽑아야 한다.
	 */
	public boolean isOver() {
		if(totalScore >= 17) return true;
		else return false;
	}
	
}
