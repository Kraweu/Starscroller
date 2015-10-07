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

    void setLeftMove(boolean leftMove);

    void setRightMove(boolean rightMove);

    void setUpMove(boolean upMove);

    void setDownMove(boolean downMove);

    double getSpeedx();

    double getSpeedy();

    double getSpeed();

    void setSpeed(double speed);

    double setBreakspeed();

    double getBreakspeed();

    double getPosx();

    double getPosy();

    void setSpeedx(double speedx);

    void setSpeedy(double speedy);

    void setPosx(double posx);

    void setPosy(double posy);

    MovementInterface getInterface(MovementInterface interf);

}
