  
package loader;  
/**   
 * Date:     2016年2月6日 下午6:16:27 <br/>  
 * @author   medusar       
 */
public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
//		System.out.println(ClassLoader.getSystemClassLoader());
		
		Class<?> forName = Class.forName("loader.Foo");
		
//		System.out.println(Foo.data);
		
//		Class.forName("loader.FooInterface");
		
//		new Foo();
	}
}
  
