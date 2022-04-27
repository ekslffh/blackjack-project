package blackjack;

import java.util.ArrayList;

/*
PROPERTY
cards : �÷��̾ ���� ������ �ִ� ī��
total : cards�� ���� ��ü �� ���

METHOD
Dealer() : ������, �Ӽ� �ʱ�ȭ
addCard() : ī�� �߰��ϸ� cards�� �־��ְ� total�� ���
getTotal() : ��ü �� �����ֱ�
getCards() : ��ü ī�� ����
isOver() : �����ջ��� 17 �̻����� �Ǵ�
*/

public class Dealer {

	ArrayList<Card> cards;
	int totalScore;
	
	public Dealer() {
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
		if(totalScore >= 17) return true;
		else return false;
	}
	
}
