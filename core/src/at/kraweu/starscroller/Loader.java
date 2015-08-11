package at.kraweu.starscroller;

import at.kraweu.xmlParser.MyDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

/**
 * Created by Kraweu on 02.08.2015.
 */
public class Loader
{
    String[] documentStrings;
    MyDocument[] documents;

    public static void main(String[] args)
    {
        Loader loader = new Loader();
    }

    public Loader()
    {
        loadXmlFiles();
        WeaponType[] weaponTypes = loadWeaponTypes();
        System.out.println(weaponTypes[0].getAcceleration());
        System.out.println(weaponTypes[0].getName());
        System.out.println(weaponTypes[0].getAsset());
        System.out.println(weaponTypes[0].getProjectileasset());
        System.out.println(weaponTypes[0].getShotposx());
    }

    /**
     * Loads all XML Files in the main Directory into documentStrings and documents
     */
    public void loadXmlFiles()
    {
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();
        int numberofxmlfiles = 0;

        for (int i = 0; i < listOfFiles.length; i++)
        {
            String filename = listOfFiles[i].getName();
            if (filename.endsWith(".xml") || filename.endsWith(".XML"))
            {
                numberofxmlfiles++;
            }
        }
        documentStrings = new String[numberofxmlfiles];
        documents = new MyDocument[numberofxmlfiles];
        int readxmlfiles = 0;
        for (int i = 0; i < listOfFiles.length; i++)
        {
            String filename = listOfFiles[i].getName();
            if (filename.endsWith(".xml") || filename.endsWith(".XML"))
            {
                documentStrings[readxmlfiles] = listOfFiles[i].getName();
                documents[readxmlfiles] = new MyDocument(documentStrings[readxmlfiles]);
//              System.out.println("Loaded: "filename);//Prints the Loaded Files
                readxmlfiles++;
            }
        }
    }

    private WeaponType[] loadWeaponTypes()
    {
        WeaponType[] weaponTypes = null;
        for (MyDocument mydocument : documents)
        {
            Document document = mydocument.getDocument();

            NodeList wrapperlist = document.getElementsByTagName("weapontypes");

            NodeList weaponTypeList = document.getElementsByTagName("weapontype");
            if (wrapperlist.getLength() == 0 && weaponTypeList.getLength() == 0)
                break;
            weaponTypes = new WeaponType[weaponTypeList.getLength()];
            int i = 0;
            for (WeaponType weaponType : weaponTypes)
            {
                weaponType = new WeaponType();

                Node tempnode = null;
                tempnode = getChild(weaponTypeList.item(i), "reloadtime");
                if (tempnode != null)
                    weaponType.setReloadtime(Integer.parseInt(tempnode.getNodeValue()));

                tempnode = getChild(weaponTypeList.item(i), "shotpos");
                if (tempnode != null)
                {
                    Element element = (Element) document.adoptNode(tempnode);
                    weaponType.setShotposx(Integer.parseInt(element.getAttribute("x")));
                    weaponType.setShotposy(Integer.parseInt(element.getAttribute("y")));
                }

                tempnode = getChild(weaponTypeList.item(i), "assetname");
                if (tempnode != null)
                    weaponType.setAsset(tempnode.getNodeValue());

                tempnode = getChild(weaponTypeList.item(i), "accelleration");
                if (tempnode != null)
                    weaponType.setAcceleration(Integer.parseInt(tempnode.getNodeValue()));


                //Projectile
                Node projectile = getChild(weaponTypeList.item(i), "projectile");
                tempnode = getChild(projectile, "damage");
                if (tempnode != null)
                    weaponType.setDamage(Integer.parseInt(tempnode.getNodeValue()));

                tempnode = getChild(projectile, "speed");
                if (tempnode != null)
                {
                    Element element = (Element) document.adoptNode(tempnode);
                    weaponType.setSpeedx(Integer.parseInt(element.getAttribute("x")));
                    weaponType.setSpeedy(Integer.parseInt(element.getAttribute("y")));

                }

                tempnode = getChild(projectile, "size");
                if (tempnode != null)
                {
                    Element element = (Element) document.adoptNode(tempnode);
                    weaponType.setSizex(Integer.parseInt(element.getAttribute("x")));
                    weaponType.setSizey(Integer.parseInt(element.getAttribute("y")));
                }

                tempnode = getChild(projectile, "asset");
                if (tempnode != null)
                    weaponType.setProjectileasset(tempnode.getNodeValue());
                tempnode = getChild(projectile, "rotation");
                if (tempnode != null)
                    weaponType.setRotation(Integer.parseInt(tempnode.getNodeValue()));
                tempnode = getChild(projectile, "swaying");
                if (tempnode != null)
                    weaponType.setSwaying(Integer.parseInt(tempnode.getNodeValue()));
                i++;
            }
        }
        if (weaponTypes != null)
            return weaponTypes;
        else
            return new WeaponType[0];
    }

    public static Node getChild(Node parent, String name)
    {
        if (parent != null)
        {
            for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling())
            {
                if (name.equals(child.getNodeName()))
                {
                    return child;
                }
            }
        }
        return null;
    }

}
