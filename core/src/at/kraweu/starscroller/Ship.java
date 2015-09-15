package at.kraweu.starscroller;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Kraweu on 13.09.2015.
 */
public class Ship
{
    private double speed = 1;
    private double health = 100;
    private String asset = null;
    private Weapon[] weapons = null;
    private HashSet projectiles = new HashSet();
    private Iterator projectilesit = projectiles.iterator();

    @Override
    public Ship clone()
    {
        //TODO
        Ship copy = new Ship();
        copy.speed = this.speed;
        copy.health = this.health;
        copy.weapons = this.weapons;
        return copy;
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

    public Weapon[] getWeapons()
    {
        return weapons;
    }

    public void setWeapons(Weapon[] weapons)
    {
        this.weapons = weapons;
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
        return projectilesit;
    }

    public void setProjectilesit(Iterator projectilesit)
    {
        this.projectilesit = projectilesit;
    }
}
