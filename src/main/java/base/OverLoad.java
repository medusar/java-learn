package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by medusar on 04/12/2016.
 */
public class OverLoad {

    public static void method1(List<String> list) {
        System.out.println("method(List<String> list) called");
    }

    public static int method(List<Integer> list) {
        System.out.println("method(List<Integer> list) called");
        return 0;
    }

    //查看编译后结果
    //自动装箱和迭代器
    public static void Wrap() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    //条件编译
    public static void CompileConditional() {
        if (true) {
            System.out.println("compiled");
        } else {
            System.out.printf("will not be compiled");
        }
    }

    public static void CompileSwitch(String param) {
        switch (param) {
            case "hello":
                System.out.println("hello");
                return;
            default:
                System.out.println("hhhhl");
        }
    }

    public static void ComplileTryCatch() {
        try (FileInputStream f = new FileInputStream(new File("/opt/source"))) {
            System.out.println(f.available());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("hello", "world");


        method1(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }


    class MyInnerClass{
        private String name;

        public MyInnerClass(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
