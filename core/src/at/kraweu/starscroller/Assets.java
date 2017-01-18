package at.kraweu.starscroller;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Created by Alex on 19.05.2015.
 */
public class Assets
{

    private Array<AtlasRegion> regionlist;
    private Iterator<AtlasRegion> iterator;
    private String name;

    public Assets()
    {
        //Load all backgroundlayers into regionlist
        TextureAtlas assetatlas = new TextureAtlas("packedImgs/Starscroller.pack");

        regionlist = assetatlas.getRegions();


        iterator = regionlist.iterator();
    }

    public Array<AtlasRegion> getRegions(String name)
    {
        Array<AtlasRegion> found = new Array();
        try
        {
            if (name == null || name.isEmpty())
            {
                System.out.println("Searched Regionname was null or empty");
                throw new RegionNotFound();
            }
            iterator = regionlist.iterator();
            while (iterator.hasNext())
            {
                AtlasRegion regiontemp = iterator.next();
                if (regiontemp.name.equals(name))
                {
                    found.add(regiontemp);
                }
            }
            if (found.size == 0)
            {
                throw new RegionNotFound();
            }
            return found;
        } catch (RegionNotFound regionNotFound)
        {
            System.out.println();
            regionNotFound.printStackTrace();
            System.out.println();
            System.out.println("AtlasRegion not Found: " + name);
        }
        return null;
    }
    /**
     * Returns AltasRegion of Specific Name
     * Or Null if Not Found + Exception
     */
    public AtlasRegion getRegion(String name)
    {
        try
        {
            if (name == null || name.isEmpty())
            {
                System.out.println("Searched Regionname was null or empty");
                throw new RegionNotFound();
            }
            iterator = regionlist.iterator();
            while (iterator.hasNext())
            {
                AtlasRegion regiontemp = iterator.next();
                if (regiontemp.name.equals(name))
                {
                    return regiontemp;
                }
            }
            throw new RegionNotFound();
        } catch (RegionNotFound regionNotFound)
        {
            System.out.println();
            regionNotFound.printStackTrace();
            System.out.println();
            System.out.println("AtlasRegion not Found: " + name);
        }
        return null;
    }

    private static class RegionNotFound extends Throwable
    {
    }
}
