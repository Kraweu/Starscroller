package at.kraweu.starscroller;

/**
 * Created by Kraweu on 04.11.2015.
 * A Wave of Enemies to Spawn
 */
public class Wave
{
    public Enemies enemies;
    public Level level;
    public int Nr;
    public int timeToStart = 0;

    public Wave(Level level, int timeToStart)
    {
        this.level = level;
        this.timeToStart = timeToStart;
        enemies = new Enemies(level);
    }

    public void started()
    {
        enemies.started();
    }
}
