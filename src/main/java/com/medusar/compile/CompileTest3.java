package com.medusar.compile;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * Date: 2016年2月4日 下午4:41:08 <br/>
 * 
 * @author medusar
 */
public class CompileTest3 {
	public static void main(String[] args) {
		String fileToCompile = "D:/eclipse_workspace/compile/src/main/java/com/medusar/compile/HelloWorld.java";

		// 获取JavaCompiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		// DiagnosticListener用于获取Diagnostic信息，Diagnostic信息包括：错误，警告和说明性信息
		// MyDiagnosticListener diagnosticListener = new MyDiagnosticListener();
		DiagnosticCollector diagnosticCollector = new DiagnosticCollector();

		// JavaFileManager:用于管理与工具有关的所有文件，这里的文件可以是内存数据，也可以是SOcket数据，而不仅仅是磁盘文件。
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, Locale.ENGLISH, Charset.forName("utf-8"));

		// JavaFileObjects: 是java源码文件(.java)和class文件(.class)的抽象
		Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(fileToCompile);

		// 编译任务
		CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, javaFileObjects);

		Boolean result = task.call();
		System.out.println(result);

		List list = diagnosticCollector.getDiagnostics();
		for (Object object : list) {
			Diagnostic d = (Diagnostic) object;
			System.out.println(d.getMessage(Locale.ENGLISH));
		}
	}
}
