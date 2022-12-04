package essence.ch3;

/**
 * 정수형은 실수형과 달리 근사값으로 저장되기 때문에 오차가 발생할 수 있다. 
 * double타입의 값과 float타입의 값을 비교할 때 오차 없이 올바른 결과를 얻기 위해서는 double타입의 값을 float타입으로 형변환한 다음 비교하면 된다.
 */
class OperatorEx22 {

	public static void main(String[] args) {
		float f = 0.1f;
		double d = 0.1;
		double d2 = (double)f;
		
		System.out.printf("10.0 == 10.0f %b%n", 10.0 == 10.0f);
		System.out.printf("0.1 == 0.1f %b%n", 0.1 == 0.1f);
		System.out.printf("f = %19.17f%n", f);
		System.out.printf("d = %19.17f%n", d);
		System.out.printf("d2 = %19.17f%n", d2);
		System.out.printf("d == f %b%n", d == f);
		System.out.printf("d == d2 %b%n", d == d2);
		System.out.printf("d2 == f %b%n", d2 == f);
		System.out.printf("(float)d == f %b%n", (float)d == f);
	}

}
