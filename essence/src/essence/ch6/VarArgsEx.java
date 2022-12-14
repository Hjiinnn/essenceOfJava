package essence.ch6;

/**
 * 가변인자를 선언한 메서드를 오버로딩하면, 메서드를 호출했을 때 구별되지 못하는 경우가 발생할 수 있기 때문에 가변인자를 사용한 메서드는 오버로딩 하지 않는 것이 좋다.
 */

class VarArgsEx {

	public static void main(String[] args) {
		String[] strArr = { "100", "200", "300" };
		
		System.out.println(concatenate("", "100", "200", "300"));
		System.out.println(concatenate("-", strArr));
		System.out.println(concatenate(",", new String[] {"1", "2", "3"}));
		System.out.println("[" + concatenate(",", new String[0])+ "]");
		System.out.println("[" + concatenate(",") + "]");
	}

	static String concatenate(String delim, String... args) {
		String result = "";
		
		for(String str : args) {
			result += str + delim;
		}
		
		return result;
	}
	
//	static String concatenate(String... args) {
//		return concatenate("", args);
//	}
	
}
