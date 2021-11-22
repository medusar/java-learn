package jol;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Contended;

public class ObjectLayoutDemo {

    //JVM参数：-XX:+UseCompressedOops -XX:+UseCompressedClassPointers  -XX:-RestrictContended
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new MyObj()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new CacheLine()).toPrintable());
    }

    @Contended
    private static class MyObj {
        private Object obj;

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }
    }

    private static class CacheLine {
        private long value;
        private long v1, v2, v3, v4, v5, v6, v7;
    }
}
