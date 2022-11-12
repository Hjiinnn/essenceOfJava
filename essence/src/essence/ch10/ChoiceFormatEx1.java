package essence.ch10;

import java.text.ChoiceFormat;

public class ChoiceFormatEx1 {

	public static void main(String[] args) {

		double[] limits = {60, 70, 80, 90};				// 경계값. 반드시 오름ㅊ아순으로 정렬되어 있어야 한다.
		String[] grades = {"D", "C", "B", "A"};			// 치환할 문자열 저장. 경계값에 의해 정의된 범위의 개수와 일치해야 한다. (IllegalArgumentException)
		
		int[] scores = {100, 95, 88, 70, 52, 60, 70};
		
		ChoiceFormat form = new ChoiceFormat(limits, grades);
		
		for(int i=0; i<scores.length; i++) {
			System.out.println(scores[i] + " : " + form.format(scores[i]));
		}
	}

}
