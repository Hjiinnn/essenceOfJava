package essence.ch7;

class DefaultMethodTest {

	public static void main(String[] args) {
		Child30 c = new Child30();
		c.method1();
		c.method2();
		MyInterface.staticMethod();
		MyInterface2.staticMethod();
	}

}

class Child30 extends Parend30 implements MyInterface, MyInterface2 {
	public void method1() {
		System.out.println("method1() in Child30");
	}
}

class Parend30 {
	public void method2() {
		System.out.println("method2() in Parent30");
	}
}

interface MyInterface {
	default void method1() {
		System.out.println("method1() in MyInterface");
	}
	
	default void method2() {
		System.out.println("method2() int MyInterface");
	}
	
	static void staticMethod() {
		System.out.println("staticMethod() in MyInterface");
	}
}

interface MyInterface2 {
	default void method1() {
		System.out.println("method1() in MyInterface2");
	}
	
	static void staticMethod() {
		System.out.println("staticMethod() in MyInterface2");
	}
}

