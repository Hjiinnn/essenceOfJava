package essence.ch11;

import java.util.EmptyStackException;
import java.util.Vector;

class MyStack extends Vector{

	public Object push(Object item) {
		addElement(item);
		return item;
	}
	
	public Object pop() {
		/**
		 * Object peek():Stack의 맨 위에 저장된 객체를 반환. pop()과 달리 Stack에서 객체를 꺼내지는 않음.(비었을 떄는 EmptyStackException발생)
		 */
		Object obj = peek();
		removeElementAt(size() - 1);
		return obj;
	}
	
	public Object peek() {
		int len = size();
		
		if(len == 0) {
			throw new EmptyStackException();
		}
		
		return elementAt(len - 1);
	}
	
	public boolean empty() {
		return size() == 0;
	}
	
	/**
	 * int searh(Object o):Stack에서 주어진 객체(o)를 찾아서 그 위치를 반환. 못찾으면 -1을 반환. (배열과 달리 위치는 1부터 시작함)
	 * @param o
	 * @return
	 */
	public int search(Object o) {
		int i = lastIndexOf(o);
		
		if(i >= 0) {
			return size() - 1;
		}
		
		return - 1;
	}
	
}
