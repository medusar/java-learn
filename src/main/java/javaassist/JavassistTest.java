package javaassist;

import com.medusar.compile.Hello;
import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Date: 2016年2月5日 下午3:30:54 <br/>
 * <p>
 * javassist文档：
 * https://jboss-javassist.github.io/javassist/tutorial/tutorial.html
 */
public class JavassistTest {

    public void testNewInstance() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.medusar.compile.Hello");
        Class clzz = ctClass.toClass();
        Object obj = clzz.newInstance();
        Hello hello = (Hello) obj;
        hello.print();
    }

    public void testSetSuperClass() throws Exception, Throwable {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get("com.medusar.compile.Hello");
        ctClass.setSuperclass(pool.get("com.medusar.compile.Hello1"));
        Class clz = ctClass.toClass();
        Class superclass = clz.getSuperclass();
        System.out.println(superclass.getCanonicalName());
    }

    public void testCreateClass() throws CannotCompileException, NotFoundException, SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        // 创建类
        CtClass clzz = pool.makeClass("com.medusar.compile.CreatedClass");
        // 为创建的类添加方法
        clzz.addMethod(CtNewMethod.make("public void make(){System.out.print(\"hello\");}", clzz));
        System.out.println(clzz.getName());

        Class class1 = clzz.toClass();
        Method method = class1.getMethod("make");
        method.invoke(class1.newInstance());

        // makeClass 用于创建类
        // makeInterface用于创建接口
        // 接口中的方法可以通过CtNewMethod的abstractMethod方法创建
    }

}
