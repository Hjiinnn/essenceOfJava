package essence.ch7;

/**
 * 인터페이스 
 */
interface Parseable {
	public abstract void parse(String fileName);
}

/**
 *  넘겨받는 타입에 따른 구현체 리턴
 */
class ParserManager {
	public static Parseable getParset(String type) {
		if(type.equals("XML")) {
			return new XMLParser();
		} else {
			Parseable p = new HTMLParser();
			return p;
		}
	}
}

/**
 * 인터페이스 구현 - XMLParser
 */
class XMLParser implements Parseable {
	public void parse(String fileName) {
		System.out.println(fileName + " - XML parsing completed.");
	}
}

/**
 * 인터페이스 구현 - HTMLParser
 */
class HTMLParser implements Parseable {
	public void parse(String fileName) {
		System.out.println(fileName + " - HTML parsing completed.");
	}
}

class ParserTest {

	public static void main(String[] args) {
		Parseable parser = ParserManager.getParset("XML");
		parser.parse("document.xml");
		parser = ParserManager.getParset("HTML");
		parser.parse("document2.html");
	}

}
