package essence.ch3;

/**
 * 다른 수식에 포함되거나 메서드의 매개변수로 사용된 경우 전위형과 후위형의 결과는 다르다.
 */
class OperatorEx2 {

	public static void main(String[] args) {
		int i=5, j=0;
		
		j = i++;
		System.out.println("j=i++; 실행 후, i=" + i + ", j=" + j);
		
		i = 5;
		j = 0;
		
		j = ++i;
		System.out.println("j=++i; 실행 후, i=" + i + ", j=" + j);
	}

}
