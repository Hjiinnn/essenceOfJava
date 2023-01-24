package essence.ch7;

import java.util.Vector;

class PolyArgumentTest3 {

	public static void main(String[] args) {
		Buyer23 b = new Buyer23();
		Tv23 tv = new Tv23();
		Computer23 com = new Computer23();
		Audio23 audio = new Audio23();
		
		b.buy(tv);
		b.buy(com);
		b.buy(audio);
		b.summary();
		System.out.println();
		b.refund(com);
		b.summary();
	}

}

class Product23 {
	int price;		// 제품의 가격
	int bonusPoint; // 제품구매 시 제공하는 보너스 점수
	
	Product23(int price) {
		this.price = price;
		bonusPoint = (int)(price/10.0);	// 보너스점수 = 제품 가격의 10%
	}
	
	Product23() {
		price = 0;
		bonusPoint = 0;
	}
}

class Tv23 extends Product23 {
	Tv23() {
		// 조상 클래스 초기화
		super(100);		// Tv가격을 100만원으로 한다.
	}
	
	public String toString() {
		return "Tv";
	}
	
}

class Computer23 extends Product23 {
	Computer23() {
		// 조상 클래스 초기화
		super(200);		// Computer가격을 200만원으로 한다.
	}
	
	public String toString() {
		return "Computer";
	}
}

class Audio23 extends Product23 {
	Audio23() {
		super(50);
	}
	
	
	public String toString() {
		return "Audio";
	}
}

// 고객
class Buyer23 {
	int money = 1000;
	int bonusPoint = 0;
	Vector item = new Vector();	// 배열의 크기를 알아서 관리해주는 Vector클래스 (인스턴스 개수에 신경쓰지 않아도 됨. 자동으로 크기 증가. 기본 10개)
	
	// 다형성 적용!
	/*
	 * 매개변수 타입을 Tv나 Computer으로 주지 않고 조상 클래스인 Product로 줌으로써
	 * 각 매개변수마다 메서드를 만들지 않고 하나의 메서드만 만들어도 된다. 
	 */
	void buy(Product23 p) {
		if(money < p.price) {
			System.out.println("잔액이 부족하여 물건을 살 수 없습니다.");
			return;
		}
		
		money -= p.price;				// 가진 돈에서 제품의 가격을 뺌
		bonusPoint += p.bonusPoint;		// 제품의 보너스 점수 추가
		item.add(p);					// 구입한 제품을 Vector에 추가한다.
		System.out.println(p + "을/를 구입하셨습니다.");
	}
	
	void refund(Product23 p) {
		if(item.remove(p)) {			// 구입한 제품을 Vector에서 삭제한다.
			money += p.price;
			bonusPoint -= p.bonusPoint;
			System.out.println(p + "을/를 반품하셨습니다.");
		} else {
			System.out.println("구입하신 제품 중 해당 제품이 없습니다.");
		}
	}
	
	void summary() {
		int sum = 0;
		String itemList = "";
		
		if(item.isEmpty()) {
			System.out.println("구입하신 제품이 없습니다.");
		}
		
		for(int i=0; i<item.size(); i++) {
			Product23 p = (Product23)item.get(i);
			sum += p.price;
			itemList += (i==0) ? "" + p : ", " + p;
		}
		
		System.out.println("구입하신 물품의 총금액은 " + sum + "만원입니다.");
		System.out.println("구입하신 제품은 " + itemList + "입니다.");
	}
}