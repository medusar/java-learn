package proxy;

/**
 * Date: 2016年2月19日 下午1:00:49 <br/>
 * 
 * @author medusar
 */
public class Main {

	public static void main(String[] args) {

		SayHello hello = (SayHello) ProxyFactory.createProxy(new SayHello() {

			@Override
			public void sayHi() {
				System.out.println("hi");
			}

			@Override
			public void sayHello() {
				this.sayHi();
				System.out.println("hello");
			}
		});

		hello.sayHello();
	}

}
