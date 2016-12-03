package str;

/**
 * Created by lianbin.wang on 03/12/2016.
 */
public class StringTest {

    public static void main(String[] args) {
        //Random r = new Random();
        testString("5");
        testStringBuilder("5");

    }

    public static void testString(String rnd) {
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
//            String str1 = "This";
//            String str2 = " is ";
//            String str3 = " hello ";
//            String str4 = " world ";

            String result = "this" + "is" + "hello" + "world" + rnd;
        }
        System.out.println(System.currentTimeMillis() - t1); // 打印3
    }

    public static void testStringBuilder(String rnd) {

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
//            String str1 = "This";
//            String str2 = " is ";
//            String str3 = " hello ";
//            String str4 = " world ";
            StringBuilder sb = new StringBuilder();
            sb.append("this").append("is").append("hello").append("world").append(rnd);
            sb.toString();
        }
        System.out.println(System.currentTimeMillis() - t1); // 打印32
    }

}
