package essence.ch3;

/**
 * 증감연산자가 수식이나 메서드 호출에 포함되지 않고 독립적인 하나의 문장으로 쓰인 경우에는 전위형과 후위형의 차이가 없다.
 */
class OperatorEx1 {

	public static void main(String[] args) {
		int i = 5;
		i++;	// i=i+1과 같은 의미로 ++i;로 바꿔써도 동일하다.
		System.out.println(i);
		
		i = 5;
		++i;	
		System.out.println(i);
	}

}
