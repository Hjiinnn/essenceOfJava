package essence.ch13;

/**
 * 각 쓰레드가 다른 실행상태를 가질 수 있도록 하나의 객체를 공유하지 않는다.
 */
class ThreadEx16 {

	public static void main(String[] args) {
		RunImplEx16 r1 = new RunImplEx16();
		RunImplEx16 r2 = new RunImplEx16();
		RunImplEx16 r3 = new RunImplEx16();
		
		Thread th1 = new Thread(r1, "*");
		Thread th2 = new Thread(r2, "**");
		Thread th3 = new Thread(r3, "***");
		
		th1.start();
		th2.start();
		th3.start();
		
		try {
			Thread.sleep(2000);
			r1.suspend(); 		// 쓰레드 r1을 잠시 중단시킴
			
			Thread.sleep(2000); 
			r2.suspend(); 
			
			Thread.sleep(3000);
			r1.resume(); 		// 쓰레드 th1을 다시 동작하도록 함
			
			Thread.sleep(3000);
			r1.stop();  		// 쓰레드 tr1을 강제종료 함
			r2.stop();
			
			Thread.sleep(2000);
			r3.stop();
		} catch(InterruptedException e) {}
	}

}

class RunImplEx16 implements Runnable {
	
	boolean suspended = false;
	boolean stopped = false;
	
	public void run() {
		while(!stopped) {
			if(!suspended) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {}
			
			}
		}
		
		System.out.println(Thread.currentThread().getName());
	}
	
	public void suspend() {suspended = true;}
	public void resume() {suspended = false;}
	public void stop() {stopped = true;}
}
