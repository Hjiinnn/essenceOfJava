package essence.ch3;

/**
 * 같은 의미의 식이라도 연산의 순서에 따라 다른 결과를 얻을 수 있다.
 * 먼저 곱하는 경우 int의 범위를 넘어서기 때문에 오버플로우가 발생한다.
 */
class OperatorEx10 {

	public static void main(String[] args) {
		int a = 1000000;
		
		int result1 = a * a / a ;
		int result2 = a / a * a;
		
		System.out.printf("%d * %d / %d = %d%n", a, a, a, result1);
		System.out.printf("%d / %d * %d = %d%n", a, a, a, result2);
	}

}
