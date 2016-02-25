package xml;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.SAXReader;

/**
 * Date: 2016年2月23日 下午7:18:45 <br/>
 * 
 * @author medusar
 */
public class XmlReader {
	public static void main(String[] args) throws DocumentException {
		InputStream stream = XmlReader.class.getResourceAsStream("data");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(stream);
		System.out.println("doc:" + doc);
		if (doc.hasContent()) {
			Element root = doc.getRootElement();
			System.out.println("root:" + root);
			// Node code = root.selectSingleNode("/project/modelVersion");
			// System.out.println(code);

			// String text = code.getText();
			// System.out.println(new
			// String(text.getBytes(Charset.forName("utf-8"))));

			List list = root.selectNodes("dependencies/dependency");
			if (list != null && !list.isEmpty()) {
				for (Object object : list) {
					Node data = (Node) object;
					data.accept(new Visitor() {

						@Override
						public void visit(Text node) {
							// System.out.println("text-node:" +
							// node.getText());
						}

						@Override
						public void visit(ProcessingInstruction node) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}

						@Override
						public void visit(Namespace namespace) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}

						@Override
						public void visit(Entity node) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
							System.out.println(node.getName() + ":" + node.getText());
						}

						@Override
						public void visit(Comment node) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}

						@Override
						public void visit(CDATA node) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}

						@Override
						public void visit(Attribute node) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}

						@Override
						public void visit(Element node) {
							// System.out.println(new
							// Throwable().getStackTrace()[0].getLineNumber() +
							// ",to do");
							try {
								System.out.println(node.getName() + "-->" + new String(node.getText().getBytes("utf-8")));
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}

						@Override
						public void visit(DocumentType documentType) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}

						@Override
						public void visit(Document document) {
							System.out.println(new Throwable().getStackTrace()[0].getLineNumber() + ",to do");
						}
					});
				}
			}

		}
	}
}
