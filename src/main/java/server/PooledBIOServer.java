package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2015年12月13日 下午3:47:06 <br/>
 * 
 * @author medusar
 */
public class PooledBIOServer implements EchoServer {

	private ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

	@Override
	public void bind(int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();

					// new Thread(new BIOEchoHandler(clientSocket)).start();
					poolExecutor.execute(new BIOEchoHandler(clientSocket));
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
