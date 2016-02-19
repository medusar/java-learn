package server;

/**
 * Date: 2015年12月13日 下午3:09:21 <br/>
 * 
 * @author medusar
 */
public class ServerStartup {

	public static void main(String[] args) {
//		 EchoServer server = new BIOServer();
		// EchoServer server = new PooledBIOServer();
//		EchoServer server = new NIOServer();
		EchoServer server = new NettyServer();
		server.bind(9999);
	}

}
