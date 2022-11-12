package essence.ch10;

import java.text.MessageFormat;
import java.text.ParseException;

public class MessageFormatEx3 {

	public static void main(String[] args) throws ParseException {

		String[] data = {
			"INSERT INTO CUST_INFO VALUES ('김모모', '010-1234-5678', '26', '11-05');",
			"INSERT INTO CUST_INFO VALUES ('박미미', '010-3456-1234', '21', '08-14');"
		};
		
		String pattern = "INSERT INTO CUST_INFO VALUES ({0}, {1}, {2}, {3});";
		MessageFormat mf = new MessageFormat(pattern);
		
		for(int i=0; i<data.length; i++) {
			Object[] objs = mf.parse(data[i]);
			for(int j=0; j<objs.length; j++) {
				System.out.print(objs[j] + ",");
			}
			System.out.println();
		}
	}

}
