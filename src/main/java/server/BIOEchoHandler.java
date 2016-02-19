package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Date: 2015年12月13日 下午3:19:13 <br/>
 * 
 * @author medusar
 */
public class BIOEchoHandler implements Runnable {
	private Socket socket;

	public BIOEchoHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			System.out.println("begin");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			while (true) {
				String requestData = reader.readLine();
				if (requestData == null) {
					break;
				}

				System.out.println("server received:" + requestData);

				writer.println(requestData);
				writer.flush();
			}
			System.out.println("server end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
