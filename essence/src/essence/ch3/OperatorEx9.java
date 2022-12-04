package essence.ch3;

/**
 * int타입과 int타입의 연산결과는 int인데, 연산결과가 int타입의 최대값을 넘으면 오버플로우(overflow)가 발생한다.
 * 오버플로우가 발생한 값을 아무리 long타입의 변수에 저장해도 소용이 없다.
 */
class OperatorEx9 {

	public static void main(String[] args) {
		long a = 1_000_000 * 1_000_000;
		long b = 1_000_000 * 1_000_000L;
		
		System.out.println("a=" + a);
		System.out.println("b=" + b);
	}

}
