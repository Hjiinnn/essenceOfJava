package essence.ch16;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {

	public void start() throws IOException {
		// 포트 7777번을 사용하는 소켓을 생성한다.
		DatagramSocket socket = new DatagramSocket(7777);
		DatagramPacket inPacket;
		DatagramPacket outPacket;
		
		byte[] inMsg = new byte[10];
		byte[] outMsg;
		
		while(true) {
			// 데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			// 패킷을 통해 데이터를 수신한다.
			socket.receive(inPacket);
			
			// 수신한 패킷으로부터 client의 IP주소와 Port를 얻는다.
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			// 서버의 현재시간을 시분초 형태로 반환한다.
			SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
			String time = f.format(new Date());
			
			outMsg = time.getBytes();
			
			// 패킷을 생성해서 client에게 전송한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
			socket.send(outPacket);
		}
	}
	
	public static void main(String[] args) {
		try {
			new UdpServer().start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
