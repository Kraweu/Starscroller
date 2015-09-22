package at.kraweu.starscroller;

/**
 * Created by Kraweu on 22.09.2015.
 */
public interface MovementInterface
{
    boolean getLeftMove();

    boolean getRightMove();

    boolean getUpMove();

    boolean getDownMove();

    double getspeedx();

    double getspeedy();

    double getSpeed();

    double getBreakspeed();

    double getPosx();

    double getPosy();

    void setspeedx(double speedx);

    void setspeedy(double speedy);

    void setPosx(double posx);

    void setPosy(double posy);


}
