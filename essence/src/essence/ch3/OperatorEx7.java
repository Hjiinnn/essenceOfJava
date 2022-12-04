package essence.ch3;

/**
 * 큰 자료형에서 작은 자료형으로 변환하면 데이터의 손실이 발생하므로 값이 바뀔 수 있다.
 * 300은 byte형의 범위를 넘기 때문에 byte형으로 변환하면 데이터 손실이 발생하여 결국 44가 byte형 변수 c에 저장된다.
 */
class OperatorEx7 {

	public static void main(String[] args) {
		byte a = 10;
		byte b = 30;
		byte c = (byte)(a * b);
		System.out.println(c);
	}

}
