package essence.ch7;

/**
 * Card 클래스
 */
class Card2 {
	
	// 상수지만 선언과 함께 초기화하지 않고 생성자에서 단 한번만 초기화할 수 있다.
	final int NUMBER;
	final String KIND;
	
	static int with = 100;
	static int height = 250;
	
	// 매개변수로 넘겨받은 값으로 KIND와 NUMBER를 초기화한다.
	Card2(String kind, int num) {
		KIND = kind;
		NUMBER = num;
	}
	
	Card2() {
		this("HEART", 1);
	}
	
	public String toString() {
		return KIND + " " + NUMBER;
	}
}

class FinalCardTest {
	public static void main(String args[]) {
		Card2 c = new Card2("HEART", 10);
		
		System.out.println(c.KIND);
		System.out.println(c.NUMBER);
		System.out.println(c);
	}
}