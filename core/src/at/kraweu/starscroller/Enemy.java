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

    private float speedx = 0;//HorizontalSpeed
    private float speedy = 0;//VerticalSpeed


    private Enemies enemies;
    /**
     * Ship designated to this Enemy
     */
    private Ship ship;
    public MoveAI moveAI = new MoveAI(this);

    private boolean destroyed;
    private boolean beingdestroyed;
    private float destroytime;

    public Enemy()
    {
        this(null);
    }

    public Enemy(Enemies enemies)
    {
        this.enemies = enemies;
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
    public float getSpeedx()
    {
        return speedx;
    }

    @Override
    public float getSpeedy()
    {
        return speedy;
    }

    @Override
    public void setSpeedx(float speedx)
    {
        this.speedx = speedx;
    }

    @Override
    public void setSpeedy(float speedy)
    {
        this.speedy = speedy;
    }

    @Override
    public float getSpeed()
    {
        return ship.getSpeed();
    }

    @Override
    public void setSpeed(float speed)
    {
        ship.setSpeed(speed);
    }

    @Override
    public void setBreakspeed(float breakspeed)
    {
        ship.setBreakspeed(breakspeed);
    }

    @Override
    public float getBreakspeed()
    {
        return ship.getBreakspeed();
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

    @Override
    public float getPosx()
    {
        return ship.getPosx();
    }

    @Override
    public float getPosy()
    {
        return ship.getPosy();
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
//        Movement.movement(this, delta);
        if (!moveAI.atPosition)
            moveAI.update(delta);
        Movement.movementNoCollision(this,delta);
        ship.updateProjectiles(delta, assets);
    }
}
