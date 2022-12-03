package dom_WUWIOS;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyWUWIOS {

	public static void main(String[] args) {
		
		try {
			File inputFile = new File("carsWUWIOS.xml");
			
			DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docfactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse(inputFile);
			
			Node cars = doc.getFirstChild();
			
			Node supercar = doc.getElementsByTagName("supercars").item(0);
			
			NamedNodeMap attr = supercar.getAttributes();
			Node nodeAttr = attr.getNamedItem("company");
			nodeAttr.setTextContent("Lamborghini");
			
			NodeList list = supercar.getChildNodes();
			
			for (int i = 0; i < list.getLength(); i++) {
				
				Node node = list.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					
					if("carname".equals(element.getNodeName()))
					{
						if("Ferrari 01".equals(element.getTextContent()))
						{
							element.setTextContent("Lamborghini 01");
						}
						if("Ferrari 02".equals(element.getTextContent()))
						{
							element.setTextContent("Lamborghini 02");
						}
					}
				}
			}
				
			NodeList childNodes = cars.getChildNodes();
			for (int i = 0; i <childNodes.getLength() ; i++) {
					
				Node node = childNodes.item(i);
					
				if("luxurycars".equals(node.getNodeName()))			
				{
					cars.removeChild(node);
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transf = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			System.out.println("Modosotott fájl: ");
			StreamResult consoleResult = new StreamResult(System.out);
			transf.transform(source,consoleResult);
		} catch (Exception e) {
			
		}

	} //end main

} //end class
