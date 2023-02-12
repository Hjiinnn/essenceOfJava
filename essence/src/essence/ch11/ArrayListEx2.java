package essence.ch11;

import java.util.ArrayList;
import java.util.List;

class ArrayListEx2 {

	public static void main(String[] args) {
		final int LIMIT = 10;
		String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
		int length = source.length();	// 43
		
		List list = new ArrayList(length/LIMIT + 10);
		
		// 0~10, 10~20, 20~30, 30~40, 40~42
		for(int i=0; i<length; i+=LIMIT) {
			if(i+LIMIT < length) {
				list.add(source.substring(i, i+LIMIT));
			}else {
				list.add(source.substring(i));
			}
		}
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
