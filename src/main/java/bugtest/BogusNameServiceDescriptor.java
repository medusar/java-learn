package bugtest;

import sun.net.spi.nameservice.*;

/**
 * Date: 2016年1月13日 上午10:16:26 <br/>
 * 
 * @author medusar
 */
public class BogusNameServiceDescriptor implements NameServiceDescriptor {
	public NameService createNameService() throws Exception {
		return new BogusNameService();
	}

	public String getProviderName() {
		return "Bogus";
	}

	public String getType() {
		return "dns";
	}
}
