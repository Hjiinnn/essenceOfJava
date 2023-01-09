package essence.ch13;

class ThreadEx15 {

	public static void main(String[] args) {
		RunImplEx15 r = new RunImplEx15();
		Thread th1 = new Thread(r, "*");
		Thread th2 = new Thread(r, "**");
		Thread th3 = new Thread(r, "***");
		th1.start();
		th2.start();
		th3.start();
		
		try {
			Thread.sleep(2000);
			th1.suspend(); 		// 쓰레드 th1을 잠시 중단시킴
			
			Thread.sleep(2000); 
			th2.suspend(); 
			
			Thread.sleep(3000);
			th1.resume(); 		// 쓰레드 th1을 다시 동작하도록 함
			
			Thread.sleep(3000);
			th1.stop();  		// 쓰레드 tr1을 강제종료 함
			th2.stop();
		} catch(InterruptedException e) {}
	}

}

class RunImplEx15 implements Runnable {
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
		}
	}
}
