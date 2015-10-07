package at.kraweu.starscroller;


import at.kraweu.collisionDetection.Border;

/**
 * Created by Alex on 17.05.2015.
 */
public class Player implements MovementInterface
{
    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

    private boolean shoot;

    private int sizeshipx = 0;
    private int sizeshipy = 0;

    private double posx = (Starscroller.gamewidth / 2) - 100; //Zentriert
    private double posy = 100;

    private double speed = 1;

    private double speedx = 0;//HorizontalSpeed
    private double speedy = 0;//VerticalSpeed

    private double breakspeed = 1f;

    private Ship ship;


    public Player()
    {

    }

    public void setShip(Ship ship, Assets assets)
    {
        this.ship = ship;
        setSizeshipx(assets.getRegion(getShip().getAsset()).packedWidth);
        setSizeshipy(assets.getRegion(getShip().getAsset()).packedHeight);
    }

    public Ship getShip()
    {
        return ship;
    }
    public void setMoveLeft(boolean b)
    {
        if(rightMove && b)
            rightMove = false;
        leftMove = b;
    }
    public void setMoveRight(boolean b)
    {
        if(leftMove && b)
            leftMove = false;
        rightMove = b;
    }
    public void setMoveUp(boolean b)
    {
        if(downMove && b)
            downMove = false;
        upMove = b;
    }
    public void setMoveDown(boolean b)
    {
        if(upMove && b)
            upMove = false;
        downMove = b;
    }
    public void setShoot(boolean shoot)
    {
        this.shoot = shoot;
    }
    public void movement(float delta)
    {
//        System.out.println(delta);
        if (leftMove)
        {
            speedx-=speed*delta*150;
//            System.out.println(speedx + " * " + breakspeed + "  left " +leftMove + "   right " +rightMove);
        }
        else if (rightMove)
        {
            speedx+=speed*delta*150;
//            System.out.println(speedx + " * " + breakspeed + "  left " +leftMove + "   right " +rightMove);
//            System.out.println("break " + breakspeed + " / " + delta);
        }
        if (downMove)
            speedy-=speed*delta*150;
        else if (upMove)
            speedy+=speed*delta*150;

        //Bremsen

        if (speedx<0.2&&speedx>-0.2)
            speedx=0;
        else
        {
            speedx /= 1 + (breakspeed * 10) / ((Math.abs(speedx) / (Math.abs(speedx) * 0.8)) * 0.4) * delta;//Bremsgeschwindigkeit Abhängig von delta und speedx
        }
        if (speedy<0.2&&speedy>-0.2)
            speedy=0;
        else
            speedy/=1+(breakspeed*10)/((Math.abs(speedy)/(Math.abs(speedy)*0.8))*0.4)*delta;

        //Bewegung + Randkontrolle
        if (Border.left((int) (posx + speedx)))//ganzlinks
        {
            if (Border.right((int) (posx + speedx), Starscroller.gamesize.x, sizeshipx))//ganzrechts
            {
                posx += speedx;
                if (speedx != 0);
//                    System.out.println("processing movement " + speedx + "  " + posx);
            }
            else
            {
                posx = Starscroller.gamesize.x - sizeshipx;
                speedx=0;
            }
        }
        else
        {
            posx=0;
            speedx=0;
        }


        if (Border.up((int) (posy + speedy)))//if not ganzoben
        {
            if (Border.down((int) (posy + speedy), Starscroller.gamesize.y, sizeshipy))//if not ganzunten
            {
                posy += speedy;
                if (speedy != 0) ;
//                    System.out.println("processing movement " + speedy + "  " + posy);
            } else
            {
                posy = Starscroller.gamesize.y - sizeshipy;
                speedy = 0;
            }
        } else
        {
            posy = 0;
            speedy = 0;
        }


        return;
    }


    public void setSizeshipx(int sizeshipx)
    {
        this.sizeshipx = sizeshipx;
    }

    public void setSizeshipy(int sizeshipy)
    {
        this.sizeshipy = sizeshipy;
    }

    public void shoot(float delta)
    {
        if (shoot)
        {

        }
        ship.updateProjectiles();
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
    public MovementInterface getInterface(MovementInterface interf)
    {
        return this;
    }

}
