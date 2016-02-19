package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Date: 2016年1月4日 下午4:41:50 <br/>
 * 
 * @author medusar
 */
public class SpinLock {
	private AtomicReference<Thread> cas = new AtomicReference<Thread>();

	public void lock() {
		Thread current = Thread.currentThread();
		// 利用CAS
		while (!cas.compareAndSet(null, current)) {
			// DO nothing
		}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		cas.compareAndSet(current, null);
	}
}
