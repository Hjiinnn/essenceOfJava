package essence.ch6;

/**
 * 참조형 매개변수를 활용하면 반환값이 없어도 메서드의 실행결과를 얻어올 수 있다.
 */

class ReturnTest {

	public static void main(String[] args) {
		ReturnTest r = new ReturnTest();
		
		int result = r.add(3, 5);
		System.out.println(result);
		
		int[] result2 = {0};	// 배열을 생성하고 result2[0]의 값을 0으로 초기화
		r.add(3, 5, result2);	// 배열을 add 메서드의 매개변수로 전달.
		System.out.println(result2[0]);

	}

	int add(int a, int b) {
		return a + b;
	}
	
	void add(int a, int b, int[] result) {
		result[0] = a + b;
	}
}
