package pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2016年1月5日 下午4:32:01 <br/>
 * 
 * @author medusar
 */
public class ThreadPoolExceptionTest {
	// static ExecutorService service = Executors.newFixedThreadPool(3);

	static ThreadPoolExecutor service = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			service.submit(new Runnable() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getId() + "exc");
					throw new IllegalStateException("exc");
				}
			});
		}

		service.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("run success...，id:" + Thread.currentThread().getId());
			}
		});

		TimeUnit.SECONDS.sleep(10000);
	}
}
