package bugtest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Date: 2016年1月13日 上午10:18:08 <br/>
 * 
 * @author medusar
 */
public class BogusTest {
	private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 20; i++) {
			newFixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						// First attempt - IllegalStateException catched below
						InetAddress firstAttempt = InetAddress.getByName("host.company.com");
					} catch (RuntimeException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						// 2nd attempt
						InetAddress secondAttempt = InetAddress.getByName("host.company.com");
						// Stuck here forever
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("done");
				}
			});
		}

		newFixedThreadPool.shutdown();

	}
}
