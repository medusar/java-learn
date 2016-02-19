package com.medusar.compile.string;

import java.util.Arrays;

/**
 * Date: 2016年2月5日 下午1:25:50 <br/>
 * 
 * @author medusar
 */
public class Main {

	public static void main(String[] args) throws Exception {
		//自定义了一个AddFunction，我们把它定义在包'org.medusar'中
		String functionImplSourceCode = "package org.medusar;\n"
				+"import com.medusar.compile.string.Function;\n"
				+"public class AddFunction implements Function {\n"
				+"@Override\n"
				+"public int calculate(int x, int y) {\n"
				+"return x + y;\n"
				+"}"
				+"}";
		//创建编译器实例
		StringSourceCompiler compiler = new StringSourceCompiler(Main.class.getClassLoader(), Arrays.asList("-target", "1.6"));
		//编译代码并得到对应的Class实例
		Class<?> clazz = compiler.compile("org.medusar.AddFunction", functionImplSourceCode);
		//创建实例
		Object instance = clazz.newInstance();
		Function fun = (Function)instance;
		//执行代码
		int value = fun.calculate(3, 2);
		//输出结果
		System.out.println(value);
	}

}
