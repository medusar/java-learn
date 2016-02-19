package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Date: 2016年2月19日 下午12:50:12 <br/>
 * 
 * @author medusar
 */
public class ProxyFactory {

	public static Object createProxy(Object obj) {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		return Proxy.newProxyInstance(contextClassLoader, obj.getClass().getInterfaces(), new WrappedInvocationHandler(obj));
	}

	static class WrappedInvocationHandler implements InvocationHandler {
		private Object realObj;

		public WrappedInvocationHandler(Object realObj) {
			super();
			this.realObj = realObj;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("invoking method:" + method.getName());
			Object result = method.invoke(realObj, args);
			System.out.println("invoking finished...");
			return result;
		}

	}
}
