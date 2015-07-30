package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Alex on 19.05.2015.
 */
public class Weapon
{
    String name = "unnamed";
    double reloadtime = 1;
    double lastshot = 0;
    double posx;    //Position relative to the ship
    double posy;
    AtlasRegion asset=null;
    double shotposx; //Position relative to the Weapon
    double shotposy;


    //Projectile properties

    double damage = 1;
    double speedx;
    double speedy;
    int sizex;
    int sizey;
    double acceleration = 0;
    double swaying = 0;
    double rotation = 0;
    AtlasRegion projectileasset=null;

    public Weapon(String name, double reloadtime, double posx, double posy, AtlasRegion asset, double damage, double speedx, double speedy, int sizex, int sizey, AtlasRegion projectileasset)
    {
        this.name = name;
        this.reloadtime = reloadtime;
        this.posx = posx;
        this.posy = posy;
        this.asset = asset;
        this.damage = damage;
        this.speedx = speedx;
        this.speedy = speedy;
        this.sizex = sizex;
        this.sizey = sizey;
        this.projectileasset = projectileasset;
    }

    public Projectile shoot(double shipposx, double shipposy)
    {
        if (lastshot==0)
            return new Projectile(damage,shipposx+posx+shotposx,shipposy+posy+shotposy,speedx,speedy,sizex,sizey,projectileasset);
        return null;
    }
    public void reload(float delta)
    {
        if (lastshot!=0)
            lastshot = lastshot-delta<0?0:lastshot-delta;
    }

    public AtlasRegion getProjectileasset()
    {
        return projectileasset;
    }

    public void setProjectileasset(AtlasRegion projectileasset)
    {
        this.projectileasset = projectileasset;
    }

    public double getRotation()
    {
        return rotation;
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }

    public double getSwaying()
    {
        return swaying;
    }

    public void setSwaying(double swaying)
    {
        this.swaying = swaying;
    }

    public double getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(double acceleration)
    {
        this.acceleration = acceleration;
    }
}
