package blackjack4.copy;

import java.util.List;
import java.util.Random;

/**
 * �÷��̾� ��ü�� Gamer Ŭ������ ��ӹް� �߰��� player�� ���� ������ ���´�.
 * ������δ� �ʱ�ī��й�(2�徿), ī��̱�, ī��̰� ���� ��� �߰�, �߻�޼ҵ��� isOver()�޼ҵ� �����ߴ�.
 */
public class Dealer extends Gamer {

	private List<Card> deck;
	
	/**
	 * ������
	 * deck : Card�κ��� Ÿ�Ե� �޾ƿ´�. (���� 13����)
	 * cards, totalScore�ʱ�ȭ
	 */
	public Dealer() {
		super();
		deck = Card.getCards();
	}
	
	/**
	 * �������� ī�带 �̰� �ߺ����� �ʵ��� deck���� �����Ѵ�. 
	 * @return ���� ī�� 
	 */
	public Card drawCard() {
		Random random = new Random();
		Card card = deck.get(random.nextInt(deck.size()));
		deck.remove(card);
		return new Card(card.getSuit(), card.getRank());
	}
	
	/**
	 * �����ε� �Լ� 
	 * ī��̱� + ī������
	 */
	public void addCard() {
		super.addCard(drawCard());
	}
	
	/**
	 * 17�̻����� �Ǵ��ϴ� �Լ�
	 * ������ ī���� ���� 17�̻� �� ������ ��� �̾ƾ� �Ѵ�.
	 */
	public boolean isOver() {
		if(totalScore >= 17) return true;
		else return false;
	}
	
}
