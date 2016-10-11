package javaassist;

import com.medusar.compile.string.Function;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewMethod;

/**
 * Date: 2016年2月6日 上午10:48:19 <br/>
 * 
 * @author medusar
 */
public class JavassistCompiler {

	/**
	 * 根据类名和类源码编译java类（简单版本，不考虑继承类这些情况）
	 * 
	 * @param classFullName
	 * @param sourceCode
	 * @return
	 * @throws Exception
	 */
	public Class<?> compile(String classFullName, String sourceCode) throws Exception {
		ClassPool pool = new ClassPool(true);
		// 创建类
		CtClass clazz = pool.makeClass(classFullName);
		// 增加方法，这里需要提取出源码中的方法，然后逐个调用clazz.addMethod(m);方法
		//为了演示功能，这里做了简单处理
		clazz.addMethod(CtNewMethod.make(getMethodSource(sourceCode), clazz));
		return clazz.toClass();
	}

	private String getMethodSource(String sourceCode) {
		//TODO:
		return null;
	}

	public void testCompile() throws Exception {
		String functionImplSourceCode = "package org.medusar;\n"
				+"import com.medusar.compile.string.Function;\n"
				+"public class AddFunction implements Function {\n"
				+"@Override\n"
				+"public int calculate(int x, int y) {\n"
				+"return x + y;\n"
				+"}"
				+"}";
		
		Class<?> clazz = new JavassistCompiler().compile("AddFunction", functionImplSourceCode);
		Function func = (Function)clazz.newInstance();
		int result = func.calculate(1, 23);
		System.out.println(result);
	}

}
