package at.kraweu.starscroller;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class Level
{
    public Enemies spawnedenemies = new Enemies();

    public Background background = new Background();

    public Wave[] waves;

    /**
     * Called when level is Started
     */
    public void prepare()
    {
        background.setAsset("Backgrounds/SpaceBackground3cut");
        spawnedenemies.addEnemies(waves[0].enemies);
    }

    /**
     * Called regulary to controll the environment(enemy movement, ememyspawning, wincheck)
     */
    public void update(float delta)
    {

    }

}
