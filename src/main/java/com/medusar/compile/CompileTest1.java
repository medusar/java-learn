package com.medusar.compile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Date: 2016年2月2日 下午5:44:27 <br/>
 */
public class CompileTest1 {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		// 获取系统的java编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 指定要编译的java文件
		String fileToCompile = "E:/HelloWorld.java";
		// 执行编译方法
		int compilationResult = compiler.run(null, null, null, fileToCompile);
		// 返回0表示编译成功
		if (compilationResult == 0) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}

		Class<?> clazz = CompileTest1.class.getClassLoader().loadClass("HelloWorld");
		System.err.println(clazz);
	}
}
