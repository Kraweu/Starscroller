package at.kraweu.starscroller;

/**
 * Created by Kraweu on 01.08.2015.
 */
public class WeaponType
{

    //Weapon properties

    String name = "unnamed";


    double reloadtime = 1;
    int shotposx; //Position relative to the Weapons left upper corner
    int shotposy;
    String asset = null;

    //Projectile properties

    private double damage = 1;
    private double speedx;
    private double speedy;
    private double acceleration = 0;
    private double swaying = 0;
    private double rotation = 0;
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

    public double getShotposx()
    {
        return shotposx;
    }

    public void setShotposx(int shotposx)
    {
        this.shotposx = shotposx;
    }

    public double getShotposy()
    {
        return shotposy;
    }

    public void setShotposy(int shotposy)
    {
        this.shotposy = shotposy;
    }

    public double getReloadtime()
    {
        return reloadtime;
    }

    public void setReloadtime(double reloadtime)
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

    public double getDamage()
    {
        return damage;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
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

}
