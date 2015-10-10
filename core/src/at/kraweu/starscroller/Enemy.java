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

    private int sizeshipx = 0;
    private int sizeshipy = 0;

    private double posx = (Starscroller.gamewidth / 2) - 100; //Zentriert
    private double posy = 100;

    private double speed = 1;

    private double speedx = 0;//HorizontalSpeed
    private double speedy = 0;//VerticalSpeed

    private double breakspeed = 1;

    private Ship ship;

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
        setSizeshipx(assets.getRegion(getShip().getAsset()).packedWidth);
        setSizeshipy(assets.getRegion(getShip().getAsset()).packedHeight);
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
    public double getPosx()
    {
        return posx;
    }

    @Override
    public double getPosy()
    {
        return posy;
    }

    @Override
    public void setPosx(double posx)
    {
        this.posx = posx;
    }

    @Override
    public void setPosy(double posy)
    {
        this.posy = posy;
    }

    @Override
    public int getSizeshipx()
    {
        return sizeshipx;
    }

    @Override
    public void setSizeshipx(int size)
    {
        this.sizeshipx = size;
    }

    @Override
    public int getSizeshipy()
    {
        return sizeshipy;
    }

    @Override
    public void setSizeshipy(int size)
    {
        this.sizeshipy = size;
    }

    @Override
    public MovementInterface getInterface(MovementInterface interf)
    {
        return this;
    }


    public void update(float delta)
    {
        Movement.movement(this, delta);
    }
}
