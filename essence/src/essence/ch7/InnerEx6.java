package essence.ch7;

class InnerEx6 {

	// 익명 클래스
	Object iv = new Object() {
		void method() {};
	};
	
	// 익명 클래스
	static Object cv = new Object() {
		void method() {};
	};
	
	void myMethod() {
		// 익명 클래스
		Object lv = new Object() {
			void method() {
				;
			}
		};
	}
	
}
