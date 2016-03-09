package at.kraweu.starscroller;

/**
 * Created by Kraweu on 15.09.2015.
 */
public class WeaponSlot
{
    private Weapon weapon = null;
    int posx;    //Center Position relative to the ship
    int posy;
    Ship ship;

    public WeaponSlot(int posx, int posy, Ship ship)
    {
        this.posx = posx;
        this.posy = posy;
        this.ship = ship;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
        this.weapon.setSlot(this);
    }

    public WeaponSlot myClone(Ship ship)
    {
        WeaponSlot copy = new WeaponSlot(this.posx, this.posy, ship);
        copy.weapon = this.weapon.clone(copy);
        return copy;
    }

//    Not Used
//    public static WeaponSlot[] myClone(WeaponSlot[] slots)
//    {
//        WeaponSlot[] newslots = new WeaponSlot[slots.length];
//        for (int i = 0; i < slots.length; i++)
//        {
//            newslots[i] = slots[i].myClone();
//        }
//        return newslots;
//    }
}
