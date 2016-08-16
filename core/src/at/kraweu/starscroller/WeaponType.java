package at.kraweu.starscroller;

/**
 * Created by Kraweu on 01.08.2015.
 */
public class WeaponType
{

    //Weapon properties

    String name = "unnamed";


    float reloadtime = 1;
    int shotposx; //Position relative to the Weapons left upper corner
    int shotposy;
    String asset = null;
    private float sizemult = 1;

    //Projectile properties

    private float damage = 1;
    private float speedx;
    private float speedy;
    private float acceleration = 1;
    private float swaying = 0;
    private float rotation = 0;
    String projectileasset = null;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        if (name != null)
            this.name = name;
        else
            this.name = "unnamed";
    }

    public String getAsset()
    {
        return asset;
    }

    public void setAsset(String asset)
    {
        this.asset = asset;
    }

    public float getShotposx()
    {
        return shotposx;
    }

    public void setShotposx(int shotposx)
    {
        this.shotposx = shotposx;
    }

    public float getShotposy()
    {
        return shotposy;
    }

    public void setShotposy(int shotposy)
    {
        this.shotposy = shotposy;
    }

    public float getReloadtime()
    {
        return reloadtime;
    }

    public void setReloadtime(float reloadtime)
    {
        this.reloadtime = reloadtime;
    }

    public String getProjectileasset()
    {
        return projectileasset;
    }

    public void setProjectileasset(String projectileasset)
    {
        this.projectileasset = projectileasset;
    }

    public float getRotation()
    {
        return rotation;
    }

    public void setRotation(float rotation)
    {
        this.rotation = rotation;
    }

    public float getSwaying()
    {
        return swaying;
    }

    public void setSwaying(float swaying)
    {
        this.swaying = swaying;
    }

    public float getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(float acceleration)
    {
        this.acceleration = acceleration;
    }

    public float getDamage()
    {
        return damage;
    }

    public void setDamage(float damage)
    {
        this.damage = damage;
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

    public float getSizemult()
    {
        return sizemult;
    }

    public void setSizemult(float sizemult)
    {
        this.sizemult = Math.abs(sizemult);
    }
}
