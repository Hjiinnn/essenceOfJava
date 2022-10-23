package essence.ch6;

/**
 * 반환타입이 참조형이라는 것은 메서드가 객체의 주소를 반환한다는 것을 의미한다.
 */

class Data2 { int x; }

class ReferenceRetrunEx {

	public static void main(String[] args) {
		Data2 d = new Data2();
		d.x = 10;
		
		Data2 d2 = copy(d);
		System.out.println("d.x = " + d.x);
		System.out.println("d2.x = " + d2.x);
	}
	
	static Data2 copy(Data2 d) {
		Data2 tmp = new Data2();
		tmp.x = d.x;
		
		return tmp;
	}

}
