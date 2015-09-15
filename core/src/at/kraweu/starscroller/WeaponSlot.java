package at.kraweu.starscroller;

/**
 * Created by Kraweu on 15.09.2015.
 */
public class WeaponSlot
{
    Weapon weapon = null;
    double posx;    //Center Position relative to the ship
    double posy;

    public WeaponSlot(int posx, int posy)
    {
        this.posx = posx;
        this.posy = posy;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
}
