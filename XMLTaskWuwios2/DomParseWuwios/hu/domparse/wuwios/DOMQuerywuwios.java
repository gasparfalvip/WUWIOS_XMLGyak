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
                System.out.println("20évnél idősebb kuncsaftok kiírása: \n~~~~~~~~");
                for(int i =0;i<nodeList.getLength();i++){
                    Node node= nodeList.item(i);

                    
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

                //1600-nál olcsóbb tételek listázása
                //Aktuális elem meghatározása
                nodeList=document.getElementsByTagName("tetel");
                System.out.println("\n~~~~~~~~ \n1600-nál olcsóbb tételek listázása: \n~~~~~~~~");
                for(int i =0;i<nodeList.getLength();i++){
                    Node node= nodeList.item(i);

                    
                    if(node.getNodeType()==Node.ELEMENT_NODE){
                        Element element=(Element) node;

                        if(Integer.parseInt(element.getElementsByTagName("ar").item(0).getTextContent()) < 1600)
                        {
                            System.out.println("\n" + element.getElementsByTagName("cim").item(0).getTextContent());
                            System.out.println("Tétel id: " + element.getAttribute("t_id"));
                            System.out.println("Típus: "
                            + element.getElementsByTagName("tipus").item(0).getTextContent());
                            System.out.println("Megjelenés: "
                            + element.getElementsByTagName("megjelenes").item(0).getTextContent());
                            System.out.println("Ár: "
                            + element.getElementsByTagName("ar").item(0).getTextContent());
                            System.out.println("Kor határ: "
                            + element.getElementsByTagName("korhatar").item(0).getTextContent());

                        }
                    }
                }


                //tételek felsorolása videó téka szerint
                nodeList=document.getElementsByTagName("videoteka");
            
                 for (int i=0;i<nodeList.getLength();i++) {
                Node node=nodeList.item(i);
                //Videótéka kiválasztása, hogy a hozzá tartozó tételeket adatait kiírjam
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element=(Element) node;
                    System.out.println("~~~~~~~~~~~~~~~~~");
                    System.out.println("\n" + element.getElementsByTagName("nev").item(0).getTextContent()+ " tételei:");
                }
                nodeList2=document.getElementsByTagName("tetel");
                for (int j=0;j<nodeList2.getLength();j++) {
                    Node node2=nodeList2.item(j);
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

                //16 éven felülieknek ajánlott filmek
                //Aktuális elem meghatározása
                nodeList=document.getElementsByTagName("tetel");
                System.out.println("\n~~~~~~~~ \n16 éven felülieknek: \n~~~~~~~~");
                for(int i =0;i<nodeList.getLength();i++){
                    Node node= nodeList.item(i);

                    
                    if(node.getNodeType()==Node.ELEMENT_NODE){
                        Element element=(Element) node;

                        if(Integer.parseInt(element.getElementsByTagName("korhatar").item(0).getTextContent()) == 16)
                        {
                            System.out.println("\n" + element.getElementsByTagName("cim").item(0).getTextContent());
                            System.out.println("Tétel id: " + element.getAttribute("t_id"));
                            System.out.println("Típus: "
                            + element.getElementsByTagName("tipus").item(0).getTextContent());
                            System.out.println("Megjelenés: "
                            + element.getElementsByTagName("megjelenes").item(0).getTextContent());
                            System.out.println("Ár: "
                            + element.getElementsByTagName("ar").item(0).getTextContent());
                            System.out.println("Kor határ: "
                            + element.getElementsByTagName("korhatar").item(0).getTextContent());

                        }
                    }
                }
                //Kazetta típusú tételek listázása
                nodeList=document.getElementsByTagName("tetel");
                System.out.println("\n~~~~~~~~ \nKazetták listázása: \n~~~~~~~~");
                for(int i =0;i<nodeList.getLength();i++){
                    Node node= nodeList.item(i);

                    
                    if(node.getNodeType()==Node.ELEMENT_NODE){
                        Element element=(Element) node;

                        if(element.getElementsByTagName("tipus").item(0).getTextContent().equals("Kazetta") )
                        {
                            System.out.println("\n" + element.getElementsByTagName("cim").item(0).getTextContent());
                            System.out.println("Tétel id: " + element.getAttribute("t_id"));
                            System.out.println("Típus: "
                            + element.getElementsByTagName("tipus").item(0).getTextContent());
                            System.out.println("Megjelenés: "
                            + element.getElementsByTagName("megjelenes").item(0).getTextContent());
                            System.out.println("Ár: "
                            + element.getElementsByTagName("ar").item(0).getTextContent());
                            System.out.println("Kor határ: "
                            + element.getElementsByTagName("korhatar").item(0).getTextContent());

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
