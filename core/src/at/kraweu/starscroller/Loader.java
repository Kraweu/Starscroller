package at.kraweu.starscroller;

import at.kraweu.xmlParser.MyDocument;
import com.badlogic.gdx.math.Vector2;
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

                //Get childnodes if available
                if (levelList.item(i).hasChildNodes())
                    levelchildren = levelList.item(i).getChildNodes();
                else
                    continue;

                tempnode = levelList.item(i);
                if (tempnode != null)
                {
                    try
                    {
                        Element element = (Element) tempnode;
                        if (element.getAttribute("Name").isEmpty())
                            levels[i].name = "No Name Found";
                        else
                            levels[i].name = element.getAttribute("Name");
                    } catch (Exception e)
                    {
                        System.out.println("Error loading level name in Level: " + i);
                    }
                }


                /**Number of Waves found in Level*/
                int wavenr = 0;
                for (int j = 0; j < levelchildren.getLength(); j++)
                {
                    if (levelchildren.item(j).getNodeName().equals("wave"))
                        wavenr++;
                }
                Node[] waveNodes = new Node[wavenr];
                wavenr = 0;
                for (int j = 0; wavenr < waveNodes.length; j++)
                {
                    if (levelchildren.item(j).getNodeName().equals("wave"))
                    {
                        waveNodes[wavenr] = levelchildren.item(j);
                        wavenr++;
                    }

                }


                levels[i].waves = new Wave[wavenr];
                for (int currentwave = 0; currentwave < waveNodes.length; currentwave++)
                {
                    NodeList wavechildren = null;
                    if (waveNodes[currentwave].hasChildNodes())
                        wavechildren = waveNodes[currentwave].getChildNodes();
                    else
                        continue;
                    levels[i].waves[currentwave] = new Wave(levels[i], 0);
                    tempnode = waveNodes[currentwave];
                    if (tempnode != null)
                    {
                        try
                        {
                            Element element = (Element) tempnode;
                            levels[i].waves[currentwave].Nr = (int) Float.parseFloat(element.getAttribute("Nr"));
                            levels[i].waves[currentwave].timeToStart = ((int) Float.parseFloat(element.getAttribute("timeToStart")));
                        } catch (NumberFormatException e)
                        {
                            System.out.println("Format error in Level: " + levels[i].name);
                        }
                    }
                    /**Number of Enemies found in Wave*/
                    int enemienr = 0;
                    for (int j = 0; j < wavechildren.getLength(); j++)
                    {
                        if (wavechildren.item(j).getNodeName().equals("enemy"))
                            enemienr++;
                    }
                    Node[] enemyNodes = new Node[enemienr];
                    Enemy[] enemies = new Enemy[enemienr];
                    enemienr = 0;
                    for (int j = 0; enemienr < enemyNodes.length; j++)
                    {
                        if (wavechildren.item(j).getNodeName().equals("enemy"))
                        {
                            enemyNodes[enemienr] = wavechildren.item(j);
                            enemienr++;
                        }

                    }
                    for (int currentenemy = 0; currentenemy < enemyNodes.length; currentenemy++)
                    {
                        enemies[currentenemy] = new Enemy();
                        tempnode = getChild(enemyNodes[currentenemy], "shiptype");
                        if (tempnode != null)
                        {
                            try
                            {
                                String name = tempnode.getAttributes().getNamedItem("Name").getNodeValue();
                                Ship ship = Ship.getShipFromArray(ships, name);
                                if (ship != null)
                                    enemies[currentenemy].setShip(ship.myClone(), assets);
                                else
                                {
                                    System.out.println("Enemy Asset not found, Enemy not loaded");
                                    enemies[currentenemy].loadingError();
                                    continue;
                                }
                            } catch (NullPointerException e)
                            {
                                System.out.println("Nullpointer Exception while loading enemies");
                            }
                        }

                        NodeList positions = getChild(enemyNodes[currentenemy], "positions").getChildNodes();

                        for (int j = 0; j < positions.getLength(); j++)
                        {
                            if (positions.item(j) instanceof Element)
                            {
                                int x = getIntAttribute(positions.item(j), "position", "x");
                                int y = getIntAttribute(positions.item(j), "position", "y");
                                double time = getDoubleAttribute(positions.item(j), "position", "time");
                                if (x != Integer.MAX_VALUE && y != Integer.MAX_VALUE)
                                {
                                    enemies[currentenemy].moveAI.waypoints.add(new Vector2(x, y));
                                    if (time != Double.MAX_VALUE)
                                        enemies[currentenemy].moveAI.time.add(time);
                                    else
                                    {
                                        enemies[currentenemy].moveAI.time.add(2d);
                                        System.out.println("Time not Found Set Standard Time of 2 Seconds in Wave " + levels[i].waves[currentwave].Nr);
                                    }
                                }
                            }
                        }
                        enemies[currentenemy].getShip().setPosx(enemies[currentenemy].moveAI.waypoints.peekFirst().x);
                        enemies[currentenemy].getShip().setPosy(enemies[currentenemy].moveAI.waypoints.peekFirst().y);
                        enemies[currentenemy].getShip().setRotation(180);
                    }


                    for (int j = 0; j < enemies.length; j++)
                    {
                        levels[i].waves[currentwave].enemies.addEnemy(enemies[j]);
                    }
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

                ships[i].setSpeed((float) getdoubleValueofChild(shipList.item(i), "speed"));

                ships[i].setHealth((float) getdoubleValueofChild(shipList.item(i), "health"));

                String asset = getValueofChild(shipList.item(i), "asset");

                ships[i].setAsset(asset, assets.getRegion(asset).packedWidth, assets.getRegion(asset).packedHeight);


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
                        weaponSlots[j] = new WeaponSlot(getIntAttributeofChild(child, "position", "x"), getIntAttributeofChild(child, "position", "y"), ships[i]);

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
                            weaponSlots[weaponSlots.length - 1] = weaponSlots[j].myClone(ships[i]);
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

                weaponTypes[i].setReloadtime((float) getdoubleValueofChild(weaponTypeList.item(i), "reloadtime"));

                weaponTypes[i].setShotposx(getIntAttributeofChild(weaponTypeList.item(i), "shotpos", "x"));
                weaponTypes[i].setShotposy(getIntAttributeofChild(weaponTypeList.item(i), "shotpos", "y"));

                weaponTypes[i].setAsset(getValueofChild(weaponTypeList.item(i), "asset"));


                //Projectile
                Node projectile = getChild(weaponTypeList.item(i), "projectile");

                weaponTypes[i].setDamage((float) getdoubleValueofChild(projectile, "damage"));

                //ToDO Float instead of int
                weaponTypes[i].setSpeedx(getIntAttributeofChild(projectile, "speed", "x"));
                weaponTypes[i].setSpeedy(getIntAttributeofChild(projectile, "speed", "y"));

                weaponTypes[i].setAcceleration((float) getdoubleValueofChild(projectile, "accelleration"));
                weaponTypes[i].setSwaying((float) getdoubleValueofChild(projectile, "swaying"));
                weaponTypes[i].setRotation((float) getdoubleValueofChild(projectile, "rotation"));
                weaponTypes[i].setSizemult((float) getdoubleValueofChild(projectile, "sizemult"));
                weaponTypes[i].setProjectileasset(getValueofChild(projectile, "asset"));
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

    public static String getValueofChild(Node node, String name)
    {
        Node child = getChild(node, name);
        if (child != null)
            return child.getFirstChild().getNodeValue();
        else
            return null;
    }

    public static int getintValueofChild(Node node, String name)
    {
        String text = getValueofChild(node, name);
        if (text == null)
        {
            System.out.println("Loading error: Node " + name + " Integer value is missing");
            return -1;
        }
        if (isValidInt(text))
        {
            return Integer.parseInt(text);
        } else
        {
            System.out.println("Loading error: Node " + name + " not an Integer value");
            return -1;
        }
    }

    public static double getdoubleValueofChild(Node node, String name)
    {
        String text = getValueofChild(node, name);
        if (text == null)
        {
            System.out.println("Loading error: Node " + name + " double value is missing");
            return -1;
        }
        try
        {
            return Double.parseDouble(text);
        } catch (NumberFormatException e)
        {
            System.out.println("Loading error: Node " + name + " not a Double value");
            return -1;
        }
    }

    public static String getAttribute(Node node, String attribute)
    {
        Element elem = (Element) node;
        String text = null;
        if (elem != null)
            text = elem.getAttribute(attribute);
        else
        {
            System.out.println("Node for Attribute " + attribute + " not found");
            return null;
        }
        if (text == null)
        {
            System.out.println("Loading error: Node " + elem.getTagName() + " attribute " + attribute + " Value is missing");
            return null;
        }
        return text;
    }

    public static String getAttribute(Node node, String nodename, String attribute)
    {
        Element elem = (Element) node;
        if (elem != null)
        {
            if (elem.getTagName().equals(nodename))
            {
                return getAttribute(node, attribute);
            }
        }
        return null;
    }

    public static int getIntAttribute(Node node, String nodename, String attribute)
    {
        String text = getAttribute(node, nodename, attribute);
        if (text == null)
        {
            System.out.println("Int Value missing");
            return Integer.MAX_VALUE;
        }
        if (isValidInt(text))
        {
            return Integer.parseInt(text);
        } else
        {
            System.out.println("Loading error: Node " + nodename + " Attribute " + attribute + " not an Integer value");
            return Integer.MAX_VALUE;
        }
    }

    public static double getDoubleAttribute(Node node, String nodename, String attribute)
    {
        String text = getAttribute(node, nodename, attribute);
        if (text == null)
        {
            System.out.println("Double Value missing");
            return Double.MAX_VALUE;
        }
        try
        {
            return Double.parseDouble(text);
        } catch (Exception e)
        {
            System.out.println("Loading error: Node " + nodename + " Attribute " + attribute + " not a Double value");
            return Double.MAX_VALUE;
        }
    }

    public static String getAttributeofChild(Node node, String child, String attribute)
    {
        Element childelem = (Element) getChild(node, child);
        String text = null;
        if (childelem != null)
            text = childelem.getAttribute(attribute);
        else
            System.out.println("Node " + child + " for Attribute " + attribute + " not found");
        if (text == null)
        {
            System.out.println("Loading error: Node " + child + " attribute " + attribute + " Value is missing");
            return null;
        }
        return text;
    }

    public static int getIntAttributeofChild(Node node, String child, String attribute)
    {
        String text = getAttributeofChild(node, child, attribute);
        if (text == null)
        {
            System.out.println("Int Value missing");
            return -1;
        }
        if (isValidInt(text))
        {
            return Integer.parseInt(text);
        } else
        {
            System.out.println("Loading error: Node " + child + " Attribute " + attribute + " not an Integer value");
            return -1;
        }
    }

    static boolean isValidInt(String text)
    {
        for (int i = 0; i < text.length(); i++)
        {
            if (i == 0 && text.charAt(i) == '-')
                continue;
            if (!Character.isDigit(text.charAt(i)))
            {
                return false;
            }

        }
        return true;
    }

    /**
     * Returns First Child with given name
     */
    public static Node getChild(Node parent, String name)
    {
        if (name == null)
            return null;
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
//    /**
//     * Returns First Sibling with given name
//     */
//    public static Node getSibling(Node first, String siblingName)
//    {
//        if (siblingName==null)
//            return null;
//        if (first != null)
//        {
//            for (Node sibling = first; sibling != null; sibling = sibling.getNextSibling())
//            {
//                if (siblingName.equals(sibling.getNodeName()))
//                {
//                    return sibling;
//                }
//            }
//        }
//        return null;
//    }

}
