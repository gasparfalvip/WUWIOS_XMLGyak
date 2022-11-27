package hu.domparse.wuwios;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadwuwios {
    public static void main(String[] args){
        NodeList list;
        NodeList list2;
        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=factory.newDocumentBuilder();
            //fájl beolvasása
            Document document=documentBuilder.parse(new File("XMLWUWIOS.xml"));
            document.getDocumentElement().normalize();
            //root elem megkeresése
            System.out.println("Root element : " + document.getDocumentElement().getNodeName());
            System.out.println("~~~~~~~~~~~~~~~~~");
            //Aktuális elem meghatározása
            list=document.getElementsByTagName("videoteka");

            for (int i=0;i<list.getLength();i++) {
                Node node=list.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());
                //Videótéka adatainak kiirása
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("Videótéka id: " + element.getAttribute("tk_id"));
                    System.out.println("Neve: "
                    + element.getElementsByTagName("nev").item(0).getTextContent());

                    //Videótéka címének kiirása (összetett elem)
                    Node nodecimt=list.item(i);
                    if(nodecimt.getNodeType()==Node.ELEMENT_NODE){
                        Element cimelement=(Element) node;
                        System.out.println("Cím: " + cimelement.getElementsByTagName("iranyitosz").item(0).getTextContent() + ", " + cimelement.getElementsByTagName("varos").item(0).getTextContent() + ", " + cimelement.getElementsByTagName("utcahsz").item(0).getTextContent());
                    }
                }
            }

            list=document.getElementsByTagName("dolgozo");

            for (int i=0;i<list.getLength();i++) {
                Node node=list.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());
                //Dolgozó adatainak kiirása
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("Dolgozó id: " + element.getAttribute("d_id"));
                    System.out.println("Videótéka id: " + element.getAttribute("tk_id"));
                    System.out.println("Neve: "
                    + element.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Beosztása: "
                    + element.getElementsByTagName("beosztas").item(0).getTextContent());
                    System.out.println("Bére: "
                    + element.getElementsByTagName("ber").item(0).getTextContent());
                }
            }

            list=document.getElementsByTagName("videoteka");
            
            for (int i=0;i<list.getLength();i++) {
                Node node=list.item(i);
                //Videótéka kiválasztása, hogy a hozzá tartozó tételeket adatait kiírjam
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("~~~~~~~~~~~~~~~~~");
                    System.out.println("\n" + element.getElementsByTagName("nev").item(0).getTextContent()+ " tételei:");
                }
                list2=document.getElementsByTagName("tetel");
                for (int j=0;j<list2.getLength();j++) {
                    Node node2=list2.item(j);
                    //Tételek kiírása melyek a megfelelő tékáhoztartoznak
                    if(node2.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2=(Element) node2;
                        if( Integer.parseInt(element2.getAttributes().getNamedItem("tk_id").getNodeValue()) == i+1){
                            
                            System.out.println("\n" + element2.getElementsByTagName("cim").item(0).getTextContent());
                            System.out.println("Tétel id: " + element2.getAttribute("t_id"));
                            System.out.println("Típus: "
                            + element2.getElementsByTagName("tipus").item(0).getTextContent());
                            System.out.println("Megjelenés: "
                            + element2.getElementsByTagName("megjelenes").item(0).getTextContent());
                            System.out.println("Ár: "
                            + element2.getElementsByTagName("ar").item(0).getTextContent());
                            System.out.println("Kor határ: "
                            + element2.getElementsByTagName("korhatar").item(0).getTextContent());
                        }
                    }
                }
            }

            list=document.getElementsByTagName("kuncsaft");

            for (int i=0;i<list.getLength();i++) {
                Node node=list.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());
                //Kuncsaft adatainak kiirása
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("Kuncsaft id: " + element.getAttribute("k_id"));
                    System.out.println("Videótéka id: " + element.getAttribute("tk_id"));
                    System.out.println("Neve: "
                    + element.getElementsByTagName("nev").item(0).getTextContent());
                    System.out.println("Születési dátum: "
                    + element.getElementsByTagName("szuld").item(0).getTextContent());
                    
                    //Videótéka címének kiirása (összetett elem)
                    Node nodecimt=list.item(i);
                    if(nodecimt.getNodeType()==Node.ELEMENT_NODE){
                        Element cimelement=(Element) node;
                        System.out.println("Cím: " + cimelement.getElementsByTagName("iranyitosz").item(0).getTextContent() + ", " + cimelement.getElementsByTagName("varos").item(0).getTextContent() + ", " + cimelement.getElementsByTagName("utcahsz").item(0).getTextContent());
                    }
                }
                
            }

            
            list=document.getElementsByTagName("kolcsonzes");

            for (int i=0;i<list.getLength();i++) {
                Node node=list.item(i);
                System.out.println("\nAktuális elem: " + node.getNodeName());
                //Dolgozó adatainak kiirása
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("Kölcsönzés id: " + element.getAttribute("kk_id"));   
                        Node nodetetelek=list.item(i);
                        if(nodetetelek.getNodeType()==Node.ELEMENT_NODE){
                            Element tetelelement=(Element) node;
                            for (int j=0;j<tetelelement.getElementsByTagName("t").getLength();j++) {
                                System.out.println("Kölcsönzések :"
                                 + tetelelement.getElementsByTagName("t").item(j).getTextContent());
                        
                            }
                        
                        }
                }
            }

            list=document.getElementsByTagName("k_kk");

            for (int i=0;i<list.getLength();i++) {
                Node node=list.item(i);
                System.out.println("\nAktuális elem: kuncsaft-kölcsönzés kapcsolat");
                //Kuncsaft adatainak kiirása
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("Kuncsaft id: " + element.getAttribute("k_id"));
                    System.out.println("Kolcsonzes id: " + element.getAttribute("kk_id"));
                    System.out.println("Kolcsonzes kezdete: "
                    + element.getElementsByTagName("idopont").item(0).getTextContent());
                }
                
            }



        }catch (ParserConfigurationException | IOException | SAXException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
