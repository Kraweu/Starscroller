package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Kraweu on 28.03.2016.
 */
public class OptionsScreen implements Screen
{

    private Background background;
    private Stage stage;
    private Skin skin;
    private Group ui;
    private Starscroller game;

    public OptionsScreen(Starscroller starscroller)
    {
        game = starscroller;
    }

    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        skin = new Skin();
        skin.addRegions(new TextureAtlas("packedImgs/Starscroller.pack"));
        skin.addRegions(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));
        ui = new Group();
        Table mainTable = new Table();
        mainTable.defaults().pad(30);
        mainTable.center();
        mainTable.top().padTop(50);
        mainTable.setFillParent(true);
        mainTable.add(label("Options", Color.NAVY)).pad(120);
        mainTable.row();
        mainTable.add(new ButtonBack().getbutton());
        ui.addActor(mainTable);
        stage.addActor(ui);
        Gdx.input.setInputProcessor(stage);
    }

    private class ButtonBack
    {
        TextButton buttonExit;

        public ButtonBack()
        {
            buttonExit = new TextButton("Back", skin);
            buttonExit.addListener(new ChangeListener()
            {
                public void changed(ChangeEvent event, Actor actor)
                {
                    System.out.println("Exiting Options");
                    game.setScreen(new StartMenuScreen(game));
                }
            });
        }

        TextButton getbutton()
        {
            return buttonExit;
        }
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
        dispose();
    }

    @Override
    public void dispose()
    {

    }

    /**
     * used to tidy up the label adding a bit
     */
    private Label label(String text, Color color)
    {
        Label label = new Label(text, skin);
        label.setAlignment(Align.center, Align.center);
        label.setColor(color);
        return label;
    }
}
