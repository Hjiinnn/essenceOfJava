package essence.ch3;

/**
 * 나눗셈 연산자와 나머지 연산자를 이용해서 몫과 나머지를 구한다.
 */
class OperatorEx19 {

	public static void main(String[] args) {
		int x = 10;
		int y = 8;
		
		System.out.printf("%d을 %d로 나누면, %n", x, y);
		System.out.printf("몫은 %d이고, 나머지는 %d입니다.%n", x / y, x % y);
	}

}
