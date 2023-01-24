package essence.ch7;

/**
 * 멤버변수가 조상 클래스와 자손 클래스에 중복으로 정의된 경우 참조변수의 타입에 따라 달라진다.
 */
public class BindingTest {

	public static void main(String[] args) {
		Parent3 p = new Child3();
		Child3 c = new Child3();
		
		System.out.println("p.x = " + p.x);
		p.method();
		
		System.out.println("c.x = " + c.x);
		c.method();
	}

}

class Parent3 {
	int x = 100;
	
	void method() {
		System.out.println("Parent Method");
	}
}

class Child3 extends Parent3 {
	int x = 200;
	
	void method() {
		System.out.println("child Method");
	}
}
