package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Date: 2016年1月5日 下午7:11:08 <br/>
 * 
 * @author medusar
 */
public class MulticastClient {

	public static void main(String[] args) throws IOException {
		// 多播客户端监听相应的端口
		MulticastSocket clientSocket = new MulticastSocket(9999);

		// 客户端将自己加入到指定的多播组中，这样就能收到来自这个组的消息
		InetAddress group = InetAddress.getByName("224.0.0.3");
		clientSocket.joinGroup(group);

		byte[] buf = new byte[256];
		while (true) {
			// 读取数据
			DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
			clientSocket.receive(msgPacket);

			String msg = new String(msgPacket.getData());
			System.out.println("Socket 1 received msg: " + msg.trim());
		}
	}
}
