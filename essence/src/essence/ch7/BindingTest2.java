package essence.ch7;

/**
 * 자손 클래스에서 조상 클래스의 멤버를 중복으로 정의하지 않았을 때는 참조변수의 타입에 따른 변화는 없다.
 */
public class BindingTest2 {

	public static void main(String[] args) {
		Parent4 p = new Child4();
		Child4 c = new Child4();
		
		System.out.println("p.x = " + p.x);
		p.method();
		
		System.out.println("c.x = " + c.x);
		c.method();
		
	}

}

class Parent4 {
	int x = 100;
	
	void method() {
		System.out.println("Parent method");
	}
}

class Child4 extends Parent4 {}
