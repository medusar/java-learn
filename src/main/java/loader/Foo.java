package loader;

/**
 * Date: 2016年2月21日 下午8:03:55 <br/>
 * 
 * @author medusar
 */
public class Foo {
	static {
		System.out.println("static block executing...");
	}
	
	final static String data = "data";
}
