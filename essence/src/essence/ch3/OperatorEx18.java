package essence.ch3;

/**
 * Math.rount() 메서드를 이용해 실수 pi값을 소수점 넷쨰 자리인 5에서 반올림 한다.
 */
class OperatorEx18 {

	public static void main(String[] args) {
		double pi = 3.141592;
		double shortPi = Math.round(pi * 1000) / 1000.0;
		System.out.println(shortPi);
	}

}
