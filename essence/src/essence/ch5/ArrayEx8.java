package essence.ch5;

/**
 * 배열의 요소의 순서를 반복해서 바군다. (로또번호 생성)
 */
class ArrayEx8 {

	public static void main(String[] args) {
		int[] ball = new int[45];
		
		for(int i=0; i<ball.length; i++) {
			ball[i] = i+1;
		}
		
		int temp = 0;
		int j = 0;
		
		for(int i=0; i<6; i++) {
			j = (int)(Math.random() * 45);	// 0~44
			temp = ball[i];
			ball[i] = ball[j];
			ball[j] = temp;
		}
		
		for(int i=0; i<6; i++) {
			System.out.printf("ball[%d] = %d%n", i, ball[i]);
		}
	}

}
