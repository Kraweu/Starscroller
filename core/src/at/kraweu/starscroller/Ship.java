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
    private double speed = 1;
    private double health = 100;
    private String asset = null;
    private MovementInterface owner = null;
    private WeaponSlot[] weaponSlots = null;
    private Set<Projectile> projectiles = new HashSet<Projectile>();

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
        batch.draw(assets.getRegion(getAsset()), (float) owner.getPosx(), (float) owner.getPosy());
        Iterator iter = projectiles.iterator();
        while (iter.hasNext())
        {
            Projectile proj = (Projectile) iter.next();
            TextureRegion textureRegion = assets.getRegion(proj.getAsset());
            batch.draw(textureRegion, (float) proj.getPosx() - (textureRegion.getRegionWidth() / 2), (float) proj.getPosy() - (textureRegion.getRegionHeight() / 2),
                    (float) proj.getPosx() - (textureRegion.getRegionWidth() / 2), (float) proj.getPosy() - (textureRegion.getRegionHeight() / 2),
                    textureRegion.getRegionWidth(), textureRegion.getRegionHeight(),
                    1, 1, (float) proj.getRotation());
        }
    }

    public void shoot(float delta)
    {
        for (int i = 0; i < weaponSlots.length; i++)
        {
            if (weaponSlots[i].getWeapon().nextshot == 0)
            {
                projectiles.add(weaponSlots[i].getWeapon().shoot(owner.getPosx(), owner.getPosy(), owner.getSizeshipx(), owner.getSizeshipy()));
            }

        }
    }

    public void updateProjectiles(float delta, Assets assets)
    {
        //TODO
        Iterator iter = getProjectilesit();
        while (iter.hasNext())
        {
            Projectile proj = (Projectile) iter.next();
            proj.movement(delta, assets);
            if (proj.deleted)
                iter.remove();
        }
        for (int i = 0; i < weaponSlots.length; i++)
        {
            weaponSlots[i].getWeapon().reload(delta);
        }
    }

}
