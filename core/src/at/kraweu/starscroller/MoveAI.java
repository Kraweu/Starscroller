package at.kraweu.starscroller;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Kraweu on 23.11.2015.
 */
public class MoveAI
{
    private MovementInterface enemy;
    private boolean started = false;
    private boolean atPosition = false;
    private int movementrange = 5;

    Vector2 targetpos = new Vector2();

    public MoveAI(MovementInterface enemy)
    {
        this.enemy = enemy;
    }

    public void started()
    {
        started = true;
    }

    public void update(float delta)
    {
        if (started)
        {
            if (atPosition)
            {

            } else
            {

            }

        }
    }

    public void setTarget(float targetposx, float targetposy)
    {
        this.targetpos.set(targetposx, targetposy);
        atPosition = false;
    }
}

