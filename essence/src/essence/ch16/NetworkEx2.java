package essence.ch16;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class NetworkEx2 {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://www.codechobo.com:80/sample/hello.html?referer=codechobo#index1");
		
		System.out.println("getAuthority() : " + url.getAuthority());		// 호스트명과 포트를 문자열로 반환한다.
		System.out.println("getContent() : " + url.getContent());			// URL의 Content객체를 반환한다.
		System.out.println("getDefaultPort() : " + url.getDefaultPort());	// URL의 기본 포트를 반환한다. (http는 80)
		System.out.println("getPort() : " + url.getPort());					// 포트를 반환한다.
		System.out.println("getFile() : " + url.getFile());					// 파일명을 반환한다.
		System.out.println("getHost() : " + url.getHost());					// 호스트명을 반환한다.
		System.out.println("getPath() : " + url.getPath());					// 경로를 반환한다.
		System.out.println("getProtocol() : " + url.getProtocol());			// 프로토콜을 반환한다.
		System.out.println("getQuery() : " + url.getQuery());				// 쿼리를 반환한다.
		System.out.println("getRef() : " + url.getRef()); 					// 참조를 반환한다.
		System.out.println("getUserInfo() : " + url.getUserInfo()); 		// 사용자 정보를 반환한다.
		System.out.println("toExternalForm() : " + url.toExternalForm()); 	// URL을 문자열로 변환하여 반환한다.
		System.out.println("toURI() : " + url.toURI()); 					// URL을 URI로 변환하여 반환한다.
	}

}
