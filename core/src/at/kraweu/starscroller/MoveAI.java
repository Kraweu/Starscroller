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
    public double curTime = 0;
    private int movementrange = 5;
    public Deque<Vector2> waypoints;
    public Deque<Double> time;
    private Vector2 targetpos;

    public MoveAI(MovementInterface enemy)
    {
        this.enemy = enemy;
        waypoints = new ArrayDeque<Vector2>();
        time = new ArrayDeque<Double>();
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
        float distancey = enemy.getPosy() - waypoints.peek().y;

        double timeRemaining = time.peek() - curTime;
//        System.out.println(distancex+"  "+ timeRemaining+"  "+timeRemaining/delta+"  "+(-distancex/(timeRemaining/delta)));
        if (timeRemaining > delta)
        {
            if (distancex < 0)
                enemy.setSpeedx((float) (-distancex / (timeRemaining / delta)));
            else
                enemy.setSpeedx((float) (-distancex / (timeRemaining / delta)));


//        System.out.println("Movement " +enemy.getShip().getName()+" Left: "+enemy.getLeftMove()+ " Right: ");


            if (distancey < 0)
                enemy.setSpeedy((float) (-distancey / (timeRemaining / delta)));
            else
                enemy.setSpeedy((float) (-distancey / (timeRemaining / delta)));
        }
        if ((Math.abs(distancex) < 1 && Math.abs(distancey) < 1) || timeRemaining < delta)
        {
            enemy.setSpeedx(0);
            enemy.setSpeedy(0);
            curTime = 0;
            waypoints.poll();
            time.poll();
            if (waypoints.isEmpty())
                atPosition=true;
        }
        curTime += delta;
    }

    public void setTarget(float targetposx, float targetposy)
    {
        this.targetpos.set(targetposx, targetposy);
        atPosition = false;
    }
}

