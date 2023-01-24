package essence.ch7;

class FighterTest {

	public static void main(String[] args) {
		Fighter f = new Fighter();
		
		if(f instanceof Unit)
			System.out.println("f는 Unit클래스의 자손입니다.");

		if(f instanceof Fightable)
			System.out.println("f는 Fightable클래스의 자손입니다.");
		
		if(f instanceof Movable)
			System.out.println("f는 Movable클래스의 자손입니다.");
		
		if(f instanceof Attackable)
			System.out.println("f는 Attackable클래스의 자손입니다.");
		
		if(f instanceof Object)
			System.out.println("f는 Object클래스의 자손입니다.");
	}

}


/**
 * Unit클래스로부터 상속받고, Fightable인터페이스만을 구현했다.
 * 그리고 Unit클래스는 Object클래스의 자손이고, Fightable인터페이스는 Attackable과 Movable의 자손이므로
 * Fighter클래스는 이 모든 클래스와 인터페이스의 자손이 되는 셈이다.
 * 
 * 
 * error : Cannot reduce the visibility of the inherited method from Movable
 * 각 인터페이스에 정의된 추상 메서드들은 `public abstract` 이 생략된 것이다.
 * 오버라이딩 할 때는 조상의 메서드보다 넓은 범위의 접근 제어자를 사용해야 한다.
 * 따라서 이 구현체의 접근제어자는 public을 쓸 수 밖에 없다.
 */
class Fighter extends Unit implements Fightable {
	public void move(int x, int y) {}
	public void attack(Unit u) {}
}

class Unit {
	int currentHP;	// 유닛의 체력
	int x;			// 유닛의 위치(x좌표)
	int y;			// 유닛의 위치(y좌표)
}

/**
 * 인터페이스는 인터페이스로부터만 상속받을 수 있고, 다중상속이 가능하다.
 */
interface Fightable extends Movable, Attackable {}


interface Movable {
	void move(int x, int y);
}
interface Attackable {
	void attack(Unit u);
}
