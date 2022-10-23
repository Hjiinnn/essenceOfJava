package essence.ch6;

/**
 * 기본 생성자가 컴파일러에 의해 추가되는 경우는 클래스에 정의된 생성자가 하나도 없을 때 뿐이다.
 */

class Data3 {
	int value;
}

class Data4 {
	int value;
	
	// 매개변수가 있는 생성자.
	Data4(int x) {
		value = x;
	}
}

class ConstructorTest {

	public static void main(String[] args) {
		Data3 d1 = new Data3();
		//Data4 dw = new Data4();		// ERROR!!! compile error 발생 (The constructor Data2() is undefined)

	}

}
