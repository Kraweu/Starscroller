package at.kraweu.starscroller;

import at.kraweu.collisionDetection.Border;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import java.awt.*;

/**
 * Created by Alex on 19.05.2015.
 */
public class Projectile
{
    boolean deleted = false;

    double damage = 1;
    double posx;
    double posy;
    double speedx;
    double speedy;
    int sizex;
    int sizey;

    AtlasRegion asset=null;

    double acceleration = 0;
    double swaying = 0;
    double rotation = 0;

    public Projectile(double damage, double posx, double posy, double speedx, double speedy, int sizex, int sizey, AtlasRegion asset)
    {
        this.damage = damage;
        this.posx = posx;
        this.posy = posy;
        this.speedx = speedx;
        this.speedy = speedy;
        this.sizex = sizex;
        this.sizey = sizey;
        this.asset = asset;
    }

    public void movement(float delta)
    {
        if (deleted)
            return;
        if (Border.inside(new Point((int) posx, (int) posy), Starscroller.gamesize, sizex, sizey))
        {
            posx += speedx;
            posy += speedy;
        }
        else
        {
            deleted = true;
        }
    }

    public double getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(double acceleration)
    {
        this.acceleration = acceleration;
    }

    public double getSwaying()
    {
        return swaying;
    }

    public void setSwaying(double swaying)
    {
        this.swaying = swaying;
    }
    public double getRotation()
    {
        return rotation;
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
}
