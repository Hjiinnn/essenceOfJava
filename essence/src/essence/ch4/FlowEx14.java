package essence.ch4;

/**
 * 불필요한 변수는 줄이는 것이 좋다.
 * i와 j 변수2개를 사용하기 보다 조건식에 11-j를 사용함으로써 불필요한 변술르 줄일 수도 있다.
 *
 */
class FlowEx14 {

	public static void main(String[] args) {
		for(int i=1, j=10; i<=10; i++, j--)
			System.out.printf("%d \t %d%n", i, j);
	}

}
