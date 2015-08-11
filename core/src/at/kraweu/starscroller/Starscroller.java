package at.kraweu.starscroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;


public class Starscroller extends ApplicationAdapter {

    SpriteBatch batch;

    public Assets assets;

    public Input input;

    public Player player;

    //Aimed native Resolution 854x480
    public static final int gamewidth = 480;
    public static final int gameheight = 854;
    public static final Point gamesize = new Point(gamewidth, gameheight);

    private Viewport viewport;
    private Camera camera;

    @Override
    public void create () {
        camera = new PerspectiveCamera();
        viewport = new FitViewport(gamewidth, gameheight, camera);

        batch = new SpriteBatch();

        input = new Input(this);
        assets = new Assets();
        Gdx.input.setInputProcessor(input);
        player = new Player(assets);
    }

    @Override
    public void render () {
        updateGame();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        batch.draw(assets.getRegion("playerShip1_blue"), player.getPosx(), player.getPosy());
        batch.end();
    }
    public void updateGame()//called when Game is running
    {

        float delta = 0;
        delta = Gdx.graphics.getRawDeltaTime();
        player.movement(delta);
        player.shoot(delta);
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
