package essence.ch16;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 여러 개의 쓰레드를 생성해서 클라이언트의 요청을 동시에 처리한다.
 * 서버에 접속하는 클라이언트의 수가 많을 때는 쓰레드를 이용해서 클라이언트의 요청을 병렬적으로 처리하는 것이 좋다. 
 * 그렇지 않으면 서버가 접속을 욫어한 순서대로 처리하기 때문에 늦게 접속을 요청한 클라이언트는 오랜 시간을 기다릴 수 있다.
 */
public class TcpIpServer4 implements Runnable {

	ServerSocket serverSocket;
	Thread[] threadArr;

	public static void main(String[] args) {
		
		// 5개의 쓰레드를 생성하는 서버를 생성한다.
		TcpIpServer4 server = new TcpIpServer4(5);
		server.start();
	}
	
	public TcpIpServer4(int num) {
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println(getTime() + "서버가 준비되었습니다.");
			
			threadArr = new Thread[num];
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		for(int i=0; i<threadArr.length; i++) {
			threadArr[i] = new Thread(this);
			threadArr[i].start();
		}
	}
	
	public void run() {
		
		while(true) {
			try {
				System.out.println(getTime() + "연결요청을 기다립니다.");
				
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
		String name = Thread.currentThread().getName();
		SimpleDateFormat f = new SimpleDateFormat("[hh:ss:mm]");
		return f.format(new Date()) + name;
	}
}
