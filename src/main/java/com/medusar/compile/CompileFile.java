package com.medusar.compile;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Date: 2016年2月2日 下午6:42:46 <br/>
 */
public class CompileFile {
	public static void main(String[] args) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> dc;
		dc = new DiagnosticCollector<JavaFileObject>();

		StandardJavaFileManager sjfm;
		sjfm = compiler.getStandardFileManager(dc, null, null);

		Iterable<? extends JavaFileObject> fileObjects;
		fileObjects = sjfm.getJavaFileObjects(args);

		compiler.getTask(null, sjfm, dc, null, null, fileObjects).call();

		for (Diagnostic d : dc.getDiagnostics()) {
			System.out.println(d.getMessage(null));
			System.out.printf("Line number = %d\n", d.getLineNumber());
			System.out.printf("File = %s\n", d.getSource());
		}
	}
}
