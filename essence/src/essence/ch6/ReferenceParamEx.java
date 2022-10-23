package essence.ch6;

/**
 * 참조형 매개변수(Reference Param)는 값을 읽고 변경할 수 있다. (Read and Write)
 */

class Data1 { int x; }

class ReferenceParamEx {

	public static void main(String[] args) {
		Data1 d = new Data1();
		d.x = 10;
		System.out.println("main() : x = " + d.x);
		
		/*
		 * 1. change 함수가 호출되면서 참조변수 d의 주소가 매개변수 d에 복사됨. 이제 매개변수 d에 저장된 주소로 x에 접근이 가능함
		 * 2. change메서드에서 매개변수 d로 x의 값을 1000으로 변경
		 * 3. change메서드가 종료되면서 매개변수 d는 스택에서 제거됨
		 */
		change(d);
		System.out.println("After change(d)");
		System.out.println("main() : x = " + d.x);

	}
	
	static void change(Data1 d) {
		d.x = 1000;
		System.out.println("change() : x = " + d.x);
	}

}
