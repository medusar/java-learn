package server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel.Unsafe;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Date: 2015年12月13日 下午4:31:32 <br/>
 * 
 * @author medusar
 */
public class NettyServer implements EchoServer {

	@Override
	public void bind(int port) {
		// 定义EventLoopGroup
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			//启动
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup,workerGroup) //设置线程组
			.channel(NioServerSocketChannel.class) //创建的Channel类型，服务端采用NioServerSocketChannel
			.localAddress(port) //监听本地端口
			.childHandler(new ChannelInitializer<SocketChannel>() { //设置来自Channel请求的Handler

				//每次有一个新的Channel注册，都会执行这个方法，该方法返回后，该实例将被从ChannelPipeline中移除
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					//参数ch表示刚刚注册上来的channel
					
					//为新注册的channel添加处理器(handler)，可以添加多个
					//添加业务处理器NettyEchoHandler
					ch.pipeline().addLast(new NettyEchoHandler());
//					ch.pipeline().addLast(new NettyEchoHandler());
//					ch.pipeline().addLast(new NettyEchoHandler());
//					ch.pipeline().addLast(new NettyEchoHandler());
//					ch.pipeline().addLast(new NettyEchoHandler());
//					ch.pipeline().addLast(new NettyEchoHandler());
//					ch.pipeline().addLast(new NettyEchoHandler());
				}
			});
			
			// 阻塞直到绑定成功
			ChannelFuture future = bootstrap.bind().sync();
			
			//到此为止，服务端绑定成功
			System.out.println("Server bootstrap succefully,binding to port:" + port);
			
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				bossGroup.shutdownGracefully().sync();
//				workerGroup.shutdownGracefully().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
