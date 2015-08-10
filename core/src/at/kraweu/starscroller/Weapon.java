package at.kraweu.starscroller;

/**
 * Created by Alex on 19.05.2015.
 */
public class Weapon
{
    double lastshot = 0;
    double posx;    //Position relative to the ship
    double posy;
    //todo Implement level and Damagemultiplier into damage calculation
    int level = 0;
    double damagemultiplier = 1;
    WeaponType type = null;

    public Weapon(WeaponType type, double posx, double posy)
    {
        this.type = type;
        this.posx = posx;
        this.posy = posy;
    }

    public Projectile shoot(double shipposx, double shipposy)
    {
        if (lastshot==0)
            return new Projectile(type.getDamage(), shipposx + posx + type.shotposx, shipposy + posy + type.shotposy, type.getSpeedx(), type.getSpeedy(), type.getSizex(), type.getSizey(), type.projectileasset);
        return null;
    }
    public void reload(float delta)
    {
        if (lastshot!=0)
            lastshot = lastshot-delta<0?0:lastshot-delta;
    }


}
