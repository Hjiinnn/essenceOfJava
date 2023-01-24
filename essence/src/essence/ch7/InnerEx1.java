package essence.ch7;

class InnerEx1 {

	/**
	 * 인스턴스 클래스
	 */
	class InstanceInner {
		int iv = 100;
//		static int cv = 100;	// ERROR!! The field cv cannot be declared static in a non-static inner type, unless initialized with a constant expression. ; static변수를 선언할 수 없다.
		final static int CONST = 100;	// final static은 상수이므로 허용
	}
	
	/**
	 * 스태틱 클래스
	 */
	static class StaticInner {
		int iv = 200;
		static int cv = 200;	// static클래스만 static멤버를 정의할 수 있다.
	}
	
	void myMethod() {
		/**
		 * 지역 클래스
		 */
		class LocalInner {
			int iv = 300;
//			static int cv = 300; 	// ERROR!! The field cv cannot be declared static in a non-static inner type, unless initialized with a constant expression. ; static변수를 선언할 수 없다.
			final static int CONST = 300;	// final static은 상수이므로 허용
		}
	}
	
	public static void main(String[] args) {
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);
	}

}
