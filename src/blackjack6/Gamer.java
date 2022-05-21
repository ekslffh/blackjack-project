package blackjack6;

import java.util.ArrayList;

/**
 * 소개 : 게이머 추상클래스는 자신이 받은 카드(cards)와 그에 따른 합산점수(totalScore)를 가지고 있다.
 * 기능 : 카드받아 저장, 점수 합산, 가지고 있는 카드 및 점수 get하기.
 * 요구사항 : isOver() 구현하기.
 */
public abstract class Gamer {
	
	protected ArrayList<Card> cards;
	protected int totalScore;
	protected int money;
	
	/**
	 * 생성자 : cards, totalSocre 초기화
	 */
	public Gamer(int money) {
		this.money = money;
		cards = new ArrayList<>();
		totalScore = 0;
	}
	
	/**
	 * 카드 받으면 cards에 추가하고 점수 합산하기
	 * @param card : 딜러가 뽑은 랜덤카드
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalScore += card.getValue();
	}
	
	/**
	 * @return 게이머가 가진 카드 합산점수 
	 */
	public int getTotalScore() {
		return totalScore;
	}
	
	public void initCards() {
		cards.clear();
		this.totalScore = 0;
	}
	
	/**
	 * @return 게이머가 가진 카드들
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public String getCardsToString() {

		StringBuilder sb = new StringBuilder();
		for (Card card : cards) {
			sb.append(card);
		}
		if (getCards().size() == 1) sb.append("XX");
		return sb.toString();
	}
	
	/**
	 * A값을 11->1로 변경한다.
	 * totalScore에서 10을 빼준다.
	 * @return 아직 11점으로 계산되는 A값이 있었으면 true 
	 */
	public boolean changedValueA() {
		for(Card card : cards) {
			if(card.getRank() == "A" && card.getValue() == 11) {
//				System.out.println("chaneged A (11 -> 1)");
				card.setValue(1);
				this.totalScore -= 10;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 게이머가 제한된 최대점수를 넘게되면 true 반환하는 함수 구현하기
	 */
	abstract public boolean isOver();
	
}
