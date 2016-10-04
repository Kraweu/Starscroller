package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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
    private boolean displayMessage = false;
    private Message message = new Message("");
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
            message = new Message("Level " + name + " completed");
            completed = true;
            System.out.println("Level " + name + " completed");
        }
        if (displayMessage)
        {
            displayMessage = message.displayMessage(delta);
        }

    }

    private class Message
    {
        float timeSinceStart;
        float timeToDisplay = 1.5f;
        Label label;
        Skin skin;

        public Message(String text)
        {
            skin = new Skin();
            skin.load(Gdx.files.internal("uiskin.json"));
            label = new Label(text, skin);
        }

        private void startDisplayMessage(String text)
        {
            displayMessage = true;

        }

        private boolean displayMessage(float delta)
        {
            timeSinceStart += delta;
            if (timeSinceStart > timeToDisplay)
                return false;
            //todo: fading?
            //and let it being Displayed in gamescreen render
            return true;
        }
    }
}
