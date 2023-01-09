package essence.ch13;

class ThreadEx1 {

	public static void main(String args[]) {
		ThreadEx1_1 t1 = new ThreadEx1_1();
		
		Runnable r = new ThreadEx1_2();
		Thread t2 = new Thread(r);			// 생성자 Thread (Runnable target)
		
		t1.start();
		t2.start();
	}
}

class ThreadEx1_1 extends Thread {
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println("[Thread 상속] " + getName());	// Thread클래스의 getName()메서드를 호출함.
		}
	}
}

class ThreadEx1_2 implements Runnable {

	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			// Thread.currentThread() - 현재 실행 중인 Thread를 반환한다.
			System.out.println("[Runnable 구현]" + Thread.currentThread().getName());
		}
	}
	
}
