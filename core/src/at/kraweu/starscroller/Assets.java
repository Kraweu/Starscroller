package at.kraweu.starscroller;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Alex on 19.05.2015.
 */
public class Assets
{
    TextureAtlas assetatlas;
    AtlasRegion ship;
    AtlasRegion weapon;
    AtlasRegion projectile;

    public Assets()
    {
        assetatlas = new TextureAtlas("packedImgs/Starscroller.pack");



        ship = assetatlas.findRegion("playerShip1_green");
        weapon = assetatlas.findRegion("weapon2");
        projectile = assetatlas.findRegion("laserBlue01");


    }
}
