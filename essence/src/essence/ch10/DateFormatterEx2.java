package essence.ch10;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterEx2 {

	public static void main(String[] args) {

		LocalDate newYear = LocalDate.parse("2022-01-04", DateTimeFormatter.ISO_LOCAL_DATE);
		
		LocalDate date = LocalDate.parse("2022-01-01");
		LocalTime time = LocalTime.parse("23:45:30");
		LocalDateTime dateTime = LocalDateTime.parse("2022-01-01T23:45:30");
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime endOfyear = LocalDateTime.parse("2022-12-31 23:59:59", pattern);
		
		System.out.println(newYear);
		System.out.println(date);
		System.out.println(time);
		System.out.println(dateTime);
		System.out.println(endOfyear);
	}
}
