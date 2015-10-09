package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Kraweu on 13.09.2015.
 */
public class Ship
{
    private String name = null;
    private double speed = 1;
    private double health = 100;
    private String asset = null;
    private MovementInterface owner = null;
    private WeaponSlot[] weaponSlots = null;
    private HashSet projectiles = new HashSet();

    @Override
    public Ship clone()//whithout Projectiles
    {
        Ship copy = new Ship();
        copy.speed = this.speed;
        copy.health = this.health;
        copy.weaponSlots = this.weaponSlots;
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

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getHealth()
    {
        return health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

    public String getAsset()
    {
        return asset;
    }

    public void setAsset(String asset)
    {
        this.asset = asset;
    }

    public WeaponSlot[] getWeaponSlots()
    {
        return weaponSlots;
    }

    public void setWeaponSlots(WeaponSlot[] weaponSlots)
    {
        this.weaponSlots = weaponSlots;
    }

    public HashSet getProjectiles()
    {
        return projectiles;
    }

    public void setProjectiles(HashSet projectiles)
    {
        this.projectiles = projectiles;
    }

    public Iterator getProjectilesit()
    {
        return projectiles.iterator();
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

    public void updateProjectiles()
    {
        //TODO
        Iterator iter = getProjectilesit();
        while (iter.hasNext())
        {
            iter.next();
        }
    }

    /**
     * Renders Ship and all Projectiles
     *
     * @param batch
     * @param assets
     */
    public void render(SpriteBatch batch, Assets assets)
    {
        batch.draw(assets.getRegion(getAsset()), (float) owner.getPosx(), (float) owner.getPosy());
        Iterator iter = projectiles.iterator();
        while (iter.hasNext())
        {
            Projectile proj = (Projectile) iter.next();
            TextureRegion textureRegion = assets.getRegion(proj.getAsset());
            batch.draw(textureRegion, (float) proj.getPosx(), (float) proj.getPosy(),
                    (float) proj.getPosx(), (float) proj.getPosy(),
                    textureRegion.getRegionWidth(), textureRegion.getRegionHeight(),
                    1, 1, (float) proj.getRotation());
        }
    }
}
