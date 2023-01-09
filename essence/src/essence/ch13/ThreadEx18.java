package essence.ch13;

/**
 * interrupt와 yeild를 적절이 활용하면 효율성과 응답성이 좋아질 수 있음
 */
public class ThreadEx18 {

	public static void main(String[] args) {
		ThreadEx18_1 th1 = new ThreadEx18_1("*");
		ThreadEx18_1 th2 = new ThreadEx18_1("**");
		ThreadEx18_1 th3 = new ThreadEx18_1("***");
		
		th1.start();
		th2.start();
		th3.start();
		
		try {
			Thread.sleep(2000);
			th1.suspend(); 		
			
			Thread.sleep(2000); 
			th2.suspend(); 
			
			Thread.sleep(3000);
			th1.resume(); 		
			
			Thread.sleep(3000);
			th1.stop();  		
			th2.stop();
			
			Thread.sleep(2000);
			th3.stop();
		} catch(InterruptedException e) {}
	}

}

class ThreadEx18_1 implements Runnable {
	
	boolean suspended = false;
	boolean stopped = false;
	
	Thread th;
	
	ThreadEx18_1(String name) {
		th = new Thread(this, name);
	}
	
	public void run() {
		String name = th.getName();
		
		while(!stopped) {
			if(!suspended) {
				System.out.println(name);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException e) {
					System.out.println(name + " - interrupted");
				}
			}else {
				// suspended의 값이 true일 때 while문을 의미없이 돌면서 낭비하게 됨 => 바쁜 대기상태(busy-waiting)
				// yield()를 호출해서 남은 시간을 while문에서 낭비하지 않고 다른 쓰레드에게 양보하게 되므로 더 효율적임
				Thread.yield();
			}
		}
		System.out.println(name + " - stopped");
	}
	
	public void suspend() {
		suspended = true;
		th.interrupt();
		System.out.println(th.getName() + " - interrupt() by suspend()");
	}
	public void stop() {
		stopped = true;
		// stop()을 호출하여 강제 종료를 하고 싶을 때 sleep(1000)에 의해 일시정지 상태에 머물러 있는 상황이라면 stopped의 값이 true로 바뀌었어도 쓰레드가 정지될 때까지 최대 1초의 시간 지연이 생긴다.
		// 이떄 interrupt()를 호출하면 sleep()에서 InterruptedException이 발생하여 즉시 일시정지 상태에서 벗어나게 되므로 응답성이 좋아진다.
		th.interrupt();		
		System.out.println(th.getName() + " - interrupt() by stop()");
	}
	public void resume() {suspended = false;}
	public void start() {th.start();}
}