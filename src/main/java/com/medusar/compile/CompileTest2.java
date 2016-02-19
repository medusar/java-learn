package com.medusar.compile;

import java.nio.charset.Charset;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * @author medusar
 */
public class CompileTest2 {
	public static void main(String[] args) {
		String fileToCompile = "D:/eclipse_workspace/compile/src/main/java/com/medusar/compile/HelloWorld.java";

		// 获取JavaCompiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		// DiagnosticListener用于获取Diagnostic信息，Diagnostic信息包括：错误，警告和说明性信息
		MyDiagnosticListener diagnosticListener = new MyDiagnosticListener();

		// JavaFileManager:用于管理与工具有关的所有文件，这里的文件可以是内存数据，也可以是SOcket数据，而不仅仅是磁盘文件。
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticListener, Locale.ENGLISH, Charset.forName("utf-8"));

		// JavaFileObjects: 是java源码文件(.java)和class文件(.class)的抽象
		Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(fileToCompile);

		// 编译任务
		CompilationTask task = compiler.getTask(null, fileManager, diagnosticListener, null, null, javaFileObjects);

		Boolean result = task.call();
		System.out.println(result);

	}

	/**
	 * 诊断信息监听器
	 */
	static class MyDiagnosticListener implements DiagnosticListener {
		@Override
		public void report(Diagnostic diagnostic) {
			System.out.println("Code->" + diagnostic.getCode());
			System.out.println("Column Number->" + diagnostic.getColumnNumber());
			System.out.println("End Position->" + diagnostic.getEndPosition());
			System.out.println("Kind->" + diagnostic.getKind());
			System.out.println("Line Number->" + diagnostic.getLineNumber());
			System.out.println("Message->" + diagnostic.getMessage(Locale.ENGLISH));
			System.out.println("Position->" + diagnostic.getPosition());
			System.out.println("Source" + diagnostic.getSource());
			System.out.println("Start Position->" + diagnostic.getStartPosition());
			System.out.println("\n");
		}
	}
}
