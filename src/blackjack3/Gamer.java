package blackjack3;

import java.util.ArrayList;

/**
 * �Ұ� : ���̸� �߻�Ŭ������ �ڽ��� ���� ī��(cards)�� �׿� ���� �ջ�����(totalScore)�� ������ �ִ�.
 * ��� : ī��޾� ����, ���� �ջ�, ������ �ִ� ī�� �� ���� get�ϱ�.
 * �䱸���� : isOver() �����ϱ�.
 */
public abstract class Gamer {
	
	protected ArrayList<Card> cards;
	protected int totalScore;
	
	/**
	 * ������ : cards, totalSocre �ʱ�ȭ
	 */
	public Gamer() {
		cards = new ArrayList<>();
		totalScore = 0;
	}
	
	/**
	 * ī�� ������ cards�� �߰��ϰ� ���� �ջ��ϱ�
	 * @param card : ������ ���� ����ī��
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalScore += card.getValue();
	}
	
	/**
	 * @return ���̸Ӱ� ���� ī�� �ջ����� 
	 */
	public int getTotalScore() {
		return totalScore;
	}
	
	/**
	 * @return ���̸Ӱ� ���� ī���
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	/**
	 * ���̸Ӱ� ���ѵ� �ִ������� �ѰԵǸ� true ��ȯ�ϴ� �Լ� �����ϱ�
	 */
	abstract public boolean isOver();
	
}
