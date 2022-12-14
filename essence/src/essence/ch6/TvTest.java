package essence.ch6;

/**
 * 인스턴스는 참조변수를 통해서만 다룰 수 있으며, 참조변수의 타입은 인스턴스의 타입과 일치해야 한다.
 */

class Tv {
	// 멤버변수
	String color;	// 색상
	boolean power;	// 전원상태 (on/off)
	int channel;	// 채널
	
	// 메서드
	void power() { power = !power; }	// TV를 켜거나 끄는 기능을 하는 메서드
	void channelUp() { ++channel; }		// TV의 채널을 높이는 기능을 하는 메서드
	void channelDown() { --channel; }	// TV의 채널을 낮추는 기능을 하는 메서드
}

class TvTest {
	public static void main(String[] args) {
		/*
		 * 1. Tv인스턴스를 참조하기 위한 변수 t를 선언 
		 * Tv클래스 타입의 참조변수 t를 선언한다.
		 * 메모리에 참조변수 t를 위한 공간 생성
		 */
		Tv t;
		
		/*
		 * 2. Tv인스턴스 생성
		 * new 연산자에 의해 Tv클래스의 인스턴스가 메모리의 빈 공간에 생성
		 * 멤버변수는 각 자료형에 해당하는 기본값으로 초기화됨
		 * 대입연산아제 의해 생성된 객체의 주소값이 참조변수 t에 저장됨
		 */
		t = new Tv();		
		
		/*
		 * 3. Tv인스턴스의 멤버변수 channel의 값을 7로 한다.
		 * 참조변수 t에 저장된 주소에 있는 인스턴스의 멤버변수 channel에 7을 저장한다.
		 */
		t.channel = 7;		
		
		/*
		 * 4. Tv인스턴스의 메서드 channelDown()을 호출한다.
		 * 참조변수 t가 참조하고 있는 Tv인스턴스의 channelDown메서드를 호출한다.
		 */
		t.channelDown();	
		
		
		System.out.println("현재 채널은 " + t.channel + " 입니다.");
	}
}
