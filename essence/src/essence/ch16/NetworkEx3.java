package essence.ch16;

import java.net.URL;
import java.net.URLConnection;

class NetworkEx3 {

	public static void main(String[] args) {
		URL url = null;
		String address = "http://www.codechobo.com/sample/hello.html";
		
		try {
			url = new URL(address);
			URLConnection conn = url.openConnection();	// UrlConnection 객체 생성
			
			System.out.println("conn.toString() : " + conn);
			System.out.println("getAllowUserInteraction() : " + conn.getAllowUserInteraction()); 	// UserInteraction의 허용여부를 반환한다.
			System.out.println("getConnectTimeout() : " + conn.getConnectTimeout()); 				// 연결종료시간을 천분의 일초로 반환한다.
			//System.out.println("getContent() : " + conn.getContent()); 								// content객체를 반환한다.
			System.out.println("getContentEncoding() : " + conn.getContentEncoding()); 				// content의 인코딩을 반환한다.
			System.out.println("getContentLength() : " + conn.getContentLength());					// content의 크기를 반환한다.
			System.out.println("getContentType() : " + conn.getContentType()); 						// content의 타입을 반환한다.
			System.out.println("getDate() : " + conn.getDate()); 									// 헤더의 date필드 값을 반환한다.
			System.out.println("getDefaultAllowUserInteraction() : " + conn.getDefaultAllowUserInteraction()); 		// defaultAllowUserInteraction의 값을 반환한다.
			System.out.println("getDefaultUseCaches() : " + conn.getDefaultUseCaches()); 			// useCache의 디폴트 값을 얻는다.
			System.out.println("getDoInput() : " + conn.getDoInput()); 								// doInput필드값을 얻는다.
			System.out.println("getDoOutput() : " + conn.getDoOutput()); 							// doOutput필드값을 얻는다.
			System.out.println("getExpiration() : " + conn.getExpiration()); 						// 자원(URL)의 만료일자를 얻는다. (천분의 일초단위)
			System.out.println("getHeaderFields() : " + conn.getHeaderFields()); 					// 헤더의 모든 필드와 값이 저장된 Map을 반환한다.
			System.out.println("getIfModifiedsSince() : " + conn.getIfModifiedSince()); 			// ifModifiedSince(변경여부)필드의 값을 반환한다.
			System.out.println("getLastModified() : " + conn.getLastModified()); 					// LastModified(최종변경일)필드의 값을 반환한다.
			System.out.println("getReadTimeout() : " + conn.getReadTimeout()); 						// 읽기제한시간의 값을 반환한다.(천분의 일초)
			System.out.println("getURL() : " + conn.getURL()); 										// URLConnection의 URL을 반환한다.
			System.out.println("getUseCaches() : " + conn.getUseCaches()); 							// 캐쉬의 사용여부를 반환한다.
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
