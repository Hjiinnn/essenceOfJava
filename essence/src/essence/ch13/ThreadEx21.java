package essence.ch13;

/**
 * 두 쓰레드가 한 자원을 공유하여 문제가 생김
 */
class ThreadEx21 {

	public static void main(String[] args) {
		Runnable r = new RunnableEx21();
		new Thread(r).start();
		new Thread(r).start();
	}

}

class Account {
	private int balance = 1000;
	
	public int getBalance() {
		return this.balance;
	}
	
	// 계좌이체
	// 잔고(balance)가 인출금액(money)보다 크면 1초 뒤에 인출함
	public void withdraw(int money) {
		if(balance >= money) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				
			}
			
			balance -= money;
		}
	}
}

class RunnableEx21 implements Runnable {
	Account acc = new Account();
	
	public void run() {
		// 계좌 잔고가 0보타 크면 임의의 수만큼 인출 반복함
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			System.out.println("thread : " + Thread.currentThread().getName() + " / balance : " + acc.getBalance());
		}
	}
}