package loader;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ClassLoaderTest {


    public void testClassLoader() {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class, ClassLoader.getSystemClassLoader().getParent());
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("system loader:" + ClassLoader.getSystemClassLoader());
        System.out.println("system loader's parent:" + ClassLoader.getSystemClassLoader().getParent());
    }

    public void testClassLoader1() {
        Thread.currentThread().setContextClassLoader(ClassLoader.getSystemClassLoader().getParent());
        ServiceLoader<Driver> loader1 = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator2 = loader1.iterator();
        while (iterator2.hasNext()) {
            Driver driver = (Driver) iterator2.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("current classLoader:" + Thread.currentThread().getContextClassLoader());
    }

    public void testDrivemanage() {
        Enumeration<Driver> enumration = DriverManager.getDrivers();
        while (enumration.hasMoreElements()) {
            Driver driver = (Driver) enumration.nextElement();
            System.out.println(driver.getClass().getClassLoader());
        }
    }

}
