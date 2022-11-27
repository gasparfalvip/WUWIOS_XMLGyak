package hu.domparse.wuwios;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DOMQuerywuwios {

    public static void main(String[] args) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
            LocalDate most = LocalDate.now();
            NodeList nodeList;
            NodeList nodeList2;

                DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
                DocumentBuilder builder=factory.newDocumentBuilder();
                //Fájl beolvasása
                Document document=builder.parse(new File("XMLWUWIOS.xml"));
                document.getDocumentElement().normalize();

                //20 évnél idősebb kuncsaftok adatainak kiirása
                //Aktuális elem meghatározása
                nodeList=document.getElementsByTagName("kuncsaft");

                for(int i =0;i<nodeList.getLength();i++){
                    Node node= nodeList.item(i);

                    System.out.println("20évnél idősebb kuncsaftok kiírása: \n~~~~~~~~");
                    if(node.getNodeType()==Node.ELEMENT_NODE){
                        Element element=(Element) node;
                        String szuld= element.getElementsByTagName("szuld").item(0).getTextContent();

                        if(Period.between(LocalDate.parse(szuld,formatter), most).getYears()>20)
                        {
                            System.out.println("\nAktuális elem: " + node.getNodeName());
                            System.out.println("~~~~~~~~");
                            System.out.println("Neve: " + element.getElementsByTagName("nev").item(0).getTextContent());
                            System.out.println("Születési dátuma: " + element.getElementsByTagName("szuld").item(0).getTextContent());
                            System.out.println("Életkora: "+ Period.between(LocalDate.parse(szuld,formatter), most).getYears());

                            Node nodecimt=nodeList.item(i);
                                if(nodecimt.getNodeType()==Node.ELEMENT_NODE){
                                    Element cimelement=(Element) node;
                                    System.out.println("Lakcíme: " + cimelement.getElementsByTagName("iranyitosz").item(0).getTextContent() + ", " + cimelement.getElementsByTagName("varos").item(0).getTextContent() + ", " + cimelement.getElementsByTagName("utcahsz").item(0).getTextContent());
                            }

                        }
                    }
                }

                //Nagy Endre nevű kuncsaft kölcsönzéseinek kiírása
                nodeList=document.getElementsByTagName("kuncsaft");
                
                for(int i =0;i<nodeList.getLength();i++){
                    Node node = nodeList.item(i);

                    if(node.getNodeType()==Node.ELEMENT_NODE){
                        Element element=(Element) node;
                        String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                        if(nev.equals("Nagy Endre"))
                        {
                             
                            int ne_id =Integer.parseInt(element.getAttributes().getNamedItem("k_id").getNodeValue());
                            
                            nodeList2=document.getElementsByTagName("k_kk");
                            for (int j = 0; j < nodeList2.getLength(); j++) {
                                Node node2=nodeList2.item(i);
                                    if(node.getNodeType()==Node.ELEMENT_NODE){
                                    Element element2=(Element) node2;
                                    System.out.println("~");
                                     if(Integer.parseInt(element2.getAttributes().getNamedItem("k_id").getNodeValue()) == ne_id){
                                        System.out.println("\nAktuális elem: " + node.getNodeName());
                                        System.out.println("~~~~~~~~");
                                        System.out.println("Időpont: " + element2.getElementsByTagName("idopont").item(0).getTextContent());

                                        
                                    }

                                }
                            
                        }
                        
                        }
                    }
                
            }


        }catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
