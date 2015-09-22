package at.kraweu.starscroller;

/**
 * Created by Kraweu on 22.09.2015.
 */
public class Enemy implements MovementInterface
{
    private boolean shoot;

    private int sizeshipx = 0;
    private int sizeshipy = 0;

    private double posx = (Starscroller.gamewidth / 2) - 100; //Zentriert
    private double posy = 100;

    private float speed = 1;

    private float speedx = 0;//HorizontalSpeed
    private float speedy = 0;//VerticalSpeed

    private float breakspeed = 1f;

    private Ship ship;

    public void setShip(Ship ship, Assets assets)
    {
        this.ship = ship;
        setSizeshipx(assets.getRegion(getShip().getAsset()).packedWidth);
        setSizeshipy(assets.getRegion(getShip().getAsset()).packedHeight);
    }

    public boolean isShoot()
    {
        return shoot;
    }

    public void setShoot(boolean shoot)
    {
        this.shoot = shoot;
    }

    public int getSizeshipx()
    {
        return sizeshipx;
    }

    public void setSizeshipx(int sizeshipx)
    {
        this.sizeshipx = sizeshipx;
    }

    public int getSizeshipy()
    {
        return sizeshipy;
    }

    public void setSizeshipy(int sizeshipy)
    {
        this.sizeshipy = sizeshipy;
    }

    public double getPosx()
    {
        return posx;
    }

    public void setPosx(double posx)
    {
        this.posx = posx;
    }

    public double getPosy()
    {
        return posy;
    }

    public void setPosy(double posy)
    {
        this.posy = posy;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public float getSpeedx()
    {
        return speedx;
    }

    public void setSpeedx(float speedx)
    {
        this.speedx = speedx;
    }

    public float getSpeedy()
    {
        return speedy;
    }

    public void setSpeedy(float speedy)
    {
        this.speedy = speedy;
    }

    public float getBreakspeed()
    {
        return breakspeed;
    }

    public void setBreakspeed(float breakspeed)
    {
        this.breakspeed = breakspeed;
    }

    public Ship getShip()
    {
        return ship;
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
    }
}
