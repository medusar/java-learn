package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Date: 2016年1月6日 上午11:40:22 <br/>
 * 
 * @author medusar
 */
public class BroadcastClient {

	public static void main(String[] args) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket(9999);
		while (true) {
			DatagramPacket packet = new DatagramPacket(new byte[256], 256);
			clientSocket.receive(packet);
			System.out.println(new String(packet.getData()).trim());
		}
	}

}
