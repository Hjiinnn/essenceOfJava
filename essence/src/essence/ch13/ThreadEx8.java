package essence.ch13;

/**
 * main메서드를 실행하는 쓰레드의 우선순위인 5를 상속받았고, 다른 쓰레드에는 우선순위를 지정해 주었다.
 * 쓰레드를 실행하기 전에만 우선순위를 변경할 수 있다.
 */
class ThreadEx8 {

	public static void main(String[] args) {
		ThreadEx8_1 th1 = new ThreadEx8_1();
		ThreadEx8_2 th2 = new ThreadEx8_2();
		
		th2.setPriority(7);
		
		System.out.println("Priority of th1 (-) : " + th1.getPriority());
		System.out.println("Priority of th2 (|) : " + th2.getPriority());
		
		th1.start();
		th2.start();
	}

}

class ThreadEx8_1 extends Thread {
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.println("-");
			for(int x=0; x<10000000; x++);
		}
	}
}

class ThreadEx8_2 extends Thread {
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.println("|");
			for(int x=0; x<10000000; x++);		// 우선순위가 높아지면 한 번에 작업이 끝날 수 있기 때문에 지연시키기 위함.
		}
	}
}
