package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class GameScreen implements Screen
{
    private Starscroller game;
    private Player player;
    private Level level;
    private Skin skin;
    private InputMultiplexer inputMultiplexer;
    private Stage stage;
    private Group ui;
    private Table levelCompleteTable;
    private Table pauseTable;
    private Table menuTable;

    /**
     * If True Game will be Paused
     */
    boolean pause = false;
    private SpriteBatch batch;

    public Level getLevel()
    {
        return level;
    }

    public GameScreen(Starscroller game, Player player, Level level)
    {
        this.game = game;

        this.player = player;

        this.level = level;
        this.level.prepare();

        batch = new SpriteBatch();

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show()
    {
        skin = new Skin();
        skin.addRegions(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));

        stage = new Stage(new ScreenViewport());

        inputMultiplexer.addProcessor(game.input);
        inputMultiplexer.addProcessor(stage);

        ui = new Group();

        levelCompleteTable = new Table();
        levelCompleteTable.defaults().pad(30).expandX().fillX();
        levelCompleteTable.top().padTop(50);
        levelCompleteTable.setFillParent(true);
        levelCompleteTable.padLeft(10).padRight(10);
        levelCompleteTable.add(level.getMessageLabel());
        levelCompleteTable.row();
        levelCompleteTable.add(level.getScoreLabel());
        levelCompleteTable.row();
        levelCompleteTable.add(level.getNextLevelLabel());
        levelCompleteTable.setVisible(false);

        ui.addActor(levelCompleteTable);

        pauseTable = new Table();
        pauseTable.defaults().pad(30).expandX().fillX();
        pauseTable.top().padTop(50).center();
        pauseTable.setFillParent(true);
        pauseTable.add(new Label("Pause", skin));
        pauseTable.row();
        pauseTable.add(level.getScoreLabel());
        pauseTable.row();

        ui.addActor(pauseTable);

        menuTable = new Table();
        menuTable.setFillParent(true);
        MenuButton menuButton = new MenuButton("Menu", game.assets.getRegion("UI/flatDark32"), game.assets.getRegion("UI/shadedDark33"), skin, "Back to the Menu")
        {
            @Override
            public void mychanged(ChangeListener.ChangeEvent event, Actor actor)
            {
                game.setScreen(new StartMenuScreen(game));
            }
        };
        menuTable.right().bottom().pad(20);
        menuTable.add(menuButton.getbutton());
        menuTable.setVisible(false);

        ui.addActor(menuTable);

        stage.addActor(ui);

        ui.setSize(480, 854);
        pause = false;
    }

    @Override
    public void render(float delta)
    {
        if (pause)
        {
            delta = 0;
        }
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        level.background.render(batch, game.assets, delta);
        player.render(batch, game.assets);
        level.spawnedenemies.render(batch, game.assets);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (pause)
        {
            pauseTable.setVisible(true);
            menuTable.setVisible(true);
        } else
        {
            pauseTable.setVisible(false);
            menuTable.setVisible(false);
            updateGame();
        }
    }

    public void updateGame()//called when Game is running
    {
        float delta = 0;
        delta = Gdx.graphics.getRawDeltaTime();
        delta = Math.min(delta, 0.2f);
        player.movement(delta);
        player.shoot(delta, game.assets);
        level.spawnedenemies.update(delta, game.assets);
        level.update(delta);
        if (level.getCompleted())
        {
            game.nextLevel = level.nextLevel;
            levelCompleteTable.setVisible(true);
            menuTable.setVisible(true);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        //TODO Fix distortion when the window was resized and then setscreen Gamescreen is called
        game.viewport.update(width, height, true);
//        ui.setSize(width, height); //setup in show
        pause = true;
        System.out.println("resize");
    }

    @Override
    public void pause()
    {
        pause = true;
        System.out.println("paused");
    }

    @Override
    public void resume()
    {
        System.out.println("resume");
    }

    @Override
    public void hide()
    {
        System.out.println("hide");
        pause = true;
    }

    @Override
    public void dispose()
    {
        System.out.println("dispose");
    }

    public void togglePause()
    {
        pause = !pause;
        System.out.println("togglePause");

    }
}
