package exception;

/**
 * Difference between NoClassDefFoundError and ClassNotFoundException
 * https://stackoverflow.com/questions/34413/why-am-i-getting-a-noclassdeffounderror-in-java
 * 一般，当静态代码块初始化失败后，再次访问这个类就会报NoClassDefFoundError
 */
public class NoClassDefFoundErrorDemo {

    public static class Demo {
        //throw exception during static field initialization.
        private static final int value = 1 / 0;

        public static int loadValue() {
            return value;
        }
    }

    public static void main(String[] args) {

        //ExceptionInInitializerError here
        try {
            //        System.out.println(Demo.loadValue());
            Demo demo = new Demo();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //NoClassDefFoundError here
//        Demo demo1 = new Demo();

    }

}
