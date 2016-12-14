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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
//        mainTable.setDebug(true);
        mainTable.add(label("Starscroller", Color.NAVY)).pad(120);
        mainTable.row();
        MenuButton buttonStart = new MenuButton("Start", skin, "Start new Game")
        {
            @Override
            public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
            {
                game.setScreen(new GameScreen(game, game.player, game.levels[0]));/*TODO levels*/
            }
        };
        mainTable.add(buttonStart.getbutton());
        mainTable.row();
        MenuButton buttonLevels = new MenuButton("Levels", skin, "Select a Specific Level")
        {
            @Override
            public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
            {
                game.setScreen(new LevelSelectionScreen(game));
            }
        };
        mainTable.add(buttonLevels.getbutton());
        mainTable.row();
        MenuButton buttonExit = new MenuButton("Exit", skin, "Close the Game")
        {
            @Override
            public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
            {
                Gdx.app.exit();
            }
        };
        mainTable.add(buttonExit.getbutton());
        mainTable.row().right().padRight(100);
        MenuButton buttonOptions = new MenuButton("Options", game.assets.getRegion("UI/flatDark21"), game.assets.getRegion("UI/shadedDark22"), skin, "Close the Game")
        {
            @Override
            public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
            {
                game.setScreen(new OptionsScreen(game));
            }
        };
        buttonOptions.getbutton().pad(20);
        mainTable.add(buttonOptions.getbutton());

        ui.addActor(mainTable);
        stage.addActor(ui);
        Gdx.input.setInputProcessor(stage);
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
}
