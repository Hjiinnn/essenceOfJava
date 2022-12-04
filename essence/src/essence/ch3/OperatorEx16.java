package essence.ch3;

/**
 * 실수형 변수 pi의 값을 소수점 셋째 자리까지만 빼내는 방법
 */
class OperatorEx16 {

	public static void main(String[] args) {
		float pi = 3.141592f;
		float shortPi = (int)(pi * 1000) / 1000f;
		System.out.println(shortPi);
	}

}
