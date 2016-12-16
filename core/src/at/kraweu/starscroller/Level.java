package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    public boolean displayMessage = false;

    /**
     * Time in Seconds since level started
     */
    public double time = 0;
    public boolean started = false;
    private boolean allspawned = false;
    private boolean completed = false;
    public Message message = new Message("");

    /**Called when level is Started*/
    public void prepare()
    {
        background.addLayer("Backgrounds/sprstarsfarcropped", 7.1f);
        background.addLayer("Backgrounds/sprstarsnearcropped", 20.2f);
        started = true;
    }

    /**Called regularly to control the environment(enemy movement, enemySpawning, winCheck)*/
    public void update(float delta)
    {
        //Time Taking
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
        //Level Complete
        if (allspawned && spawnedenemies.isEmpty() && !completed)
        {
            completed();
            System.out.println("Level " + name + " completed");
        }
        //Message Update
        if (displayMessage)
        {
            displayMessage = message.updateMessage(delta);
            message.label.setVisible(true);
        } else
            message.label.setVisible(false);

    }

    public void completed()
    {
        completed = true;
        message.startDisplayMessage("Level " + name + " completed");
    }

    public Label getMessageLabel()
    {
        return message.getLabel();
    }

    private class Message
    {
        float timeSinceStart;
        float timeToDisplay = 3.5f;
        Label label;
        Skin skin;

        public Message(String text)
        {
            skin = new Skin();
            skin.addRegions(new TextureAtlas("uiskin.atlas"));
            skin.load(Gdx.files.internal("uiskin.json"));
            label = new Label(text, skin);
            label.setWrap(true);
            label.setAlignment(0,1);
        }

        private void startDisplayMessage(String text)
        {
            displayMessage = true;
            timeSinceStart = 0;
            label.setText(text);
        }

        private boolean updateMessage(float delta)
        {
            timeSinceStart += delta;
            if (timeSinceStart > timeToDisplay)
                return false;
            //todo: fading?
            //and let it being Displayed in gamescreen render

            return true;
        }

        private Label getLabel()
        {
            return label;
        }
    }
}
