package netty;

import io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import org.openjdk.jol.info.ClassLayout;

public class MpscArrayQueueDemo {
    public static void main(String[] args) {
        MpscArrayQueue queue = new MpscArrayQueue(64);
        System.out.println(ClassLayout.parseInstance(queue).toPrintable());
    }
}
