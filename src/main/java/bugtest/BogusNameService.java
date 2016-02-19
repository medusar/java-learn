package bugtest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import sun.net.spi.nameservice.*;

/**
 * Date: 2016年1月13日 上午10:15:09 <br/>
 * 
 * @author medusar
 */
public class BogusNameService implements NameService {

	public String getHostByAddr(byte[] arg0) throws UnknownHostException {
		System.out.println("excep");
		throw new IllegalStateException("bogus");
	}

	public InetAddress[] lookupAllHostAddr(String arg0) throws UnknownHostException {
		System.out.println("excep");
		throw new IllegalStateException("bogus");
	}
}
