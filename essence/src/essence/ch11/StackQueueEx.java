package essence.ch11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class StackQueueEx {

	public static void main(String[] args) {
		Stack st = new Stack();
		Queue q = new LinkedList();	// LinkedList는 Queue인터페이스의 구현체이다.
		
		/**
		 * Object push(Object item):Stack에 객체(item)을 저장한다.
		 */
		st.push("0");
		st.push("1");
		st.push("2");
		
		/**
		 * boolean offer(Object o):Queue에 객체를 저장한다.
		 */
		q.offer("0");
		q.offer("1");
		q.offer("2");
		
		/**
		 * boolean empty():Stack이 비어있는지 확인한다.
		 * Object pop():Stack의 맨 위에 저장된 객체를 꺼낸다. (비어있을 때는 EmptyStackException발생)
		 */
		System.out.println("= Stack =");
		while(!st.empty()) {
			System.out.println(st.pop());
		}
		
		/**
		 * Queue에서 객체를 꺼내서 반환한다. 비어있으면 null반환
		 */
		System.out.println("= Queue =");
		while(!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}

}
