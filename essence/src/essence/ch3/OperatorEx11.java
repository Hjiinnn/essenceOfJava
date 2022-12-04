package essence.ch3;

/**
 * 사칙연산의 피연산자로 숫자뿐만 아니라 문자도 가능하다. 문자는 실제로 해당 문자의 윤코드(부호없는 정수)로 바뀌어 저장되므로 문자간의 사칙연산은 정수간의 연산과 동일하다.
 */
class OperatorEx11 {

	public static void main(String[] args) {
		char a = 'a';
		char d = 'd';
		char zero = '0';
		char two = '2';
		
		System.out.printf("'%c' - '%c' = %d%n", d, a, d - a);	// 'd' - 'a' = 3
		System.out.printf("'%c' - '%c' = %d%n", two, zero, two - zero);	// 'two' - 'zero' = 2
		System.out.printf("'%c'=%d%n", a, (int)a);
		System.out.printf("'%c'=%d%n", d, (int)d);
		System.out.printf("'%c'=%d%n", zero, (int)zero);
		System.out.printf("'%c'=%d%n", two, (int)two);
	}

}
