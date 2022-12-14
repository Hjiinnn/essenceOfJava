package essence.ch5;

/**
 * 오름차순, 내림차순으로 배열을 정렬하기
 */
class ArrayEx10 {

	public static void main(String[] args) {
		
		// 1. 배열 생성
		int[] numArr = new int[10];
		
		for(int i=0; i<numArr.length; i++) {
			System.out.print(numArr[i] = (int)(Math.random() * 10));
		}
		System.out.println();
		
		
		// 2. 정렬하기
		for(int i=0; i<numArr.length-1; i++) {
			boolean changed = false;
			
			for(int j=0; j<numArr.length-1-i; j++) {
				if(numArr[j] > numArr[j+1]) {
					int temp = numArr[j];
					numArr[j] = numArr[j+1];
					numArr[j+1] = temp;
					changed = true;
				}
			}
			
			if(!changed) break;	// 여기서 걸리면 바깥 for문으로 이동하여 다음 턴 실행
			
			for(int k=0; k<numArr.length; k++) {
				System.out.print(numArr[k]);
			}
			System.out.println();
		}
		
	}

}
