package xpathwuwios;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.xpath.XPathException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathWUWIOS {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("studentWUWIOS.xml");
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
			Document document = documentBuilder.parse("studentWUWIOS.xml");
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();

			//String expr = "class/student";
			//String expr = "class/student[@id=2]";
			//String expr = "//student";
			//String expr = "class/student[position()=2]";
			//String expr = "class/student[last()]";
			//String expr = "class/student[last()-1]";
			//String expr = "class/student[position()<3]";
			//String expr = "class/*";
			//String expr = "class/student[*]";
			//String expr = "*";
			//String expr = "class/student[kor > 20]";		
			
			String expr = "class/student/keresztnev | class/student/vezeteknev";
			
			NodeList nodeList = (NodeList) xPath.compile(expr).evaluate(document, XPathConstants.NODESET);
			
			for(int i = 0; i < nodeList.getLength();i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: "+ node.getNodeName());
				
				if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("Hallgató ID: "+ element.getAttribute("id"));
					
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				}
			}
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}













