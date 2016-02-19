package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Date: 2015年12月13日 下午3:23:07 <br/>
 * 
 * @author medusar
 */
public class BIOClient {
	public static void main(String[] args) throws Exception {
		// 向服务端创建连接
		Socket socket = new Socket("127.0.0.1", 9999);
		
		socket.connect(null);
		socket.connect(null, 0);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

		// 发送数据
		writer.println("Hello,i am client");
		writer.flush();

		// 等待服务端响应
		while (true) {
			String serverData = reader.readLine();
			if (serverData == null) {
				break;
			}
			System.out.println("client received:" + serverData);
		}

		socket.close();
	}
}
