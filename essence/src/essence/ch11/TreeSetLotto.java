package essence.ch11;

import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet은 저장할 때 이미 정렬하기 때문에 읽어올 때 따로 정렬할 필요가 없다.
 *
 */
class TreeSetLotto {

	public static void main(String[] args) {
		Set set = new TreeSet();
		
		for(int i=0; set.size() < 6; i++) {
			int num = (int)(Math.random() * 45) + 1;
			set.add(num);
		}
		
		System.out.println(set);
	}

}
