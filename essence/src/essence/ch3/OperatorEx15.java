package essence.ch3;

/**
 * 소문자를 대문자로 변환한다.
 * 대문자와 소문자 간 코드값 차이는 10진수로 32이다.
 */
class OperatorEx15 {

	public static void main(String[] args) {
		char lowerCase = 'a';
		char upperCase = (char)(lowerCase - 32);
		System.out.println(upperCase);
	}

}
