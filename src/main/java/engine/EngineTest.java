package engine;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

/**
 * Date: 2016年1月22日 上午11:39:01 <br/>
 * 
 * http://www.1java2c.com/CN/UseJavascriptingengineJDK16.htm
 */
public class EngineTest {

	public static void main(String[] args) throws Exception {
		ScriptEngineManager mgr = new ScriptEngineManager();
		List<ScriptEngineFactory> engines = mgr.getEngineFactories();
		for (ScriptEngineFactory engine : engines) {
			System.out.println(engine.getEngineName());
			for (String n : engine.getNames()) {
				System.out.println("Short name : " + n);
			}
		}
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String myJSCode = "function myFunction(){return (4+2);}myFunction();";
		System.out.println(engine.eval(myJSCode));
	}

}
