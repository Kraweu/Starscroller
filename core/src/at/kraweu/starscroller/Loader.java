package at.kraweu.starscroller;

import at.kraweu.xmlParser.MyDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Kraweu on 02.08.2015.
 */
public class Loader
{
    private String[] documentStrings;
    private MyDocument[] documents;
    private Assets assets;
    public static void main(String[] args)
    {
        Loader loader = new Loader(new Assets());
    }

    public Loader(Assets assets)
    {
        this.assets = assets;
        loadXmlFiles();
        WeaponType[] weaponTypes = loadWeaponTypes();
        System.out.println(weaponTypes[0].getAcceleration());
        System.out.println(weaponTypes[0].getName());
        System.out.println(weaponTypes[0].getAsset());
        System.out.println(weaponTypes[0].getProjectileasset());
        System.out.println(weaponTypes[0].getShotposx());
        Ship[] ships = loadShips(weaponTypes);
        System.out.println(ships[0].getHealth());
    }

    public Loader()
    {
        loadXmlFiles();
    }

    public void setAssets(Assets assets)
    {
        this.assets = assets;
    }
    /**
     * Loads all XML Files in the main Directory into documentStrings and documents
     */
    public void loadXmlFiles()
    {
        File folder = new File("./");
        FileFilter filter = new FileFilter()
        {
            @Override
            public boolean accept(File pathname)
            {
                if (pathname.getName().endsWith(".xml") || pathname.getName().endsWith(".XML"))
                    return true;
                else
                    return false;
            }
        };
        File[] listOfFiles = folder.listFiles(filter);
        int numberofxmlfiles = listOfFiles.length;

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

    public Level[] loadLevels(Ship[] ships)
    {
        Level[] levels = null;
        for (MyDocument mydocument : documents)
        {
            Document document = mydocument.getDocument();

            NodeList wrapperlist = document.getElementsByTagName("levels");
//            wrapperlist.item(0).getNodeValue();
            NodeList levelList = document.getElementsByTagName("level");
            if (wrapperlist.getLength() == 0)
                break;
            levels = new Level[levelList.getLength()];
            for (int i = 0; i < levels.length; i++)
            {
                levels[i] = new Level();

                Node tempnode = null;

                Element elementparent = (Element) levelList.item(i);

                Wave[] waves = null;
                NodeList levelchildren = null;

                if (levelList.item(i).hasChildNodes())
                    levelchildren = levelList.item(i).getChildNodes();
                else
                    continue;

                /**Number of Enemies found in Level*/
                int enemienr = 0;
                for (int j = 0; j < levelchildren.getLength(); j++)
                {
                    if (levelchildren.item(j).getNodeName().equals("enemy"))
                        enemienr++;
                }
                Node[] enemyNodes = new Node[enemienr];
                Enemy[] enemies = new Enemy[enemienr];
                enemienr = 0;
                for (int j = 0; enemienr < enemyNodes.length; j++)
                {
                    if (levelchildren.item(j).getNodeName().equals("enemy"))
                    {
                        enemyNodes[enemienr] = levelchildren.item(j);
                        enemienr++;
                    }

                }
                for (int j = 0; j < enemyNodes.length; j++)
                {
                    enemies[j] = new Enemy();
                    tempnode = getChild(enemyNodes[j], "shiptype");
                    if (tempnode != null)
                    {
                        try
                        {
                            enemies[j].setShip(Ship.getShip(ships, tempnode.getAttributes().getNamedItem("Name").getNodeValue()).clone(), assets);
                        } catch (NullPointerException e)
                        {
                            System.out.println("Nullpointer Exception while loading enemies");
                        }
                    }
                    tempnode = getChild(enemyNodes[j], "position");
                    if (tempnode != null)
                    {
                        try
                        {
                            Element element = (Element) tempnode;
                            enemies[j].setPosx(Double.parseDouble(element.getAttribute("x")));
                            enemies[j].setPosy(Double.parseDouble(element.getAttribute("y")));
                        } catch (NumberFormatException e)
                        {

                        }
                    }
                }
                levels[i].waves = new Wave[1];
                levels[i].waves[0] = new Wave();
                for (int j = 0; j < enemies.length; j++)
                {
                    levels[i].waves[0].enemies.addEnemy(enemies[j]);
                }
            }
        }
        return levels;
    }
    public Ship[] loadShips(WeaponType[] weapontypes)
    {
        Ship[] ships = null;
        for (MyDocument mydocument : documents)
        {
            Document document = mydocument.getDocument();

            NodeList wrapperlist = document.getElementsByTagName("ships");

            NodeList shipList = document.getElementsByTagName("ship");
            if (wrapperlist.getLength() == 0 || shipList.getLength() == 0)
                continue;
            ships = new Ship[shipList.getLength()];
            for (int i = 0; i < ships.length; i++)
            {
                ships[i] = new Ship();

                Node tempnode = null;

                Element elementparent = (Element) shipList.item(i);
                ships[i].setName(elementparent.getAttribute("Name"));

                tempnode = getChild(shipList.item(i), "speed");
                if (tempnode != null)
                {
                    try
                    {
                        ships[i].setSpeed(Double.parseDouble(tempnode.getFirstChild().getNodeValue()));
                    } catch (NumberFormatException e)
                    {

                    }
                }

                tempnode = getChild(shipList.item(i), "health");
                if (tempnode != null)
                {
                    try
                    {
                        ships[i].setHealth(Double.parseDouble(tempnode.getFirstChild().getNodeValue()));
                    } catch (NumberFormatException e)
                    {

                    }
                }

                tempnode = getChild(shipList.item(i), "asset");
                if (tempnode != null)
                {
                    try
                    {
                        ships[i].setAsset(tempnode.getFirstChild().getNodeValue());
                    } catch (NumberFormatException e)
                    {

                    }
                }
                int weaponslotcount = 0;
                for (Node child = shipList.item(i).getFirstChild(); child != null; child = child.getNextSibling())
                {
                    if (child.getNodeName().equals("weaponslot"))
                    {
                        weaponslotcount++;
                    }
                }

                WeaponSlot[] weaponSlots = new WeaponSlot[weaponslotcount];
                int j = 0;
                for (Node child = shipList.item(i).getFirstChild(); child != null; child = child.getNextSibling())
                {
                    if (child.getNodeName().equals("weaponslot"))
                    {
                        tempnode = getChild(child, "position");
                        if (tempnode != null)
                        {
                            try
                            {
                                Element element = (Element) tempnode;
                                int x = 0, y = 0;
                                x = Integer.parseInt(element.getAttribute("x"));
                                y = Integer.parseInt(element.getAttribute("y"));
                                weaponSlots[j] = new WeaponSlot(x, y);
                            } catch (NumberFormatException e)
                            {

                            }
                        }

                        String name = (((Element) getChild(child, "weapon")).getAttribute("Name"));

                        tempnode = getChild(getChild(child, "weapon"), "weapontype");
                        WeaponType type = null;
                        if (tempnode != null)
                        {
                            type = (findWeaponType(weapontypes, ((Element) tempnode).getAttribute("Name")));
                        }
                        weaponSlots[j].setWeapon(new Weapon(type, name));
                        tempnode = getChild(getChild(child, "weapon"), "mirrorhoriz");
                        if (tempnode != null && ((Element) tempnode).getAttribute("Mirror").equals("true"))
                        {
                            WeaponSlot[] tempslot = new WeaponSlot[weaponSlots.length + 1];
                            for (int k = 0; k < weaponSlots.length; k++)
                            {
                                tempslot[k] = weaponSlots[k];
                            }
                            weaponSlots = tempslot;
                            weaponSlots[weaponSlots.length - 1] = weaponSlots[j].clone();
                            int height, width, widthship;
                            if (assets.getRegion(weaponSlots[j].getWeapon().getType().getAsset()) != null)
                            {
                                height = assets.getRegion(weaponSlots[j].getWeapon().getType().getAsset()).packedHeight;
                                width = assets.getRegion(weaponSlots[j].getWeapon().getType().getAsset()).packedWidth;
                                widthship = assets.getRegion(ships[i].getAsset()).packedWidth;
                                weaponSlots[weaponSlots.length - 1].posx = widthship - weaponSlots[j].posx - width;
                                weaponSlots[weaponSlots.length - 1].posy = weaponSlots[j].posy;//Stays the same because of Horizontal Mirroing
                            } else
                                System.out.println("Region for duplication not found on Ship: " + ships[i].getName() + " for " + j + 1 + ". Weapon");
                        }

                        j++;
                    }


                }
                ships[i].setWeaponSlots(weaponSlots);

            }
        }
        if (ships != null)
            return ships;
        else
        {
            System.out.println("No Ships loaded!");
            return new Ship[0];
        }
    }

    private WeaponType findWeaponType(WeaponType[] weapontypes, String name)
    {
        for (int i = 0; i < weapontypes.length; i++)
        {
            if (weapontypes[i].getName().equals(name))
                return weapontypes[i];
        }
        System.out.println("Error: Weapontype for Ship not Found, " + name);
        return null;
    }

    public WeaponType[] loadWeaponTypes()
    {
        WeaponType[] weaponTypes = null;
        for (MyDocument mydocument : documents)
        {
            Document document = mydocument.getDocument();

            NodeList wrapperlist = document.getElementsByTagName("weapontypes");

            NodeList weaponTypeList = document.getElementsByTagName("weapontype");
            if (wrapperlist.getLength() == 0 || weaponTypeList.getLength() == 0)
                continue;
            weaponTypes = new WeaponType[weaponTypeList.getLength()];
            for (int i = 0; i < weaponTypes.length; i++)
            {
                weaponTypes[i] = new WeaponType();

                Node tempnode = null;

                Element elementparent = (Element) weaponTypeList.item(i);
                weaponTypes[i].setName(elementparent.getAttribute("Name"));
                //ToDO Sort out where double instead of int needs to be parsed
                tempnode = getChild(weaponTypeList.item(i), "reloadtime");
                if (tempnode != null)
                {
                    try
                    {
                        weaponTypes[i].setReloadtime(Double.parseDouble(tempnode.getFirstChild().getNodeValue()));
                    } catch (NumberFormatException e)
                    {

                    }

                }

                tempnode = getChild(weaponTypeList.item(i), "shotpos");
                if (tempnode != null)
                {
                    Element element = (Element) tempnode;
                    weaponTypes[i].setShotposx(Integer.parseInt(element.getAttribute("x")));
                    weaponTypes[i].setShotposy(Integer.parseInt(element.getAttribute("y")));
                }

                tempnode = getChild(weaponTypeList.item(i), "asset");
                if (tempnode != null)
                    weaponTypes[i].setAsset(tempnode.getFirstChild().getNodeValue());




                //Projectile
                Node projectile = getChild(weaponTypeList.item(i), "projectile");
                tempnode = getChild(projectile, "damage");
                if (tempnode != null)
                    weaponTypes[i].setDamage(Integer.parseInt(tempnode.getFirstChild().getNodeValue()));

                tempnode = getChild(projectile, "speed");
                if (tempnode != null)
                {
                    Element element = (Element) tempnode;
                    weaponTypes[i].setSpeedx(Integer.parseInt(element.getAttribute("x")));
                    weaponTypes[i].setSpeedy(Integer.parseInt(element.getAttribute("y")));

                }

                tempnode = getChild(projectile, "accelleration");
                if (tempnode != null)
                    weaponTypes[i].setAcceleration(Integer.parseInt(tempnode.getFirstChild().getNodeValue()));

                tempnode = getChild(projectile, "swaying");
                if (tempnode != null)
                    weaponTypes[i].setSwaying(Integer.parseInt(tempnode.getFirstChild().getNodeValue()));

                tempnode = getChild(projectile, "rotation");
                if (tempnode != null)
                    weaponTypes[i].setRotation(Integer.parseInt(tempnode.getFirstChild().getNodeValue()));
                tempnode = getChild(projectile, "asset");

                if (tempnode != null)
                    weaponTypes[i].setProjectileasset(tempnode.getFirstChild().getNodeValue());
            }
        }
        if (weaponTypes != null)
            return weaponTypes;
        else
        {
            System.out.println("No Weapons loaded!");
            return new WeaponType[0];
        }
    }

    /**
     * Returns First Child with given name
     */
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
