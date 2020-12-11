package util.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        new Thread(() -> {
            while (true) {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                semaphore2.release(1);
                //                System.out.println("release 2");
            }
        }, "T1").start();

        new Thread(() -> {
            while (true) {
                try {
                    semaphore2.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                semaphore3.release(1);
                //                System.out.println("release 1");
            }
        }, "T2").start();

        new Thread(() -> {
            while (true) {
                try {
                    semaphore3.acquire();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                semaphore1.release(1);
                //                System.out.println("release 1");
            }
        }, "T3").start();

        //trigger
        semaphore1.release(1);

        TimeUnit.SECONDS.sleep(10);
    }
}
