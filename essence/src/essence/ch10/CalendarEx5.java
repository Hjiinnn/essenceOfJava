package essence.ch10;

import java.util.Calendar;

/**
 * 단, 일 필드가 말일일 때, roll메서드를 이용하여 월 필드를 변경하면 일 필드에 영향을 미칠 수 있다. (add메서드도 마찬가지)
 */
public class CalendarEx5 {

	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		
		date.set(2015, 0, 31); // 2015년 1월 31일
		System.out.println(toString(date));
		date.roll(Calendar.MONTH, 1);
		System.out.println(toString(date));
	}

	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1) + "월 " + date.get(Calendar.DATE) + "일";
	}

}
