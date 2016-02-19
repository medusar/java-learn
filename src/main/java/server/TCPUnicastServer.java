package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date: 2016年1月6日 下午1:16:57 <br/>
 * TCP服务端，监听端口，等待连接，然后将客户端的输入的内容原样返回给客户端
 */
public class TCPUnicastServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = null;
		try {
			// 服务端，绑定到端口9999
			serverSocket = new ServerSocket(9999);
			while (true) {
				final Socket clientSocket = serverSocket.accept();
				// 启动一个线程，处理客户端连接
				new Thread() {
					@Override
					public void run() {
						try {
							BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
							// 监听客户端的输入
							while (true) {
								String requestData = reader.readLine();
								if (requestData == null) {
									break;
								}

								System.out.println("server received:" + requestData);
								// 将客户端的输入原样返回给客户端
								writer.println(requestData);
								writer.flush();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
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
