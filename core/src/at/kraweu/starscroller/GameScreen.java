package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class GameScreen implements Screen
{
    private Starscroller game;
    private Player player;
    private Level level;
    private Stage stage;
    private Group ui;
    private Table mainTable;
    /**
     * If True Game will be Paused
     */
    boolean pause;
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
        Gdx.input.setInputProcessor(game.input);
    }

    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());
        ui = new Group();
        mainTable = new Table();
        mainTable.defaults().pad(30).expandX().fillX();
        mainTable.top().padTop(50);
        mainTable.setFillParent(true);
        mainTable.padLeft(10).padRight(10);
        mainTable.add(level.getMessageLabel());
        mainTable.row();

        ui.addActor(mainTable);
        stage.addActor(ui);


        pause = false;
    }

    @Override
    public void render(float delta)
    {
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

        } else
            updateGame();
        pause = false;
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
    }

    @Override
    public void resize(int width, int height)
    {
        //TODO Fix distortion when the window was resized and then setscreen Gamescreen is called
        game.viewport.update(width, height, true);
        ui.setSize(width, height);
        pause = true;
    }

    @Override
    public void pause()
    {
        pause = true;
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
