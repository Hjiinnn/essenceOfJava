package essence.ch7;

class Tv {
	boolean power;	// 전원상태(on/off)
	int channel;	// 채널
	
	// 전원 on/off 
	void power() {
		power = !power;
	}
	
	// 채널 증가
	void channelUp() {
		++channel;
	}
	
	// 채널 감소
	void channelDown() {
		--channel;
	}
}

class CaptionTv extends Tv {
	boolean caption;	// 캡션상태(on/off)
	
	// 캡션상태가 on(true)일 경우에만 text를 출력한다.
	void displayCaption(String text) {
		if(caption) {	
			System.out.println(text);
		}
	}
}

public class CaptionTvTest {

	public static void main(String[] args) {
		CaptionTv ctv = new CaptionTv(); // 자손 클래스의 인스턴스를 생성하면 조상 클래스의 멤버도 함께 생성된다.
		ctv.channel = 10;
		ctv.channelUp();
		System.out.println(ctv.channel);
		
		ctv.displayCaption("Hello, World1");
		ctv.caption = true;
		ctv.displayCaption("Hello, Wrold2");
		
		
	}

}
