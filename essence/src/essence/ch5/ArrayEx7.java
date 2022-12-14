package essence.ch5;

import java.util.Arrays;

/**
 * 배열의 요소의 순서를 반복해서 바군다. (카드번호 섞기)
 */
class ArrayEx7 {

	public static void main(String[] args) {
		int[] numArr = new int[10];
		
		for(int i=0; i<numArr.length; i++) {
			numArr[i] = i;
			System.out.print(numArr[i]);
		}
		System.out.println();
		
		/**
		 * numArr[0]과 numArr[n]의 값을 서로 바꾸는 작업을 100번함으로써 값을 섞는다.
		 */
		for(int i=0; i<100; i++) {
			int n = (int)(Math.random() * 10);
			int tmp = numArr[0];
			numArr[0] = numArr[n];
			numArr[n] = tmp;
		}
		
		System.out.println(Arrays.toString(numArr));
	}

}
