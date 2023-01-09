package essence.ch13;

/**
 * sleep(long millis) - 일정시간동안 쓰레드를 멈추게 한다.
 * sleep()은 항상 현재 실행 중인 쓰레드에 대해 작동하기 때문에 th1.sleep(2000)을 호출해도 실제로 영향을 받는 것은 main메서드를 실행하는 main쓰레드이다.
 * 그래서 sleep()은 static으로 선언되어 있으며 참조변수를 이용해서 호출하기 보다는 Thread.sleep(2000)과 같이 작성해야 한다.
 */
class ThreadEx12 {

	public static void main(String[] args) {
		ThreadEx12_1 th1 = new ThreadEx12_1();
		ThreadEx12_2 th2 = new ThreadEx12_2();
		
		th1.start();
		th2.start();
		
		try {
			th1.sleep(2000);
		} catch(InterruptedException e) {}
		
		System.out.print("<<main 종료>>");
	}

}

class ThreadEx12_1 extends Thread {
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.print("-");
		}
		System.out.print("<<th1 종료>>");
	}
}

class ThreadEx12_2 extends Thread {
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.print("|");
		}
		System.out.print("<<th2 종료>>");
	}
}
