package essence.ch13;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 예제25와 달리 요리사 쓰레드가 통지를 받아야 하는 상황에서 손님 쓰레드가 통지를 받는 경우가 없어졌다.
 * 기아 현상이나 경쟁 상태가 개선된 것이다.
 * 그래도 쓰레드의 종류에 따라 구분하여 통지를 할 수 있게 된 것일 뿐, 여전히 특정 쓰레드를 선택할 수 없기 때문에 같은 종류의 쓰레드간의 기아현상이나, 경쟁상태가 발생할 가능성은 남아있다.
 */
class ThreadWaitEx4 {

	public static void main(String[] args) throws InterruptedException {
		Table4 table = new Table4();
		
		new Thread(new Cook4(table), "COOK1").start();
		new Thread(new Customer4(table, "donut"), "CUST1").start();
		new Thread(new Customer4(table, "burger"), "CUST2").start();
		
		Thread.sleep(2000);
		System.exit(0);
	}

}

class Customer4 implements Runnable {
	private Table4 table;
	private String food;
	
	Customer4(Table4 table, String food) {
		this.table = table;
		this.food = food;
	}
	
	// 손님이 음식을 먹는다.
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				
			}
			
			String name = Thread.currentThread().getName();
			
			table.remove(food);
			System.out.println(name + " ate a " + food);
		}
	}
}

class Cook4 implements Runnable {
	private Table4 table;
	
	Cook4(Table4 table) {
		this.table = table;
	}
	
	// 임의의 음식을 주문한다.
	public void run() {
		while(true) {
			int idx = (int)(Math.random() * table.dishNum());
			table.add(table.dishNames[idx]);
			
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				
			}
		}
	}
}

class Table4 {
	String[] dishNames = {"donut", "donut", "burger"};
	final int MAX_FOOD = 6; 	// 테이블에 놓을 수 있는 최대 음식의 개수
	private ArrayList<String> dishes = new ArrayList<>();
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition forCook = lock.newCondition();
	private Condition forCust = lock.newCondition();
	
	public void add(String dish) {		// synchronized 추가
		lock.lock();
		
		try {
			// 0.5초마다 테이블에 음식이 가득찼는지 체크하고, 가득찼으면 락을 반납하고 기다림 (wait)
			while(dishes.size() >= MAX_FOOD) {
				String name = Thread.currentThread().getName();
				System.out.println(name + " is waiting.");
				try {
					forCook.await();	// COOK쓰레드를 기다리게 한다.
					Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
			
			// 음식이 가득 차지 않았으면 음식을 추가하고, 잠자고 있는 CUST 객체에 대한 락을 얻어 작업을 이어감 (notify)
			dishes.add(dish);
			forCust.signal();	// 기다리고 있는 CUST를 깨우기 위함
			System.out.println("Dishes : " + dishes.toString());
		} finally {
			lock.unlock();
		}
	}
	
	public boolean remove(String dishName) {
		lock.lock();
		
		String name = Thread.currentThread().getName();

		try {
			// 0.5초마다 테이블에 음식이 있는지 확인하고, 음식이 없으면 락을 반납하고 기다림 (wait)
			while(dishes.size() == 0) {
				System.out.println(name + " is waiting.");
				try {
					forCust.await(); // CUST 쓰레드를 기다리게 함
					Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
			
			// 테이블에 음식이 있다면, 지정된 요리와 일치하는 요리를 테이블에서 제거하고, 잠자고 있는 COOK 객체에 대한 락을 얻음 (notify)
			while(true) {
				for(int i=0; i<dishes.size(); i++) {
					if(dishName.equals(dishes.get(i))) {
						dishes.remove(i);
						forCook.signal(); // 잠자고 있는 COOK을 꺠우기 위함
						return true;
					}
				}

				try {
					System.out.println(name + " is waiting.");
					forCust.await();		// 원하는 음식이 없는 CUST쓰레드를 기다리게 한다.
					Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
			
		} finally {
			lock.unlock();
		}
	}
	
	public int dishNum() {
		return dishNames.length;
	}
}