package blackjack;

import java.util.ArrayList;

/*
PROPERTY
cards : 플레이어가 현재 가지고 있는 카드
total : cards에 대한 전체 값 계산

METHOD
Player() : 생성자, 속성 초기화
addCard() : 카드 추가하면 cards에 넣어주고 total값 계산
getTotal() : 전체 값 보여주기
getCards() : 전체 카드 리턴
isOver() : 점수합산이 21 이상인지 판단
*/

public class Player {

	private ArrayList<Card> cards;
	private int totalScore;
	
	public Player() {
		cards = new ArrayList<>();
		totalScore = 0;
	}
	
	public void addCard(Card card) {
		cards.add(card);
		totalScore += card.getCardValue();
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
