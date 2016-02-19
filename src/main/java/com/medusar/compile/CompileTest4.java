package com.medusar.compile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

/**
 * Date: 2016年2月4日 下午4:49:16 <br/>
 * 
 * @author medusar
 */
public class CompileTest4 {

	public static void main(String[] args) throws URISyntaxException {
		// 获取JavaCompiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		// DiagnosticListener用于获取Diagnostic信息，Diagnostic信息包括：错误，警告和说明性信息
		// MyDiagnosticListener diagnosticListener = new MyDiagnosticListener();
		DiagnosticCollector diagnosticCollector = new DiagnosticCollector();

		// JavaFileManager:用于管理与工具有关的所有文件，这里的文件可以是内存数据，也可以是SOcket数据，而不仅仅是磁盘文件。
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, Locale.ENGLISH, Charset.forName("utf-8"));

		/**
		 * 组装java源码并返回JavaFileObject
		 */
		StringBuilder javaFileContents = new StringBuilder("" + "class TestClass{" + "   public void testMethod(){" + "       System.out.println("
				+ "\"test\"" + ");" + "}" + "}");

		JavaObjectFromString javaSourceObject = new JavaObjectFromString("TestClass", javaFileContents.toString());

		// JavaFileObjects: 是java源码文件(.java)和class文件(.class)的抽象
		Iterable<? extends JavaFileObject> javaFileObjects = Arrays.asList(javaSourceObject);

		// 编译任务
		CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, javaFileObjects);

		Boolean result = task.call();

		/**
		 * 输出结果
		 */
		System.out.println(result);
		List list = diagnosticCollector.getDiagnostics();
		for (Object object : list) {
			Diagnostic d = (Diagnostic) object;
			System.out.println(d.getMessage(Locale.ENGLISH));
		}
	}

	static class JavaObjectFromString extends SimpleJavaFileObject {
		// 存放java源码内容
		private String contents;

		public JavaObjectFromString(String className, String contents) throws URISyntaxException {
			super(new URI(className), Kind.SOURCE);
			this.contents = contents;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			return contents;
		}

	}
}
