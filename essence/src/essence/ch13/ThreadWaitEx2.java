package essence.ch13;

import java.util.ArrayList;

/**
 * 여러 쓰레드가 공유하는 객체인 테이블의 add()와 remove()를 동기화하였다.
 * 손님 쓰레드가 원하는 음식이 테이블에 없으면 fail to eat을 출력하고, 테이블에 음식이 하나도 없으면 0.5초마다 음식이 추가되었는지 확인하면서 기다리도록 작성되었지만,
 * 요리사 쓰레드는 음식을 추가하지 않고 손님 쓰레드를 계속 기다리게 하는 문제가 있다. 

 * 손님 쓰레드가 테이블 객체의 lock을 쥐고 기다리고 있기 때문에 요리사 쓰레드가 음식을 새로 추가하려 해도 테이블 객체의 lock을 얻을 수 없어서 불가능한 것이다.
 * 이럴 때 사용하는 것이 바로 wait() & notify() 이다.
 */
class ThreadWaitEx2 {

	public static void main(String[] args) throws InterruptedException {
		Table2 table = new Table2();
		
		new Thread(new Cook2(table), "COOK1").start();
		new Thread(new Customer2(table, "donut"), "CUST1").start();
		new Thread(new Customer2(table, "burger"), "CUST2").start();
		
		Thread.sleep(5000);
		System.exit(0);
	}

}

class Customer2 implements Runnable {
	private Table2 table;
	private String food;
	
	Customer2(Table2 table, String food) {
		this.table = table;
		this.food = food;
	}
	
	// 손님이 음식을 먹는다.
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				
			}
			
			String name = Thread.currentThread().getName();
			
			if(eatFood())
				System.out.println(name + " ate a " + food);
			else
				System.out.println(name + " failed to eat. :(");
		}
	}
	
	boolean eatFood() {
		return table.remove(food);
	}
}

class Cook2 implements Runnable {
	private Table2 table;
	
	Cook2(Table2 table) {
		this.table = table;
	}
	
	// 임의의 음식을 주문한다.
	public void run() {
		while(true) {
			int idx = (int)(Math.random() * table.dishNum());
			table.add(table.dishNames[idx]);
			
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				
			}
		}
	}
}

class Table2 {
	String[] dishNames = {"donut", "donut", "burger"};
	final int MAX_FOOD = 6; 	// 테이블에 놓을 수 있는 최대 음식의 개수
	
	private ArrayList<String> dishes = new ArrayList<>();
	
	public synchronized void add(String dish) {		// synchronized 추가
		// 테이블에 음식이 가득찼으면, 음식을 추가하지 않는다.
		if(dishes.size() >= MAX_FOOD) return;
		
		dishes.add(dish);
		System.out.println("Dishes : " + dishes.toString());
	}
	
	public boolean remove(String dishName) {
		synchronized(this) {
			// 0.5초마다 음식이 추가되었는지 확인한다.
			while(dishes.size() == 0) {
				String name = Thread.currentThread().getName();
				System.out.println(name + " is waiting.");
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {}
			}
			// 지정된 요리와 일치하는 요리를 테이블에서 제거한다.
			for(int i=0; i<dishes.size(); i++) {
				if(dishName.equals(dishes.get(i))) {
					dishes.remove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int dishNum() {
		return dishNames.length;
	}
}