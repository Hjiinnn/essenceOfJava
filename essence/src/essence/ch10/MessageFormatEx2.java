package essence.ch10;

import java.text.MessageFormat;

public class MessageFormatEx2 {

	public static void main(String[] args) {

		String tableName = "CUST_INFO";
		String msg = "INSERT INTO " + tableName + " VALUES (''{0}'', ''{1}'', ''{3}'');";
		
		Object[][] arguments = {
				{"김모모", "010-1234-5678", "26", "11-05"},
				{"박미미", "010-3456-1234", "21", "08-14"},
		};
		
		for(int i=0; i<arguments.length; i++) {
			String result = MessageFormat.format(msg, arguments[i]);
			System.out.println(result);
		}
	}

}
