package bstpractice.exception;

import java.io.IOException;

/**
 * Date: 2016年2月21日 下午9:11:08 <br/>
 * 一二三四五一二三四五一二三四五一二三四五一二三四五
 * @author medusar
 */
public abstract class SuperExcep {

	public SuperExcep() throws IOException {
	}

	public SuperExcep(int data) {
		if (data < 0) {
			throw new IllegalArgumentException();
		}
	}

	abstract void doStaff() throws IOException;
}
