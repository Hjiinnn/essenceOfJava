package essence.ch7;

class PolyArgumentTest {

	public static void main(String[] args) {
		Buyer b = new Buyer();
		
		b.buy(new Tv21());
		b.buy(new Computer());
		
		System.out.println("현재 남은 돈은 " + b.money + "만원입니다.");
		System.out.println("현재 보너스점수는 " + b.bonusPoint + "점입니다.");
	}

}

class Product {
	int price;		// 제품의 가격
	int bonusPoint; // 제품구매 시 제공하는 보너스 점수
	
	Product(int price) {
		this.price = price;
		bonusPoint = (int)(price/10.0);	// 보너스점수 = 제품 가격의 10%
	}
}

class Tv21 extends Product {
	Tv21() {
		// 조상 클래스 초기화
		super(100);		// Tv가격을 100만원으로 한다.
	}
	
	public String toString() {
		return "Tv";
	}
	
}

class Computer extends Product {
	Computer() {
		// 조상 클래스 초기화
		super(200);		// Computer가격을 200만원으로 한다.
	}
	
	public String toString() {
		return "Computer";
	}
}

// 고객
class Buyer {
	int money = 1000;
	int bonusPoint = 0;
	
	// 다형성 적용!
	/*
	 * 매개변수 타입을 Tv나 Computer으로 주지 않고 조상 클래스인 Product로 줌으로써
	 * 각 매개변수마다 메서드를 만들지 않고 하나의 메서드만 만들어도 된다. 
	 */
	void buy(Product p) {
		if(money < p.price) {
			System.out.println("잔액이 부족하여 물건을 살 수 없습니다.");
			return;
		}
		
		money -= p.price;				// 가진 돈에서 제품의 가격을 뺌
		bonusPoint += p.bonusPoint;		// 제품의 보너스 점수 추가
		System.out.println(p + "을/를 구입하셨습니다.");
	}
}