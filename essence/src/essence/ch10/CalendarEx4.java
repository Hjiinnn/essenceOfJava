package essence.ch10;

import java.util.Calendar;

/**
 * add(int field, int amount) : 원하는 만큼 증감시킬 수 있기 때문에 특정 날짜 또는 시간을 기점으로 일정기간 전후의 날짜와 시간을 알아낼 수 있다.
 * roll(int filed, int amount) : 값을 증감시킬 수 있지만, add메서드와 달리 다른 필드에 영향을 미치지 않는다.
 * 
 * 예를 들어 add메서드는 날짜필드의 값을 31만큼 증가시키면 다음 달로 넘어가므로 월필드의 값도 1 증가하지만,
 * roll메서드는 월 필드의 값은 변하지 않고 일 필드의 값만 바뀐다.
 */
public class CalendarEx4 {

	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		date.set(2015, 7, 31);	// 2015년 8월 31일
		
		System.out.println(toString(date));
		System.out.println("= 1일 후 =");
		date.add(Calendar.DATE, 1);
		System.out.println(toString(date));
		
		System.out.println("= 6달 전 =");
		date.add(Calendar.MONTH, -6);
		System.out.println(toString(date));
		
		System.out.println("= 31일 후(roll) =");
		date.roll(Calendar.DATE, 31);
		System.out.println(toString(date));
		
		System.out.println("= 31일 후(add) =");
		date.add(Calendar.DATE, 31);
		System.out.println(toString(date));
	}
	
	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1) + "월 " + date.get(Calendar.DATE) + "일";
	}

}
