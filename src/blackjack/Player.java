package blackjack;

import java.util.ArrayList;

/*
PROPERTY
cards : �÷��̾ ���� ������ �ִ� ī��
total : cards�� ���� ��ü �� ���

METHOD
Player() : ������, �Ӽ� �ʱ�ȭ
addCard() : ī�� �߰��ϸ� cards�� �־��ְ� total�� ���
getTotal() : ��ü �� �����ֱ�
getCards() : ��ü ī�� ����
isOver() : �����ջ��� 21 �̻����� �Ǵ�
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
