package blackjack4;

/**
 * 플레이어 객체는 Gamer 클래스를 상속받고 추가로 dealer에 대한 정보를 갖는다.
 * 기능으로는 힛이 추가 되었고 추상메서드인 isOver() 구현했다.
 */
public class Player extends Gamer {

	/**
	 * 플레이어의 totalScore가 21 이상 인지 판별한다.
	 * 21이상일시 더이상 hit or stand 불가
	 */
	public boolean isOver() {
		if(totalScore >= 21) return true;
		else return false;
	}
	
}
