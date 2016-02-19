package com.medusar.compile;

import java.util.ServiceLoader;

import javax.tools.JavaCompiler;

/**
 * Date: 2016年2月2日 下午6:39:10 <br/>
 */
public class EnumAlternateJavaCompilers {

	public static void main(String[] args) {
		ServiceLoader<JavaCompiler> compilers;
		compilers = ServiceLoader.load(JavaCompiler.class);
		System.out.println(compilers.toString());

		for (JavaCompiler compiler : compilers) {
			System.out.println(compiler);
		}
	}
}
