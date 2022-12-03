package dom_WUWIOS;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomRead_WUWIOS {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		File xmlFile = new File("users_WUWIOS.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("user");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent ELement: " + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				String uid = elem.getAttribute("id");
				
				Node nodel = elem.getElementsByTagName("firstname").item(0);
				String fname = nodel.getTextContent();
				
				Node nodel2 = elem.getElementsByTagName("lastname").item(0);
				String lname = nodel2.getTextContent();
				
				Node nodel3 = elem.getElementsByTagName("profession").item(0);
				String pname = nodel3.getTextContent();
				
				System.out.println("User id: " + uid);
				System.out.println("Firstname: " + fname);
				System.out.println("Lastname: " + lname);
				System.out.println("Profession: " + pname);
			} //end if
			
		} //end for

	} //end main

} //end class
