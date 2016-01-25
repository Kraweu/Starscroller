package at.kraweu.starscroller;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Kraweu on 23.11.2015.
 */
public class MoveAI
{
    /**
     * Enemy controlled by this AI
     */
    private MovementInterface enemy;
    private boolean started = false;
    public boolean atPosition = false;
    private int movementrange = 5;
    public Deque<Vector2> waypoints;
    private Vector2 targetpos;

    public MoveAI(MovementInterface enemy)
    {
        this.enemy = enemy;
        waypoints = new ArrayDeque<Vector2>();
    }

    public void start()
    {
        started = true;
        if (waypoints.size() > 1)
            waypoints.poll();
    }

    public void update(float delta)
    {
        if (started)
        {
            if (atPosition)
            {

            } else
            {
                movementtick(delta);
            }

        }
    }

    private void movementtick(float delta)
    {
        if (waypoints.isEmpty())
        {
            atPosition = true;
            return;
        }
        float distancex = enemy.getPosx() - waypoints.peek().x;
        if (enemy.getSpeedx() < Math.abs(distancex) / 10)
        {
            if (distancex < 0)
                enemy.setSpeedx(enemy.getSpeedx() + 0.8f);
            else
                enemy.setSpeedx(enemy.getSpeedx() - 0.8f);
        } else
        {

        }
//        System.out.println("Movement " +enemy.getShip().getName()+" Left: "+enemy.getLeftMove()+ " Right: ");
        float distancey = enemy.getPosy() - waypoints.peek().y;
        if (enemy.getSpeedy() < Math.abs(distancey) / 10)
        {
            if (distancey < 0)
                enemy.setSpeedy(enemy.getSpeedy() + 0.8f);
            else
                enemy.setSpeedy(enemy.getSpeedy() - 0.8f);
        } else
        {

        }
        if (Math.abs(distancex) < 1 && Math.abs(distancey) < 1)
        {
            waypoints.poll();
            if (waypoints.isEmpty())
                atPosition=true;
        }
    }

    public void setTarget(float targetposx, float targetposy)
    {
        this.targetpos.set(targetposx, targetposy);
        atPosition = false;
    }
}

