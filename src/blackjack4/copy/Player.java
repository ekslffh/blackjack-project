package blackjack4.copy;

/**
 * �÷��̾� ��ü�� Gamer Ŭ������ ��ӹް� �߰��� dealer�� ���� ������ ���´�.
 * ������δ� ���� �߰� �Ǿ��� �߻�޼����� isOver() �����ߴ�.
 */
public class Player extends Gamer {

	/**
	 * �÷��̾��� totalScore�� 21 �̻� ���� �Ǻ��Ѵ�.
	 * 21�̻��Ͻ� ���̻� hit or stand �Ұ�
	 */
	public boolean isOver() {
		if(totalScore >= 21) return true;
		else return false;
	}
	
}
