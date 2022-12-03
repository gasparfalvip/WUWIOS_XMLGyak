package dom_WUWIOS;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DomWrite_WUWIOS {

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("DOM_WUWIOS", "users");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Gasparfalvi", "Péter", "C"));
		root.appendChild(createUser(doc, "2", "Piroska", "Feher", "Java programozo"));
		root.appendChild(createUser(doc, "3", "Ferenc", "Puskas", "Manager"));
		
		TransformerFactory transformerFactroy = TransformerFactory.newInstance();
		Transformer transf = transformerFactroy.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File("users1_WUWIOS.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
		
	} //end main
	
	private static Node createUser(Document doc, String id, String firstName, String lastName, String profession) {
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstName));
		user.appendChild(createUserElement(doc, "lastname", lastName));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	} //end createUser
	
	private static Node createUserElement (Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	} //end createUserElement

} //end class
