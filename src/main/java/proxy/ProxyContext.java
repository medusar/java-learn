package proxy;

/**
 * Date: 2016年2月19日 下午2:06:26 <br/>
 * 
 * @author medusar
 */
public class ProxyContext {
	private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

	static void put(Object proxy) {
		threadLocal.set(proxy);
	}

	public static Object getProxy() {
		return threadLocal.get();
	}

	static void remove() {
		threadLocal.remove();
	}
}
