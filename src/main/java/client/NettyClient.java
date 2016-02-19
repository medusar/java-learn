package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Date: 2015年12月18日 上午10:43:03 <br/>
 * 
 * @author medusar
 */
public class NettyClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress("www.baidu.com", 9999))
			.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							 ch.pipeline().addLast(new NettyClientEchoHandler());
						}
					});
			 ChannelFuture f = b.connect().sync();
			 f.channel().closeFuture().sync();
		 } finally {
			 group.shutdownGracefully().sync();
		 }
	}
}
