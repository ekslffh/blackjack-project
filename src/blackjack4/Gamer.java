package blackjack4;

import java.util.ArrayList;

/**
 * 소개 : 게이머 추상클래스는 자신이 받은 카드(cards)와 그에 따른 합산점수(totalScore)를 가지고 있다.
 * 기능 : 카드받아 저장, 점수 합산, 가지고 있는 카드 및 점수 get하기.
 * 요구사항 : isOver() 구현하기.
 */
public abstract class Gamer {
	
	protected ArrayList<Card> cards;
	protected int totalScore;
	
	/**
	 * 생성자 : cards, totalSocre 초기화
	 */
	public Gamer() {
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
	
	/**
	 * @return 게이머가 가진 카드들
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public boolean changedValueA() {
		for(Card card : cards) {
			if(card.getRank() == "A" && card.getValue() == 11) {
				System.out.println("chaneged A (11 -> 1)");
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
