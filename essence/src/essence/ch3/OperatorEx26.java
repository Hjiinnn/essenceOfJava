package essence.ch3;

/**
 * 변수 b에 증감 연산자를 넣어 우측 피연산자가 처리되면, b의 값이 증가되도록 했지만, 두 번의 논리연산 후에도 b의 값은 여전히 0이다.
 * 우측 피연산자를 평가하지 않았기 때문이다.
 */
class OperatorEx26 {

	public static void main(String[] args) {
		int a = 5;
		int b = 0;
		
		System.out.printf("a=%d, b=%d%n", a, b);
		System.out.printf("a!=0 || ++b!=0 = %b%n", a!=0 || ++b!=0);
		System.out.printf("a=%d, b=%d%n", a, b);
		System.out.printf("a==0 && ++b!=0 = %b%n", a==0 && ++b!=0);
		System.out.printf("a=%d, b=%d%n", a, b);
		
	}

}
