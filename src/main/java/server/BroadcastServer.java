package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2016年1月6日 上午11:08:24 <br/>
 * 
 * @author medusar
 */
public class BroadcastServer {

	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket();

		// 发送的目的地址和端口
		InetAddress destination = InetAddress.getByName("255.255.255.255");
		for (int i = 0; i < 10; i++) {
			byte[] buf = "Hello client".getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, destination, 9999);
			serverSocket.send(packet);
			TimeUnit.SECONDS.sleep(5);
		}

		serverSocket.close();
	}
}
