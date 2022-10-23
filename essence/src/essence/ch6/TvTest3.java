package essence.ch6;

/**
 * 참조변수에는 하나의 값(주소)만이 저장될 수 있으므로 둘 이상의 참조변수가 하나의 인스턴스를 참조하는 것은 가능하지만, 하나의 참조변수로 여러 개의 인스턴스를 참조하는 것은 가능하지 않다.
 */

class Tv3 {
	// 멤버변수
	String color;	// 색상
	boolean power;	// 전원상태 (on/off)
	int channel;	// 채널
	
	// 메서드
	void power() { power = !power; }	// TV를 켜거나 끄는 기능을 하는 메서드
	void channelUp() { ++channel; }		// TV의 채널을 높이는 기능을 하는 메서드
	void channelDown() { --channel; }	// TV의 채널을 낮추는 기능을 하는 메서드
}

public class TvTest3 {

	public static void main(String[] args) {
		
		Tv3 t1 = new Tv3();
		Tv3 t2 = new Tv3();
		System.out.println("t1의 channel 값은 " + t1.channel + "입니다.");
		System.out.println("t2의 channel 값은 " + t2.channel + "입니다.");
		
		/*
		 * t1은 참조변수이므로, 인스턴스의 주소를 저장하고 있다.
		 * 이 문장이 수행되면 t2가 가지고 있던 값은 잃어버리게 되고, t1에 저장되어 있던 값이 t2에 저장되게 된다.
		 * 그렇게 되면 t2역시 t1이 참조하고 있던 인스턴스를 같이 참조하게 되고, t2가 원래 참조하고 있던 인스턴스는 더 이상 사용할 수 없게 된다.
		 * t2가 참조하고 있던 인스턴스는 가비지 컬렉터에 의해서 자동적으로 메모리에서 삭제된다.
		 */
		t2 = t1;		// t1이 저장하고 있는 주소를 t2에 저장한다.
		t1.channel = 7;	// channel의 값을 7로 한다.
		System.out.println("t1의 channel값을 7로 변경했습니다.");
		
		System.out.println("t1의 channel 값은 " + t1.channel + "입니다.");
		System.out.println("t2의 channel 값은 " + t2.channel + "입니다.");

	}

}
