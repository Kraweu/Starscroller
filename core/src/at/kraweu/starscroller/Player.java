package at.kraweu.starscroller;


import at.kraweu.collisionDetection.Border;

/**
 * Created by Alex on 17.05.2015.
 */
public class Player
{
    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

    private boolean shoot;

    private int sizeshipx=99;
    private int sizeshipy=75;

    private double posx = (Starscroller.gamewidth/2) - sizeshipx; //Zentriert
    private double posy = 100;

    private float speed=1;

    private float speedx=0;//HorizontalSpeed
    private float speedy=0;//VerticalSpeed
    
    private float breakspeed = 1f;

    private Ship ship;


    public Player()
    {

    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
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
        if (!Border.left((int) (posx + speedx), Starscroller.gamesize.x, sizeshipx))//ganzlinks
        {
            if (!Border.right((int) (posx + speedx), Starscroller.gamesize.x, sizeshipx))//ganzrechts
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


        if (!Border.left((int) (posy + speedy), Starscroller.gamesize.y, sizeshipy))//if not ganzlinks
        {
            if (!Border.right((int) (posy + speedy), Starscroller.gamesize.y, sizeshipy))//if not ganzrechts
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

    public int getPosx()
    {
        return (int)posx;
    }

    public void setPosx(float posx)
    {
        this.posx = posx;
    }

    public int getPosy()
    {
        return (int)posy;
    }

    public void setPosy(float posy)
    {
        this.posy = posy;
    }


    public void shoot(float delta)
    {
        if (shoot)
        {

        }

    }
}
