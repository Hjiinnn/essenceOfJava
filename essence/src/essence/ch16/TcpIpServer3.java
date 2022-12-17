package essence.ch16;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpIpServer3 {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다.");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				System.out.println(getTime() + "연결요청을 기다립니다.");
				
				// 요청 대기 시간을 5초로 설정한다.
				// 5초동안 접속이 없으면 SocketTimeoutException이 발생한다.
					serverSocket.setSoTimeout(5*1000);
				Socket socket = serverSocket.accept();
				System.out.println(getTime() + socket.getInetAddress() + "로부터 연결요청이 들어왔습니다.");
				
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] Test Message1 from Server");
				System.out.println(getTime() + "데이터를 전송하였습니다.");
				
				dos.close();
				socket.close();
			} catch(SocketTimeoutException e) {
				 System.out.println("지정된 시간동안 접속요청이 없어서 서버를 종료합니다.");
				 System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:ss:mm]");
		return f.format(new Date());
	}
}
