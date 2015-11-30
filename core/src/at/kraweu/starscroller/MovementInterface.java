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

    float getSpeedx();

    float getSpeedy();

    float getSpeed();

    void setSpeed(float speed);

    float setBreakspeed();

    float getBreakspeed();

    void setSpeedx(float speedx);

    void setSpeedy(float speedy);

    float getPosx();

    float getPosy();

    void hit(Projectile proj);

    Ship getShip();
    
    MovementInterface getInterface(MovementInterface interf);

    boolean isBeingdestroyed();

    boolean isDestroyed();
}
