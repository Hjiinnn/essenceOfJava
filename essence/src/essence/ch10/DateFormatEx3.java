package essence.ch10;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * parse(String source)를 사용하여 문자열source를 날짜Date인스턴스로 쉽게 변환할 수 있다.
 */
public class DateFormatEx3 {

	public static void main(String[] args) {

		DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			Date d = df.parse("2015년 11월 23일");
			System.out.println(df2.format(d));
		} catch (Exception e) {}
	}

}
