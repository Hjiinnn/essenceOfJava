package essence.ch11;

import java.util.Vector;

class VectorEx1 {

	public static void main(String[] args) {
		
		Vector v = new Vector(5);
		v.add("1");
		v.add("2");
		v.add("3");
		print(v);
		
		/**
		 * void trimToSize():용량을 크기에 맞게 줄인다.(빈 공간 삭제)
		 */
		v.trimToSize();
		System.out.println("=== After trimToSize() ===");
		print(v);
		
		/**
		 * void ensureCapacity(int minCapacity):ArrayList의 용량이 최소한 minCapacity가 되도록한다.
		 */
		v.ensureCapacity(6);
		System.out.println("=== After ensureCapaity(6) ===");
		print(v);
		
		/**
		 * 사이즈가 7이 되도록 변경한다.
		 * 만일 v의 capacity가 충분하면 새로 인스턴스를 생성하지 않아도 되지만, capacity가 부족할 경우 자동적으로 기존의 크기보다 2배의 크기로 증가된다.
		 * 그래서 여기서 v의 capacity는 12가 된다.
		 */
		v.setSize(7);
		System.out.println("=== After setSize(7) ===");
		print(v);
		
		/**
		 * v의 모든 요소를 삭제한다.
		 */
		v.clear();
		System.out.println("=== After clear() ===");
		print(v);
		
	}

	public static void print(Vector v) {
		System.out.println(v);
		System.out.println("size : " + v.size());
		System.out.println("capacity : " + v.capacity());
	}
}
