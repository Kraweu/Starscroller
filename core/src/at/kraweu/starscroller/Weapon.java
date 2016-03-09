package at.kraweu.starscroller;

/**
 * Created by Alex on 19.05.2015.
 */
public class Weapon
{
    private float nextShot = 0;

    String name = null;

    WeaponType type = null;
    WeaponSlot slot = null;

    //todo Implement level and Damagemultiplier into damage calculation
    int level = 0;
    float damagemultiplier = 1;

    public Weapon(WeaponType type, String name)
    {
        this.type = type;
        this.name = name;
    }

    public Projectile shoot(float shipposx, float shipposy, int sizeShipx, int sizeShipy)
    {
        if (nextShot == 0)
        {
            nextShot = type.getReloadtime();
//            System.out.println(type.getReloadtime());
//            System.out.println("dmg " + type.getDamage() + " shippos " + shipposx + "+" + slot.posx + "+" + type.shotposx + " " + shipposy + "+" + slot.posy + "+" + type.shotposy + " " + type.getSpeedx() + type.getSpeedy() + type.projectileasset);
            return new Projectile(type.getDamage(), shipposx + slot.posx + type.shotposx, shipposy + slot.posy + type.shotposy, type.getSpeedx(), type.getSpeedy(), type.getRotation() + slot.ship.getRotation(), type.projectileasset);
        }
        return null;
    }
    public void reload(float delta)
    {
        if (nextShot != 0)
        {
            nextShot = nextShot - delta < 0 ? 0 : nextShot - delta;
//            System.out.println(nextShot + " " + delta);
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public WeaponType getType()
    {
        return type;
    }

    public void setType(WeaponType type)
    {
        this.type = type;
    }

    public WeaponSlot getSlot()
    {
        return slot;
    }

    public void setSlot(WeaponSlot slot)
    {
        this.slot = slot;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public float getDamagemultiplier()
    {
        return damagemultiplier;
    }

    public void setDamagemultiplier(float damagemultiplier)
    {
        this.damagemultiplier = damagemultiplier;
    }

    public Weapon clone(WeaponSlot newslot)
    {
        Weapon copy = new Weapon(this.type, this.name);//type is not Copied (hopefully)
        copy.setDamagemultiplier(this.getDamagemultiplier());
        copy.setLevel(this.getLevel());
        copy.setSlot(newslot);
        return copy;
    }


    public float getNextShot()
    {
        return nextShot;
    }
}
