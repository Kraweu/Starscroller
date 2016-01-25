package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Kraweu on 13.09.2015.
 */
public class Ship
{
    private String name = null;
    private float speed = 1;
    private float breakspeed = 1;
    private float health = 100;
    private String asset = null;
    private MovementInterface owner = null;
    private WeaponSlot[] weaponSlots = null;
    private Set<Projectile> projectiles = new HashSet<Projectile>();
    /**
     * Width
     */
    private int sizex = 1;
    /**
     * Height
     */
    private int sizey = 1;
    private float posx = 50;
    private float posy = 50;

    private int rotation = 0;

    public Ship myClone()//whithout Projectiles
    {
        Ship copy = new Ship();
        copy.speed = this.speed;
        copy.health = this.health;
        copy.weaponSlots = new WeaponSlot[this.weaponSlots.length];
        for (int i = 0; i < weaponSlots.length; i++)
        {
            copy.weaponSlots[i] = this.weaponSlots[i].clone();
        }
        copy.asset = this.asset;

        return copy;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getBreakspeed()
    {
        return breakspeed;
    }

    public void setBreakspeed(float breakspeed)
    {
        this.breakspeed = breakspeed;
    }

    public float getHealth()
    {
        return health;
    }

    public void setHealth(float health)
    {
        this.health = health;
    }

    public String getAsset()
    {
        return asset;
    }

    public void setAsset(String asset, int sizex, int sizey)
    {
        this.asset = asset;
        this.sizex = sizex;
        this.sizey = sizey;
    }

    public WeaponSlot[] getWeaponSlots()
    {
        return weaponSlots;
    }

    public void setWeaponSlots(WeaponSlot[] weaponSlots)
    {
        this.weaponSlots = weaponSlots;
    }

    public Set<Projectile> getProjectiles()
    {
        return projectiles;
    }

    public void setProjectiles(Set projectiles)
    {
        this.projectiles = projectiles;
    }

    public Iterator getProjectilesit()
    {
        return projectiles.iterator();
    }

    public int getSizex()
    {
        return sizex;
    }

    public void setSizex(int sizex)
    {
        this.sizex = sizex;
    }

    public int getSizey()
    {
        return sizey;
    }

    public void setSizey(int sizey)
    {
        this.sizey = sizey;
    }

    public float getPosx()
    {
        return posx;
    }

    public int getPosxint()
    {
        return (int) posx;
    }

    public void setPosx(float posx)
    {
        this.posx = posx;
    }

    public float getPosy()
    {
        return posy;
    }

    public int getPosyint()
    {
        return (int) posy;
    }

    public void setPosy(float posy)
    {
        this.posy = posy;
    }

    public MovementInterface getOwner()
    {
        return owner;
    }

    /**
     * Should only be called from the owner
     *
     * @param owner
     */
    public void setOwner(MovementInterface owner)
    {
        this.owner = owner;
    }


    /**
     * Renders Ship and all Projectiles
     *
     * @param batch
     * @param assets
     */
    public void render(SpriteBatch batch, Assets assets)
    {
        //Projectiles
        Iterator iter = projectiles.iterator();
        while (iter.hasNext())
        {
            Projectile proj = (Projectile) iter.next();
            TextureRegion textureRegion = assets.getRegion(proj.getAsset());
            batch.draw(textureRegion, (float) proj.getPosx() - (textureRegion.getRegionWidth() / 2), (float) proj.getPosy() - (textureRegion.getRegionHeight() / 2),
                    (float) (textureRegion.getRegionWidth() / 2), (float) (textureRegion.getRegionHeight() / 2),
                    textureRegion.getRegionWidth(), textureRegion.getRegionHeight(),
                    1, 1, (float) proj.getRotation());
        }
        //Weapons
        for (int i = 0; i < weaponSlots.length; i++)
        {
            TextureRegion textureRegion = assets.getRegion(weaponSlots[i].getWeapon().getType().getAsset());
            batch.draw(textureRegion, (float) (weaponSlots[i].posx + getPosx()), (float) (weaponSlots[i].posy + getPosy()));
        }
        //Ship
        batch.draw(assets.getRegion(getAsset()), (float) getPosx(), (float) getPosy(), getSizex() / 2, getSizey() / 2, getSizex(), getSizey(), 1, 1, getRotation());

    }

    public void shoot(float delta)
    {
        for (int i = 0; i < weaponSlots.length; i++)
        {
            if (weaponSlots[i].getWeapon().getNextShot() == 0)
            {
                projectiles.add(weaponSlots[i].getWeapon().shoot(getPosx(), getPosy(), getSizex(), getSizey()));
            }

        }
    }

    public void updateProjectiles(float delta, Assets assets)
    {
        Iterator iter = getProjectilesit();
        while (iter.hasNext())
        {
            Projectile proj = (Projectile) iter.next();
            proj.movement(delta, assets);
            //TODO facilitate level selection
            Iterator<Enemy> enemiesiter = Starscroller.getGame().levels[0].spawnedenemies.getIterator();
            boolean hit = false;
            while (enemiesiter.hasNext() && !hit)
            {
                if (proj.detectCollision(enemiesiter.next().getShip()))
                {
                    hit = true;
                    proj.setDeleted(true);
                }
            }
            if (proj.deleted)
                iter.remove();
        }
        for (int i = 0; i < weaponSlots.length; i++)
        {
            weaponSlots[i].getWeapon().reload(delta);
        }
    }

    public static Ship getShipFromArray(Ship[] ships, String name)
    {
        for (int i = 0; i < ships.length; i++)
        {
            if (ships[i].name.equals(name))
                return ships[i];
        }
        System.out.println("Ship not found: " + name);
        return null;
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }
}
