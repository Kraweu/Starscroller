package at.kraweu.starscroller;

import at.kraweu.collisionDetection.Border;

/**
 * Created by Kraweu on 10.10.2015.
 */
public class Movement
{
    static int speedmult = 150;

    public static void movement(MovementInterface obj, float delta)
    {

        //Acceleration

        if (obj.getLeftMove())
        {
            obj.setSpeedx(obj.getSpeedx() - obj.getSpeed() * delta * speedmult);

//            System.out.println(speedx + " * " + breakspeed + "  left " +leftMove + "   right " +rightMove);
        } else if (obj.getRightMove())
        {
            obj.setSpeedx(obj.getSpeedx() + obj.getSpeed() * delta * speedmult);

//            System.out.println(speedx + " * " + breakspeed + "  left " +leftMove + "   right " +rightMove);
//            System.out.println("break " + breakspeed + " / " + delta);
        }
        if (obj.getDownMove())
            obj.setSpeedy(obj.getSpeedy() - obj.getSpeed() * delta * speedmult);
        else if (obj.getUpMove())
            obj.setSpeedy(obj.getSpeedy() + obj.getSpeed() * delta * speedmult);

        //Breaking

        if (obj.getSpeedx() < 0.2 && obj.getSpeedx() > -0.2)
            obj.setSpeedx(0);
        else
        {
            speedx /= 1 + (breakspeed * 10) / ((Math.abs(speedx) / (Math.abs(speedx) * 0.8)) * 0.4) * delta;//Bremsgeschwindigkeit Abhängig von delta und speedx
        }
        if (speedy < 0.2 && speedy > -0.2)
            speedy = 0;
        else
            speedy /= 1 + (breakspeed * 10) / ((Math.abs(speedy) / (Math.abs(speedy) * 0.8)) * 0.4) * delta;

        //Bewegung + Randkontrolle
        if (Border.left((int) (posx + speedx)))//ganzlinks
        {
            if (Border.right((int) (posx + speedx), Starscroller.gamesize.x, sizeshipx))//ganzrechts
            {
                posx += speedx;
                if (speedx != 0) ;
//                    System.out.println("processing movement " + speedx + "  " + posx);
            } else
            {
                posx = Starscroller.gamesize.x - sizeshipx;
                speedx = 0;
            }
        } else
        {
            posx = 0;
            speedx = 0;
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
}
