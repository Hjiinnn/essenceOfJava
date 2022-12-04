package essence.ch3;

/**
 * 상수 또는 리터럴 간의 연산은 실행 과정동안 변하는 값이 아니기 때문에, 컴파일 시에 멐파일러가 계산해서 그 결과로 대체함으로써 코드를 보다 효율적으로 만든다.
 * 컴파일러가 미리 덧셈연산을 수행하기 때문에 실행 시에는 덧셈 연산이 수행되지 않는다. 그저 덧셈 결과인 문자 'b'를 변수 c2에 저장할 뿐이다.
 */
class OperatorEx13 {

	public static void main(String[] args) {
		char c1 = 'a';
		//char c2 = c1 + 1; // ERROR!!!
		char c2 = 'a' + 1;	// 에러 없음.. 왜??
		
		System.out.println(c2);
	}

}
