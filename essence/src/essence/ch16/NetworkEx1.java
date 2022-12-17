package essence.ch16;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

class NetworkEx1 {

	public static void main(String[] args) {
		
		InetAddress ip = null;
		InetAddress[] ipArr = null;
	
		try {
			ip = InetAddress.getByName("www.naver.com");	// getByName() : 도메인명(host)을 통해 IP주소를 얻는다.

			System.out.println("toString() : " + ip.toString());
			System.out.println("getHostName() : " + ip.getHostName());			// getHostName()    : 호스트의 이름을 반환한다.
			System.out.println("getHostAddress() : " + ip.getHostAddress());	// getHostAddress() : 호스트의 IP주소를 반환한다.

			byte[] ipAddr = ip.getAddress();	// getAddress() : IP주소를 byte배열로 반환한다.
			System.out.println("getAddress() : " + Arrays.toString(ipAddr));
			
			String result = "";
			for(int i=0; i<ipAddr.length; i++) {
				result += (ipAddr[i] < 0) ? ipAddr[i] + 256 : ipAddr[i];
				result += ".";
			}
			System.out.println("getAddress() + 256 : " + result);
			System.out.println();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
		
		try {
			ip = InetAddress.getLocalHost();	// getLocalHost() : 지역호스트의 IP주소를 반환한다.
			System.out.println("getHostName() : " + ip.getHostName());
			System.out.println("getHostAddress() : " + ip.getHostAddress());
			System.out.println();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
		
		try {
			ipArr = InetAddress.getAllByName("www.naver.com");	// getAllByName() : 도메인명(host)에 지정된 모든 호스트의 IP주소를 배열에 담아 반환한다.

			for(int i=0; i<ipArr.length; i++) {
				System.out.println("ipArr[" + i + "] : " + ipArr[i]);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
	}

}
