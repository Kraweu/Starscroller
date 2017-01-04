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
    public String nextLevel;
    public boolean isLabel = false;
    public int nextWave = 0;
    public boolean displayMessage = false;

    /**
     * Time in Seconds since level started
     */
    public double time = 0;
    public boolean started = false;

    private Skin skin;
    private boolean allspawned = false;
    private int score = 0;
    private boolean completed = false;

    public Message message;
    public Label scoreLabel;
    public Label nextLevelLabel;

    /**Called when level is Started*/
    public void prepare()
    {
        skin = new Skin();
        skin.addRegions(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));

        message = new Message("");
        scoreLabel = new Label("1", skin);
        scoreLabel.setAlignment(0, 1);
        nextLevelLabel = new Label("1", skin);
        nextLevelLabel.setAlignment(0, 1);

        background.addLayer("Backgrounds/sprstarsfarcropped", 7.1f);
        background.addLayer("Backgrounds/sprstarsnearcropped", 20.2f);
        started = true;
    }

    /**Called regularly to control the environment(enemySpawning, winCheck, MessageDisplay)*/
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
        scoreLabel.setText(Integer.toString(score));
        nextLevelLabel.setText(nextLevel);
    }

    public boolean getCompleted()
    {
        return completed;
    }



    public Label getMessageLabel()
    {
        return message.getLabel();
    }

    public Label getScoreLabel()
    {
        return scoreLabel;
    }

    public Label getNextLevelLabel()
    {
        return nextLevelLabel;
    }

    private class Message
    {
        float timeSinceStart;
        float timeToDisplay = 3.5f;
        Label label;

        public Message(String text)
        {
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

    /**
     * Returns Level with given name, if Level is not found null
     */
    public static Level getLevel(String name, Level[] levels)
    {
        for (int i = 0; i < levels.length; i++)
        {
            if (levels[i].name == name)
                return levels[i];
        }
        return null;
    }

}
