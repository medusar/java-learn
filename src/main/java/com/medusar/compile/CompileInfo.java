package com.medusar.compile;

import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Date: 2016年2月2日 下午6:43:46 <br/>
 */
public class CompileInfo {
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.class.path"));

		args = new String[] { "-cp" };
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		System.out.println("Supported source versions:");
		Set<SourceVersion> srcVer = compiler.getSourceVersions();
		for (SourceVersion sv : srcVer)
			System.out.println("  " + sv.name());

		int nargs = compiler.isSupportedOption(args[0]);
		if (nargs == -1)
			System.out.println("Option " + args[0] + " is not supported");
		else
			System.out.println("Option " + args[0] + " takes " + nargs + " arguments");
	}
}
