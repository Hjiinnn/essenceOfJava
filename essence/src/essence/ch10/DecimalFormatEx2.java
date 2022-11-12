package essence.ch10;

import java.text.DecimalFormat;

/**
 * parse()메서드를 이용하면 기호와 문자가 포함된 문자열을 숫자로 쉽게 변환할 수 있다.
 * (Integer.parseInt의 경우 콤마가 포함된 문자열을 숫자로 변환하지 못함)
 * 
 * parse(String source)는 DecimalFormat의 조상인 NumberFormat에 정의된 메서드이다.
 * Number클래스는 Integer, Double과 같은 숫자를 저장하는 래퍼 클래스의 조상이다.
 */
public class DecimalFormatEx2 {

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("#,###.##");
		DecimalFormat df2 = new DecimalFormat("#.###E0");
		
		try {
			Number num = df.parse("1,234,567.89");
			System.out.print("1,234,567.89" + " -> ");
			
			double d = num.doubleValue();
			System.out.print(d + " -> ");
			
			System.out.println(df2.format(num));
		} catch (Exception e) {}
	}

}
