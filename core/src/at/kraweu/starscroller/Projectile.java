package at.kraweu.starscroller;

import at.kraweu.collisionDetection.Border;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;


/**
 * Created by Alex on 19.05.2015.
 */
public class Projectile
{
    boolean deleted = false;

    float damage = 1;
    float posx;
    float posy;
    float speedx;
    float speedy;

    String asset = null;

    float acceleration = 1;
    float swaying = 0;
    float rotation = 0;
    float sizemult = 1;

    public Projectile(float damage, float posx, float posy, float speedx, float speedy, float rotation, float acceleration, float sizemult, String asset)
    {
        this.damage = damage;
        this.posx = posx;
        this.posy = posy;
        this.rotation = rotation;
        this.acceleration = acceleration;
        this.sizemult = sizemult;
        this.asset = asset;

        //Speed Vector Rotation
        double radians = rotation * (Math.PI / 180);

        this.speedx = (float) (Math.cos(radians) * speedx - Math.sin(radians) * speedy);
        this.speedy = (float) (Math.sin(radians) * speedx + Math.cos(radians) * speedy);
    }

    public void movement(float delta, Assets assets)
    {
        if (deleted)
            return;
        if (Border.inside(new Point((int) posx - assets.getRegion(asset).packedWidth, (int) posy - assets.getRegion(asset).packedHeight), Starscroller.gamesize, assets.getRegion(asset).packedWidth, assets.getRegion(asset).packedHeight))
        {
            posx += speedx * delta;
            posy += speedy * delta;
            speedx *= Math.pow(acceleration, delta);
            speedy *= Math.pow(acceleration, delta);
        }
        else
        {
            deleted = true;
        }
    }

    public float getAcceleration()
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

    public float getDamage()
    {
        return damage;
    }

    public void setDamage(float damage)
    {
        this.damage = damage;
    }

    public float getPosx()
    {
        return posx;
    }

    public void setPosx(float posx)
    {
        this.posx = posx;
    }

    public float getPosy()
    {
        return posy;
    }

    public void setPosy(float posy)
    {
        this.posy = posy;
    }

    public float getSpeedx()
    {
        return speedx;
    }

    public void setSpeedx(float speedx)
    {
        this.speedx = speedx;
    }

    public float getSpeedy()
    {
        return speedy;
    }

    public void setSpeedy(float speedy)
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

    public void setAcceleration(float acceleration)
    {
        this.acceleration = acceleration;
    }

    public float getSwaying()
    {
        return swaying;
    }

    public void setSwaying(float swaying)
    {
        this.swaying = swaying;
    }

    public float getRotation()
    {
        return rotation;
    }

    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    public float getSizemult()
    {
        return sizemult;
    }

    public void setSizemult(float sizemult)
    {
        this.sizemult = sizemult;
    }

    public boolean detectCollision(Ship ship, Ship owner)
    {
        if (ship.getOwner().isBeingdestroyed())
            return false;
        Rectangle projrect = new Rectangle((int) posx, (int) posy, 1, 1);
        Rectangle enemyrect = new Rectangle(ship.getPosxint(), ship.getPosyint(), ship.getSizex(), ship.getSizey());
        if (Intersector.overlaps(projrect, enemyrect))
        {
            ship.hit(this);
//            System.out.println("Hit");
            return true;
        } else
            return false;
    }
}
