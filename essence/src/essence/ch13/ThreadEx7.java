package essence.ch13;

import javax.swing.JOptionPane;

/**
 * 두개의 쓰레드로 사용자의 입력을 받는 작업과 화면에 숫자를 출력하는 작업 나눠서 처리하기 때문에
 * 사용자가 입력을 마치지 않았어도 화면에 숫자가 출력된다.
 */
class ThreadEx7 {

	public static void main(String[] args) {
		ThreadEx7_1 th1 = new ThreadEx7_1();
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
		System.out.println("입력하신 값은 " + input + "입니다.");
	}

}

class ThreadEx7_1 extends Thread {
	public void run() {
		for(int i=10; i>0; i--) {
			System.out.println(i);
			
			try {
				sleep(1000);
			} catch(Exception e) {
				
			}
		}
	}
}
