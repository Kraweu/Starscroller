package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class StartMenuScreen implements Screen
{
    private Background background;
    private Stage stage;
    private Skin skin;
    private Group ui;
    private Starscroller game;

    public StartMenuScreen(Starscroller starscroller)
    {
        game = starscroller;
    }


    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        ui = new Group();
        Table mainTable = new Table();
        mainTable.defaults().pad(30);
        mainTable.center();
        mainTable.top().padTop(50);
        mainTable.setFillParent(true);
//        mainTable.setDebug(true);
        mainTable.add(label("Starscroller", Color.NAVY)).pad(120);
        mainTable.row();
        ButtonStart buttonStart = new ButtonStart();
        mainTable.add(buttonStart.getbutton());
        mainTable.row();
        ButtonExit buttonExit = new ButtonExit();
        mainTable.add(buttonExit.getbutton());
        mainTable.row();
        ui.addActor(mainTable);
        stage.addActor(ui);
        Gdx.input.setInputProcessor(stage);
    }

    private class ButtonStart
    {
        TextButton buttonStart;

        public ButtonStart()
        {
            buttonStart = new TextButton("Start", skin);
            buttonStart.addListener(new ChangeListener()
            {
                public void changed(ChangeEvent event, Actor actor)
                {
                    System.out.println("Starting");
                    game.setScreen(new GameScreen(game, game.player, game.levels[0]));//TODO levels
                }
            });
            buttonStart.addListener(new TextTooltip("Start new Game", skin)
            {

            });
        }

        TextButton getbutton()
        {
            return buttonStart;
        }
    }

    private class ButtonExit
    {
        TextButton buttonExit;

        public ButtonExit()
        {
            buttonExit = new TextButton("Exit", skin);
            buttonExit.addListener(new ChangeListener()
            {
                public void changed(ChangeEvent event, Actor actor)
                {
                    System.out.println("Exiting");
                    Gdx.app.exit();
                }
            });
        }

        TextButton getbutton()
        {
            return buttonExit;
        }
    }

    private class ButtonOptions
    {
        ImageButton buttonOptions;

        public ButtonOptions()
        {
            buttonOptions = new ImageButton(new TextureRegionDrawable(game.assets.getRegion("UI/transparentDark20")), new TextureRegionDrawable(game.assets.getRegion("UI/shadedDark22")));
            buttonOptions.addListener(new ChangeListener()
            {
                public void changed(ChangeEvent event, Actor actor)
                {
                    System.out.println("Options");
                    game.setScreen(new OptionsScreen());
                }
            });
        }
    }

    /**
     * used to tidy up the label adding a bit for the how to play description
     */
    private Label label(String text, Color color)
    {
        Label label = new Label(text, skin);
        label.setAlignment(Align.center, Align.center);
        label.setColor(color);
        return label;
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        ui.setSize(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }
}
