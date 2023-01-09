package essence.ch13;

import java.util.ArrayList;

/**
 * 여러 쓰레드가 테이블을 공유하는데 동기화를 해주지 않아서 예외가 발생한다.
 * 1. 요리사(Cook)가 테이블에 음식을 놓는 도중에 손님(Customer) 쓰레드가 음식을 가져가려했기 때문에 발생하는 예외 (ConcurrentModificationException)
 * 2. 손님 쓰레드가 테이블의 마지막 남은 음식을 가져가는 도중에 다른 손님 쓰레드가 먼저 음식을 낚아채버려서 있지도 않은 음식을 테이블에서 제거하려했기 때문에 발생하는 예외(IndexOutOuBoundsException)
 */
class ThreadWaitEx1 {

	public static void main(String[] args) throws InterruptedException {
		Table table = new Table();
		
		new Thread(new Cook(table), "COOK1").start();
		new Thread(new Customer(table, "donut"), "CUST1").start();
		new Thread(new Customer(table, "burger"), "CUST2").start();
		
		Thread.sleep(100);
		System.exit(0);
	}

}

class Customer implements Runnable {
	private Table table;
	private String food;
	
	Customer(Table table, String food) {
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

class Cook implements Runnable {
	private Table table;
	
	Cook(Table table) {
		this.table = table;
	}
	
	// 임의의 음식을 주문한다.
	public void run() {
		while(true) {
			int idx = (int)(Math.random() * table.dishNum());
			table.add(table.dishNames[idx]);
			
			try {
				Thread.sleep(1);
			} catch(InterruptedException e) {
				
			}
		}
	}
}

class Table {
	String[] dishNames = {"donut", "donut", "burger"};
	final int MAX_FOOD = 6; 	// 테이블에 놓을 수 있는 최대 음식의 개수
	
	private ArrayList<String> dishes = new ArrayList<>();
	
	public void add(String dish) {
		// 테이블에 음식이 가득찼으면, 음식을 추가하지 않는다.
		if(dishes.size() >= MAX_FOOD) return;
		
		dishes.add(dish);
		System.out.println("Dishes : " + dishes.toString());
	}
	
	public boolean remove(String dishName) {
		// 지정된 요리와 일치하는 요리를 테이블에서 제거한다.
		for(int i=0; i<dishes.size(); i++) {
			if(dishName.equals(dishes.get(i))) {
				dishes.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public int dishNum() {
		return dishNames.length;
	}
}