package pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Date: 2016年1月8日 上午10:24:24 <br/>
 * 
 * @author medusar
 */
public class ThreadPoolExceptionTestV2 {
	private static Logger logger = Logger.getLogger("ThreadPoolExceptionTestV2");

	static class MyThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = Executors.defaultThreadFactory().newThread(r);
			thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					if (e != null) {
						e.printStackTrace();
					}
				}
			});
			return thread;
		}
	}

	static class MyThreadPool extends ThreadPoolExecutor {

		public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}

		@Override
		protected void afterExecute(Runnable r, Throwable t) {
			super.afterExecute(r, t);
			if (t != null) {
				t.printStackTrace();
			}
		}

		@Override
		protected void terminated() {
			super.terminated();
			System.out.println("terminated");
		}

	}

	public static void main(String[] args) throws InterruptedException {
		ThreadPoolExecutor executor = new MyThreadPool(1, 1, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
		// ExecutorService executor = new ThreadPoolExecutor(1, 1, 0L,
		// TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new
		// MyThreadFactory());
		for (int i = 0; i < 10; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1111);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("do....");
					// throw new
					// IllegalArgumentException("sfasdfasdffasdfasdf");
				}
			});
		}

		executor.shutdown();
//		executor.shutdownNow();

		boolean awaitTermination = executor.awaitTermination(30, TimeUnit.SECONDS);
		System.out.println("awaitTermination:" + awaitTermination);
	}
}
