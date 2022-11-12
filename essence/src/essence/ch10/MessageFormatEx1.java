package essence.ch10;

import java.text.MessageFormat;

public class MessageFormatEx1 {

	public static void main(String[] args) {

		String msg = "Name : {0} \nTel: {1} \nAge: {2} \nBirthday : {3}";
		
		Object[] arguments = {
				"김모모", "010-1234-5678", "26", "11-05"
		};
		
		String result = MessageFormat.format(msg, arguments);
		System.out.println(result);
	}

}
