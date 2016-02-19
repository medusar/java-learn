package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date: 2015年12月13日 下午3:09:06 <br/>
 * 
 * @author medusar
 */
public class BIOServer implements EchoServer {

	@Override
	public void bind(int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					new Thread(new BIOEchoHandler(clientSocket)).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
				}
			}
		}
	}

}