package essence.ch3;

/**
 * int형보다 작은 byte형을 연산하게 되면 두 개의 피연산자들의 자료형이 자동적으로 int형으로 변환된 다음 연산이 수행된다.
 * 그래서 int형(4byte)의 연산 결과를 1byte의 변수에 형변환 없이 저장하려고 하면 에러가 발생하게 된다.
 * 따라서 명시적으로 형변환을 해주어야 한다.
 */
class OperatorEx6 {

	public static void main(String[] args) {
		byte a = 10;
		byte b = 20;
		//byte c = a + b; // Unresolved compilation problem: Type mismatch: cannot convert from int to byte
		byte c = (byte)(a + b);
		System.out.println(c);
	}

}
