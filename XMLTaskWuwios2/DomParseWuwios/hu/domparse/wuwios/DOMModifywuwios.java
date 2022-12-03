package hu.domparse.wuwios;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


public class DOMModifywuwios {
    public static void main(String[] args) {
        try {
            NodeList nodeList;

            DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            //Fájl beolvasása
            Document document=builder.parse(new File("XMLWUWIOS.xml"));
            document.getDocumentElement().normalize();
            //videótéka nevének átírása
            //Aktuális elem meghatározása
            nodeList=document.getElementsByTagName("videoteka");

            for(int i =0;i<nodeList.getLength();i++){
                Node node= nodeList.item(i);
                //Videótéka nevének átírása.
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    element.getElementsByTagName("nev").item(0).setTextContent("Menőbb TK");
                }
            }
            //Minden kazetta átírása DVDre
            //Aktuális elem meghatározása
            nodeList=document.getElementsByTagName("tetel");

            for(int i =0;i<nodeList.getLength();i++){
                Node node= nodeList.item(i);
                //tétel átírása
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    if(element.getElementsByTagName("tipus").item(0).getTextContent().equals("Kazetta") )
                    {
                    element.getElementsByTagName("tipus").item(0).setTextContent("DVD");
                    }
                }
            }

            //árak egységesítése
            nodeList=document.getElementsByTagName("tetel");

            for(int i =0;i<nodeList.getLength();i++){
                Node node= nodeList.item(i);
                //tétel átírása
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    if(Integer.parseInt(element.getElementsByTagName("ar").item(0).getTextContent()) < 1600)
                        {
                    element.getElementsByTagName("ar").item(0).setTextContent("1600");
                        }
                }
            }


            //tulajdonosadószamank átírása
            //Aktuális elem meghatározása
            nodeList=document.getElementsByTagName("tulajdonos");

            for(int i =0;i<nodeList.getLength();i++){
                Node node= nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    element.getElementsByTagName("adoszam").item(0).setTextContent("321");
                }
            }

            //Kiss Virág szuletési dátumának átírása
            //Aktuális elem meghatározása
            nodeList=document.getElementsByTagName("kuncsaft");

            for(int i =0;i<nodeList.getLength();i++){
                Node node= nodeList.item(i);
                //tétel átírása
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    if(element.getElementsByTagName("nev").item(0).getTextContent().equals("Kiss Virág") )
                    {
                    element.getElementsByTagName("szuld").item(0).setTextContent("2022.05.07.");
                    }
                }
            }


            //Módosított xml fájl létrehozása    
            Transformer transformer= TransformerFactory.newInstance().newTransformer();
            Source input=new DOMSource(document);
            Result output=new StreamResult(new File("XMLWUWIOSModify.xml"));
            transformer.transform(input,output);

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
