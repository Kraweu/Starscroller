package at.kraweu.starscroller;

import at.kraweu.collisionDetection.Border;

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

    String asset = null;

    double acceleration = 0;
    double swaying = 0;
    double rotation = 0;

    public Projectile(double damage, double posx, double posy, double speedx, double speedy, String asset)
    {
        this.damage = damage;
        this.posx = posx;
        this.posy = posy;
        this.speedx = speedx;
        this.speedy = speedy;
        this.asset = asset;
    }

    public void movement(float delta, Assets assets)
    {
        if (deleted)
            return;
        if (Border.inside(new Point((int) posx, (int) posy), Starscroller.gamesize, assets.getRegion(asset).packedWidth, assets.getRegion(asset).packedHeight))
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

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    public double getPosx()
    {
        return posx;
    }

    public void setPosx(double posx)
    {
        this.posx = posx;
    }

    public double getPosy()
    {
        return posy;
    }

    public void setPosy(double posy)
    {
        this.posy = posy;
    }

    public double getSpeedx()
    {
        return speedx;
    }

    public void setSpeedx(double speedx)
    {
        this.speedx = speedx;
    }

    public double getSpeedy()
    {
        return speedy;
    }

    public void setSpeedy(double speedy)
    {
        this.speedy = speedy;
    }


    public String getAsset()
    {
        return asset;
    }

    public void setAsset(String asset)
    {
        this.asset = asset;
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
