package essence.ch3;

/**
 * 실수형 변수 pi의 값을 반올림하여 소수점 셋째 자리까지만 빼내는 방법
 */
class OperatorEx17 {

	public static void main(String[] args) {
		double pi = 3.141592;
		double shortPi = (int)(pi * 1000 + 0.5) / 1000.0;
		
		System.out.println(shortPi);
	}

}
