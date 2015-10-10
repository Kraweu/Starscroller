package at.kraweu.starscroller;

import at.kraweu.collisionDetection.Border;

/**
 * Created by Kraweu on 10.10.2015.
 */
public class Movement
{
    static int speedmult = 150;

    public static void move(MovementInterface obj, float delta)
    {

    }
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

        //Bremsen

        if (obj.getSpeedx() < 0.2 && obj.getSpeedx() > -0.2)
            obj.setSpeedx(0);
        else
        {
            obj.setSpeedx((obj.getSpeedx() * (0.8 + (0.2 * (1 - delta * 50)))));
        }
        if (obj.getSpeedy() < 0.2 && obj.getSpeedy() > -0.2)
            obj.setSpeedy(0);
        else
            obj.setSpeedy((obj.getSpeedy() * (0.8 + (0.2 * (1 - delta * 50)))));


//        //Breaking
//
//        if (obj.getSpeedx() < 0.2 && obj.getSpeedx() > -0.2)
//            obj.setSpeedx(0);
//        else
//        {
//            obj.setSpeedx((obj.getSpeedx()/(obj.getSpeedx()*)) * delta);//Bremsgeschwindigkeit Abhängig von delta und speedx);
//        }
//        if (obj.getSpeedy() < 0.2 && obj.getSpeedy() > -0.2)
//            obj.setSpeedy(0);
//        else
//            obj.setSpeedy((obj.getSpeedy()/(obj.getSpeedy()/speedmult)) * delta);

        //Bewegung + Randkontrolle
        if (Border.left((int) (obj.getPosx() + obj.getSpeedx())))//ganzlinks
        {
            if (Border.right((int) (obj.getPosx() + obj.getSpeedx()), Starscroller.gamesize.x, obj.getSizeshipx()))//ganzrechts
            {
                obj.setPosx(obj.getPosx() + obj.getSpeedx());
                if (obj.getSpeedx() != 0) ;
//                    System.out.println("processing movement " + speedx + "  " + posx);
            } else
            {
                obj.setPosx(Starscroller.gamesize.x - obj.getSizeshipx());
                obj.setSpeedx(0);
            }
        } else
        {
            obj.setPosx(0);
            obj.setSpeedx(0);
        }


        if (Border.up((int) (obj.getPosy() + obj.getSpeedy())))//if not ganzoben
        {
            if (Border.down((int) (obj.getPosy() + obj.getSpeedy()), Starscroller.gamesize.y, obj.getSizeshipy()))//if not ganzunten
            {
                obj.setPosy(obj.getPosy() + obj.getSpeedy());
                if (obj.getSpeedy() != 0) ;
//                    System.out.println("processing movement " + speedy + "  " + posy);
            } else
            {
                obj.setPosy(Starscroller.gamesize.y - obj.getSizeshipy());
                obj.setSpeedy(0);
            }
        } else
        {
            obj.setPosy(0);
            obj.setSpeedy(0);
        }


        return;
    }
}
