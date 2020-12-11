package util.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new T(exchanger), "t1").start();
        new Thread(new T(exchanger), "t2").start();

        TimeUnit.SECONDS.sleep(10);
    }

    public static final class T implements Runnable {
        private final Exchanger<String> exchanger;

        public T(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    exchanger.exchange("hello");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                System.out.println("Thread:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
