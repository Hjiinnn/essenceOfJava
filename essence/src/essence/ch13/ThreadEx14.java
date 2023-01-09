package essence.ch13;

import javax.swing.JOptionPane;

/**
 * 예제13과 달리 시간 지연을 위해 sleep()메서드를 호출했지만, 카운트는 멈추지 않았따.
 * sleep()에 의해 잠시 멈춰있을 때 interrupt()를 호출하면 InterruptedException이 발생되고, 쓰레드의 interrupted상태는 false로 초기화 되기 때문이다.
 */
class ThreadEx14 {

	public static void main(String[] args) {
		ThreadEx14_1 th1 = new ThreadEx14_1();
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
		System.out.println("입력하신 값은 " + input + "입니다.");
		
		th1.interrupt();	// interrupted : true
		System.out.println("isInterrupted() : " + th1.isInterrupted());
	}

}

class ThreadEx14_1 extends Thread {
	public void run() {
		int i = 10;
		
		while(i != 0 && !isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000); 	// 1초 지연
			} catch(InterruptedException e) { 
				// interrupt(); // InterruptedException이 발생하여 interrupt상태가 false로 초기화 되므로 interrupt상태가 true가 되도록 바꿔주면 예제13과 동일하게 정상 카운트 됨
			}
		}
		
		System.out.println("카운트가 종료되었습니다.");
	}
}
