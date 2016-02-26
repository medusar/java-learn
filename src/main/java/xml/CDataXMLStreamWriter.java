package xml;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Date: 2016年2月26日 上午11:14:28 <br/>
 * 
 * @author wanglianbin
 */
public class CDataXMLStreamWriter extends DelegatingXMLStreamWriter {
	private static final String CDATA_END = "]]>";
	private static final String CDATA_BEGIN = "<![CDATA[";

	public CDataXMLStreamWriter(XMLStreamWriter del) {
		super(del);
	}

	@Override
	public void writeCharacters(String text) throws XMLStreamException {
		boolean useCData = false;
		if (text.startsWith(CDATA_BEGIN) && text.endsWith(CDATA_END)) {
			text = text.substring(CDATA_BEGIN.length(), text.length() - CDATA_END.length());
			useCData = true;
		}
		if (useCData) {
			super.writeCData(text);
		} else {
			super.writeCharacters(text);
		}
	}
}
