package essence.ch7;

class PolyArgumentTest2 {

	public static void main(String[] args) {
		Buyer22 b = new Buyer22();
		
		b.buy(new Tv22());
		b.buy(new Computer22());
		b.buy(new Audio22());
		b.summary();
	}

}

class Product22 {
	int price;		// 제품의 가격
	int bonusPoint; // 제품구매 시 제공하는 보너스 점수
	
	Product22(int price) {
		this.price = price;
		bonusPoint = (int)(price/10.0);	// 보너스점수 = 제품 가격의 10%
	}
	
	Product22() {}
}

class Tv22 extends Product22 {
	Tv22() {
		// 조상 클래스 초기화
		super(100);		// Tv가격을 100만원으로 한다.
	}
	
	public String toString() {
		return "Tv";
	}
	
}

class Computer22 extends Product22 {
	Computer22() {
		// 조상 클래스 초기화
		super(200);		// Computer가격을 200만원으로 한다.
	}
	
	public String toString() {
		return "Computer";
	}
}

class Audio22 extends Product22 {
	Audio22() {
		super(50);
	}
	
	
	public String toString() {
		return "Audio";
	}
}

// 고객
class Buyer22 {
	int money = 1000;
	int bonusPoint = 0;
	Product22[] item = new Product22[10];	// 부모 클래스 타입 배열 안에 자식 객체를 담을 수 있다.
	int i = 0;
	
	// 다형성 적용!
	/*
	 * 매개변수 타입을 Tv나 Computer으로 주지 않고 조상 클래스인 Product로 줌으로써
	 * 각 매개변수마다 메서드를 만들지 않고 하나의 메서드만 만들어도 된다. 
	 */
	void buy(Product22 p) {
		if(money < p.price) {
			System.out.println("잔액이 부족하여 물건을 살 수 없습니다.");
			return;
		}
		
		money -= p.price;				// 가진 돈에서 제품의 가격을 뺌
		bonusPoint += p.bonusPoint;		// 제품의 보너스 점수 추가
		item[i++] = p;					// 제품을 Product22[] item에 저장한다.
		System.out.println(p + "을/를 구입하셨습니다.");
	}
	
	void summary() {
		int sum = 0;
		String itemList = "";
		
		for(int i=0; i<item.length; i++) {
			if(item[i] == null) break;
			
			sum += item[i].price;
			itemList += item[i] + ", ";
		}
		
		System.out.println("구입하신 물품의 총금액은 " + sum + "만원입니다.");
		System.out.println("구입하신 제품은 " + itemList + "입니다.");
	}
}