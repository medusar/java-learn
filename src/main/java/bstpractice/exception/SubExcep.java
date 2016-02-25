package bstpractice.exception;

import java.io.IOException;
import java.net.SocketException;

/**
 * Date: 2016年2月21日 下午9:11:43 <br/>
 * 
 * @author medusar
 */
public class SubExcep extends SuperExcep {

	/**
	 * 由于父类抛出了checked Exception，子类必须显式实现一个构造器。
	 * 
	 * 但是子类抛出的异常可以是IOException，也可以是IOException的父类
	 * 
	 * @throws Exception
	 */
	public SubExcep() throws Exception {
		super();
	}

	/**
	 * 覆盖父类方法的时候，只能抛出父类异常相同类型异常或者父类异常的子类型异常
	 */
	@Override
	void doStaff() throws SocketException {

	}

}
