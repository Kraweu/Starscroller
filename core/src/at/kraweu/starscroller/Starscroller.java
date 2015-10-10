package at.kraweu.starscroller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;


public class Starscroller extends ApplicationAdapter {

    SpriteBatch batch;

    public Assets assets;

    public WeaponType[] weaponTypes;

    public Ship[] ships;

    public Input input;

    public Player player;

    public Enemies enemies;

    //Aimed native Resolution 854x480
    public static final int gamewidth = 480;
    public static final int gameheight = 854;
    public static final Point gamesize = new Point(gamewidth, gameheight);

    private Viewport viewport;
    private Camera camera;

    @Override
    public void create () {
        camera = new PerspectiveCamera();
        camera.rotate(new Vector3(1, 2, 3), 5);
        viewport = new FitViewport(gamewidth, gameheight, camera);
        batch = new SpriteBatch();

        input = new Input(this);
        assets = new Assets();
        System.out.println();
        System.out.println();
        Loader loader = new Loader();
        loader.setAssets(assets);
        weaponTypes = loader.loadWeaponTypes();
        ships = loader.loadShips(weaponTypes);
        System.out.println();
        System.out.println();
        Gdx.input.setInputProcessor(input);
        player = new Player();
        player.setShip(ships[0].clone(), assets);
        enemies = new Enemies();
    }

    @Override
    public void render () {
        updateGame();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        player.render(batch, assets);
        enemies.render(batch, assets);
        batch.end();
    }
    public void updateGame()//called when Game is running
    {
        float delta = 0;
        delta = Gdx.graphics.getRawDeltaTime();
        player.movement(delta);
        player.shoot(delta);
        enemies.update(delta);
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
