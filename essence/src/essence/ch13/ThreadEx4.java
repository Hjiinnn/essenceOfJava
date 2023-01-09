package essence.ch13;

/**
 * '-'를 출력하는 작업과 '|'를 출력하는 작업을 하나의 쓰레드가 연속적으로 처리하는 시간을 측정한다.
 */
class ThreadEx4 {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		for(int i=0; i<300; i++) {
			System.out.printf("%s", new String("-")	);
		}
		
		System.out.println("소요시간1:" + (System.currentTimeMillis() - startTime));
		
		for(int i=0; i<300; i++) {
			System.out.printf("%s", new String("|"));
		}
		
		System.out.println("소요시간2:" + (System.currentTimeMillis() - startTime));
	}

}
