package essence.ch6;

/**
 * main메서드 역시 자기 자신을 호출하는 것이 가능하며, 아무런 조건도 없이 계속해서 자기 자신을 다시 호출하기 때문에 무한호출에 빠지게 된다.
 * main메서드가 종료되지 않고 호출스택에 계속해서 쌓이게 되므로 결국 호출스택의 메모리 한계를 넘게 되고 StatckOverflowError가 발생하여 프로그램은 비정상적으로 종료된다.
 */
class MainTest {

	public static void main(String[] args) {
		main(null);	// 재귀호출
	}

}
