package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Date: 2015年12月13日 下午3:59:31 <br/>
 * 
 * @author medusar
 */
public class NIOServer implements EchoServer {

	@Override
	public void bind(int port) {
		try {
			// 服务端绑定端口
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			ServerSocket serverSocket = serverSocketChannel.socket();
			serverSocket.bind(new InetSocketAddress(port));
			// 设置为非阻塞
			serverSocketChannel.configureBlocking(false);

			// 轮询器
			Selector selector = Selector.open();
			// 将channel以及关心的事件 注册到轮询器上 （accept为收到客户端连接）
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.err.println("ready:"+serverSocketChannel);
			
			
			ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
			ServerSocket serverSocket1 = serverSocketChannel1.socket();
			serverSocket1.bind(new InetSocketAddress(9998));
			// 设置为非阻塞
			serverSocketChannel1.configureBlocking(false);
			serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
			System.err.println("ready1:"+serverSocketChannel1);
			
			// 循环监听
			while (true) {
				try {
					// 获取可用的Channel(阻塞)
					selector.select();
				} catch (Exception e) {
					e.printStackTrace();
				}

				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {
					SelectionKey currentKey = iterator.next();
					iterator.remove();

					// 是否可以接收新的连接
					if (currentKey.isAcceptable()) {
						ServerSocketChannel serverChannel = (ServerSocketChannel) currentKey.channel();
						System.err.println("Acctptable:"+serverChannel);
						// 接收客户端连接
						SocketChannel clientChannel = serverChannel.accept();
						System.out.println("Accepted channel from client:" + clientChannel);

						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, ByteBuffer.allocate(100));
					}
					// 可读
					if (currentKey.isReadable()) {
						// 获取客户端channel
						SocketChannel clientChannel = (SocketChannel) currentKey.channel();
						ByteBuffer output = (ByteBuffer) currentKey.attachment();
						// 将客户端数据读取到缓冲区
						clientChannel.read(output);
					}
					// 可写
					if (currentKey.isWritable()) {
						SocketChannel clientChannel = (SocketChannel) currentKey.channel();
						ByteBuffer output = (ByteBuffer) currentKey.attachment();
						output.flip();
						// 将数据写入缓冲区
						clientChannel.write(output);
						output.compact();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
