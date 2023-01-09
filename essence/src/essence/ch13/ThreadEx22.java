package essence.ch13;

/**
 * 동기화를 해줌으로써 두 쓰레드가 한 자원을 문제없이 공유할 수 있음
 */
class ThreadEx22 {

	public static void main(String[] args) {
		Runnable r = new RunnableEx22();
		new Thread(r).start();
		new Thread(r).start();
	}

}

class Account1 {
	private int balance = 1000;	// private으로 해야 동기화의 의미가 있다.
	
	public int getBalance() {
		return this.balance;
	}
	
	// synchronized로 메서드를 동기화
	public synchronized void withdraw(int  money) {
		if(balance >= money) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				
			}
			
			balance -= money;
		}
	}
}

class RunnableEx22 implements Runnable {
	Account1 acc = new Account1();
	
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println("thread : " + Thread.currentThread().getName() + " / balance : " + acc.getBalance());
		}
	}
}