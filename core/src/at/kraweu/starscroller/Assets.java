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

    private Array<AtlasRegion> regions;
    private Iterator<AtlasRegion> iterator;
    private String name;

    public Assets()
    {
        //Load all assets into regions
        TextureAtlas assetatlas = new TextureAtlas("packedImgs/Starscroller.pack");

        regions = assetatlas.getRegions();


        iterator = regions.iterator();
    }

    /**
     * Returns AltasRegion of Specific Name
     * Or Null if Not Found + Exception
     */
    public AtlasRegion getRegion(String name)
    {
        try
        {
            iterator = regions.iterator();
            while (iterator.hasNext())
            {
                AtlasRegion region = iterator.next();
                if ((region).name.equals(name))
                {
                    return region;
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
