package xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 * Date: 2016年2月26日 上午10:57:44 <br/>
 * 
 * @author wanglianbin
 */
public class JaxbUtil {
	private static final String CDATA_END = "]]>";
	private static final String CDATA_BEGIN = "<![CDATA[";

	public static String toXml(Object obj) throws XMLStreamException {
		try {
			JAXBContext jaxbContex = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = jaxbContex.createMarshaller();
			// 是否格式化生成的xml串
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 编码格式
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			// 是否省略xml头声明信息
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);

			// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter writer = new StringWriter();

			/**
			 * 通过动态代理修改写xml方法，以便于处理CDATA
			 */
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter streamWriter = xof.createXMLStreamWriter(writer);
			
			marshaller.marshal(obj, streamWriter);

			return writer.toString();

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	private static XMLStreamWriter wrapWriter(XMLStreamWriter writer) {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		return (XMLStreamWriter) Proxy.newProxyInstance(contextClassLoader, writer.getClass().getInterfaces(), new WriteHandler(writer));
	}

	// private static XMLStreamReader wrapReader(XMLStreamReader reader) {
	// ClassLoader contextClassLoader =
	// Thread.currentThread().getContextClassLoader();
	// return (XMLStreamReader) Proxy.newProxyInstance(contextClassLoader,
	// reader.getClass().getInterfaces(), new ReaderHandler(reader));
	// }

	public static <T> T toObject(String xml, Class<T> clazz) throws JAXBException, XMLStreamException {
		JAXBContext jaxbContex = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jaxbContex.createUnmarshaller();
		// XMLInputFactory xif = XMLInputFactory.newInstance();
		StringReader reader = new StringReader(xml);
		// XMLStreamReader xmlReader =
		// wrapReader(xif.createXMLStreamReader(reader));
		return (T) unmarshaller.unmarshal(reader);
	}

	// static class ReaderHandler implements InvocationHandler {
	// private XMLStreamReader reader;
	//
	// public ReaderHandler(XMLStreamReader reader) {
	// super();
	// this.reader = reader;
	// }
	//
	// @Override
	// public Object invoke(Object proxy, Method method, Object[] args) throws
	// Throwable {
	// Object result = null;
	// if (method.getName().equals("getTextCharacters")) {
	// char[] data = reader.getTextCharacters();
	// String text = new String(data);
	// System.out.println("text:" + text);
	// if (text.startsWith(CDATA_BEGIN) && text.endsWith(CDATA_END)) {
	// int start = text.indexOf(CDATA_BEGIN);
	// int end = text.indexOf(CDATA_END);
	// result = text.substring(start, end).toCharArray();
	// } else {
	// result = data;
	// }
	// } else {
	// result = method.invoke(reader, args);
	// }
	// return result;
	// }
	// }

	/**
	 * 动态代理处理xml写，以便于解决CDATA问题。
	 * 参考资料：
	 * http://blog.mi-ernst.de/2012/05/04/jaxb-and-cdata-sections/
	 * https://dzone.com/articles/java-and-xml-part-3-jaxb
	 * 
	 *  对以上博客讲解内容作了修改，改成了动态代理的实现
	 *
	 */
	static class WriteHandler implements InvocationHandler {
		private XMLStreamWriter realObj;

		public WriteHandler(XMLStreamWriter realObj) {
			super();
			this.realObj = realObj;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			if (method.getName().equals("writeCharacters") && args != null && args.length == 1) {
				String text = (String) args[0];
				if (text.startsWith(CDATA_BEGIN) && text.endsWith(CDATA_END)) {
					text = text.substring(CDATA_BEGIN.length(), text.length() - CDATA_END.length());
					realObj.writeCData(text);
				} else {
					result = method.invoke(realObj, args);
				}
			} else {
				result = method.invoke(realObj, args);
			}
			return result;
		}
	}
}
