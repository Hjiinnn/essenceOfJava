package essence.ch3;

/**
 *	소문자a~z, 대문자 A~Z, 숫자0~9까지 연속적으로 코드가 지정되어 있다.
 */
class OperatorEx14 {

	public static void main(String[] args) {
		char c = 'a';
		for(int i=0; i<26; i++) {
			System.out.print(c++);
		}
		System.out.println();
		
		c = 'A';
		for(int i=0; i<26; i++) {
			System.out.print(c++);
		}
		System.out.println();
		
		c = '0';
		for(int i=0; i<10; i++) {
			System.out.print(c++);
		}
		System.out.println();
		
	}

}
