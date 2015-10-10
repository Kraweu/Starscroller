package at.kraweu.starscroller;

/**
 * Created by Kraweu on 15.09.2015.
 */
public class WeaponSlot
{
    private Weapon weapon = null;
    int posx;    //Center Position relative to the ship
    int posy;

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
        this.weapon.setSlot(this);
    }

    @Override
    public WeaponSlot clone()
    {
        WeaponSlot copy = new WeaponSlot(this.posx, this.posy);
        copy.weapon = this.weapon.clone(copy);
        return copy;
    }

    public static WeaponSlot[] clone(WeaponSlot[] slots)
    {
        WeaponSlot[] newslots = new WeaponSlot[slots.length];
        for (int i = 0; i < slots.length; i++)
        {
            newslots[i] = slots[i].clone();
        }
        return newslots;
    }
}
