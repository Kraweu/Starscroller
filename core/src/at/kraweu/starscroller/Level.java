package at.kraweu.starscroller;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class Level
{
    public Enemies spawnedenemies = new Enemies(this);

    public Background background = new Background();

    public Wave[] waves;
    public String name;
    public int nextWave = 0;
    /**
     * Time in Seconds since level started
     */
    public double time = 0;
    public boolean started = false;
    private boolean allspawned = false;
    private boolean completed = false;

    /**
     * Called when level is Started
     */
    public void prepare()
    {
        background.addLayer("Backgrounds/sprstarsfarcropped", 7.1f);
        background.addLayer("Backgrounds/sprstarsnearcropped", 20.2f);
//        spawnedenemies.addEnemies(waves[0].enemies);
        started=true;
    }

    /**
     * Called regularly to control the environment(enemy movement, enemySpawning, winCheck)
     */
    public void update(float delta)
    {
        if (started)
        {
            time += delta;
        }
        //Wave Spawning
        if (time >= waves[nextWave].timeToStart && !allspawned)
        {
            waves[nextWave].start();
            spawnedenemies.addEnemies(waves[nextWave].enemies);
            if (!allspawned && nextWave + 1 < waves.length)
                nextWave++;
            else
                allspawned = true;

        }
        if (allspawned && spawnedenemies.isEmpty() && !completed)
        {
            completed = true;
            System.out.println("Level " + name + " completed");
        }
    }

}
