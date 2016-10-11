package nettylearn;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Date: 2015年12月23日 上午10:16:07 <br/>
 * 
 * @author medusar
 */
public class LocalTest {

	// private static Logger logger = LoggerFactory.getLogger(LocalTest.class);

	private static Logger logger = Logger.getLogger(LocalTest.class);

	public void testLog() throws IOException {
		logger.info("ssssssssssssssssssssssssss");
		 Enumeration<URL> resources = LocalTest.class.getClassLoader().getResources("org/apache/log4j/Logger.class");
		 while (resources.hasMoreElements()) {
			URL url = (URL) resources.nextElement();
			System.out.println(url.toString());
		}
	}

	public void test() {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		TimeUnit timeUnit = TimeUnit.MILLISECONDS;
		long time = 1000 * 60 * 2;
		Date timeout = new Date(System.currentTimeMillis() + timeUnit.toMillis(time));
		lock.lock();
		boolean stillWaiting = true;
		try {
			while (stillWaiting) {
				System.out.println("still waiting");
				stillWaiting = condition.awaitUntil(timeout);
				System.out.println("result:" + stillWaiting);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void testWait() {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		TimeUnit timeUnit = TimeUnit.MILLISECONDS;
		long time = 1000 * 20;

		boolean stillWaiting = true;

		lock.lock();
		try {
			while (stillWaiting) {
				System.out.println("still waiting");
				stillWaiting = condition.await(time, timeUnit);
				System.out.println("result:" + stillWaiting);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void testClassLoader() {

		ClassLoader objectLoader = Object.class.getClassLoader();
		System.out.println(objectLoader);

		ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
		System.out.println(systemClassLoader);

		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(contextClassLoader);
	}

}
