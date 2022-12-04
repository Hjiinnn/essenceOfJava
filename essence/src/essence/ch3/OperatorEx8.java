package essence.ch3;

/**
 * int타입과 int타입의 연산결과는 int타입이기 때문에 long영으로 자동 형변환되어도 값은 변하지 않는다.
 * 올바른 결과를 얻으려면 변수 a또는 b의 타입을 long으로 형변환 해야 한다.
 */
class OperatorEx8 {

	public static void main(String[] args) {
		int a = 1_000_000;	// 1,000,000 1백만
		int b = 2_000_000;	// 2,000,000 2백만
		
		long c = a * b;		// a * b = 2,000,000,000,000 ?
		
		System.out.println(c);
	}

}
