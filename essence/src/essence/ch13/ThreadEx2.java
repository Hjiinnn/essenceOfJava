package essence.ch13;

/**
 * 새로 생성한 쓰레드에서 고의로 예외를 발생시키고 예외가 발생한 당시의 호출스택을 출력한다.
 * 호출스택의 첫 번쨰 메서드가 main메서드가 아니라 run메서드인 것을 확인하자.
 * 한 쓰레드가 예외가 발생하여 종료되어도 다른 쓰레드의 실행에는 영향을 미치지 않는다.
 */
class ThreadEx2 {

	public static void main(String[] args) throws Exception {
		ThreadEx2_1 t1 = new ThreadEx2_1();		// 1. Thread의 자손 클래스의 인스턴스를 생성한다.
		t1.start();								// 2. 새로운 쓰레드를 생성하고, 쓰레드가 작업하는데 사용될 호출스택을 생성한다. 
	}

}

class ThreadEx2_1 extends Thread {
	public void run() {							// 3. 새로 생성된 호출스택에 run()이 호출되어, 쓰레드가 독립된 공간에서 작업을 수행한다.
		throwException();
	}
	
	public void throwException() {
		try {
			throw new Exception();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}


