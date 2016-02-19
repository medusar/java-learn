package proxy;

/**
 * Date: 2016年2月19日 下午1:58:33 <br/>
 * 
 * @author medusar
 */
public class SayHelloImpl implements SayHello {

	@Override
	public void sayHi() {
		this.sayHello();
	}

	@Override
	public void sayHello() {
		// do something..
	}

}
