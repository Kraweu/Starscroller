package at.kraweu.starscroller;

/**
 * Created by Kraweu on 22.09.2015.
 */
public class Enemy implements MovementInterface
{
    static int idPosition = 0;

    private int id;

    private boolean leftMove = false;
    private boolean rightMove = false;
    private boolean downMove = false;
    private boolean upMove = false;


    /**
     * True if ship wants to shoot at the moment
     */
    private boolean shoot;

    private double speed = 1;

    private double speedx = 0;//HorizontalSpeed
    private double speedy = 0;//VerticalSpeed

    private double breakspeed = 1;

    private Ship ship;
    private boolean destroyed;
    private boolean beingdestroyed;
    private float destroytime;

    public Enemy()
    {
        id = idPosition;
        if (idPosition < Integer.MAX_VALUE)
            idPosition++;
        else
            idPosition = 0;
    }

    /**
     * Sets ship and Sizeship
     * Also sets owner in given ship
     *
     * @param ship
     * @param assets
     */
    public void setShip(Ship ship, Assets assets)
    {
        this.ship = ship;
        this.ship.setOwner(this);
        ship.setSizex(assets.getRegion(getShip().getAsset()).packedWidth);
        ship.setSizey(assets.getRegion(getShip().getAsset()).packedHeight);
    }

    public boolean getShoot()
    {
        return shoot;
    }

    public void setShoot(boolean shoot)
    {
        this.shoot = shoot;
    }

    public Ship getShip()
    {
        return ship;
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

    public int getId()
    {
        return id;
    }

    //Movement Interface
    @Override
    public boolean getLeftMove()
    {
        return leftMove;
    }

    @Override
    public boolean getRightMove()
    {
        return rightMove;
    }

    @Override
    public boolean getUpMove()
    {
        return upMove;
    }

    @Override
    public boolean getDownMove()
    {
        return downMove;
    }

    @Override
    public void setLeftMove(boolean leftMove)
    {
        this.leftMove = leftMove;
    }

    @Override
    public void setRightMove(boolean rightMove)
    {
        this.rightMove = rightMove;
    }

    @Override
    public void setUpMove(boolean upMove)
    {
        this.upMove = upMove;
    }

    @Override
    public void setDownMove(boolean downMove)
    {
        this.downMove = downMove;
    }

    @Override
    public double getSpeedx()
    {
        return speedx;
    }

    @Override
    public double getSpeedy()
    {
        return speedy;
    }

    @Override
    public void setSpeedx(double speedx)
    {
        this.speedx = speedx;
    }

    @Override
    public void setSpeedy(double speedy)
    {
        this.speedy = speedy;
    }

    @Override
    public double getSpeed()
    {
        return speed;
    }

    @Override
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    @Override
    public double setBreakspeed()
    {
        return 0;
    }

    @Override
    public double getBreakspeed()
    {
        return breakspeed;
    }

    @Override
    public MovementInterface getInterface(MovementInterface interf)
    {
        return this;
    }

    @Override
    public boolean isBeingdestroyed()
    {
        return beingdestroyed;
    }

    @Override
    public boolean isDestroyed()
    {
        return destroyed;
    }

    /**
     * Called if there is an Error while loading
     * Sets the Enemy to Destroyed
     */
    public void loadingError()
    {
        System.out.println("Error while loading an Enemy, set to destroyed");
        destroyed = true;
    }

    public void hit(Projectile proj)
    {
        ship.setHealth(ship.getHealth() - proj.damage);
        if (ship.getHealth() < 0)
        {
            destroyed();
        }
    }

    /**
     * Starts destruction
     */
    private void destroyed()
    {
        System.out.println("beingDestroyed");
        beingdestroyed = true;
    }

    public void update(float delta, Assets assets, Enemies enemies)
    {
        if (destroyed)
        {
            enemies.deleteEnemy(this);
            return;
        }
        if (beingdestroyed)
        {
            if (destroytime < 0.2)
            {
                destroytime += delta;
            } else
                destroyed = true;
        }
        Movement.movement(this, delta);
        ship.updateProjectiles(delta, assets);
    }
}
