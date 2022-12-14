package essence.ch6;

/**
 * 같은 클래스로부터 생성되었을지라도 각 인스턴스의 속성(메멉변수)은 서로 다른 값을 유지할 수 있으며, 메서드의 내용은 모든 인스턴스에 대해 동일하다.
 */

class Tv2 {
	// 멤버변수
	String color;	// 색상
	boolean power;	// 전원상태 (on/off)
	int channel;	// 채널
	
	// 메서드
	void power() { power = !power; }	// TV를 켜거나 끄는 기능을 하는 메서드
	void channelUp() { ++channel; }		// TV의 채널을 높이는 기능을 하는 메서드
	void channelDown() { --channel; }	// TV의 채널을 낮추는 기능을 하는 메서드
}

class TvTest2 {

	public static void main(String[] args) {
		
		Tv2 t1 = new Tv2();	 
		Tv2 t2 = new Tv2();
		System.out.println("t1의 channel값은 " + t1.channel + " 입니다.");
		System.out.println("t2의 channel값은 " + t2.channel + " 입니다.");
		
		t1.channel = 7;
		System.out.println("t1의 channel값을 7로 변경햇습니다.");
		
		System.out.println("t1의 channel값은 " + t1.channel + " 입니다.");
		System.out.println("t2의 channel값은 " + t2.channel + " 입니다.");

	}

}
