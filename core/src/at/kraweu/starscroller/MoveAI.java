package at.kraweu.starscroller;

/**
 * Created by Kraweu on 23.11.2015.
 */
public class MoveAI
{
    private MovementInterface enemy;
    private boolean started = false;
    private boolean atPosition = false;
    private int movementrange = 5;

    public MoveAI(MovementInterface enemy)
    {
        this.enemy = enemy;
    }

    public void started()
    {

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
}
