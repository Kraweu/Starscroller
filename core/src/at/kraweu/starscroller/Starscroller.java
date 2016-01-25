package at.kraweu.starscroller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;


public class Starscroller extends Game
{
    private static Starscroller game;

    public Assets assets;

    public WeaponType[] weaponTypes;

    public Ship[] ships;

    public Level[] levels;

    public Input input;

    public Preferences preferences;

    public Player player;

    public TooltipManager tooltipManager;


    //Aimed native Resolution 854x480
    public static final int gamewidth = 480;
    public static final int gameheight = 854;
    public static final Point gamesize = new Point(gamewidth, gameheight);

    private Viewport viewport;
    private Camera camera;

    @Override
    public void create () {
        game = this;
        camera = new PerspectiveCamera();
        camera.rotate(new Vector3(1, 2, 3), 5);
        viewport = new FitViewport(gamewidth, gameheight, camera);

        input = new Input(this);
        assets = new Assets();
        System.out.println();
        System.out.println();

        Loader loader = new Loader();
        loader.setAssets(assets);
        weaponTypes = loader.loadWeaponTypes();
        ships = loader.loadShips(weaponTypes);
        levels = loader.loadLevels(ships);

        System.out.println();
        System.out.println();
        Gdx.input.setInputProcessor(input);

        preferences = Gdx.app.getPreferences("at.kraweu.starscroller.preferences");

        //Setup tooltipManager values
        tooltipManager = TooltipManager.getInstance();
        tooltipManager.initialTime = 1.5f;
        tooltipManager.subsequentTime = 0.5f;

        player = new Player();
        player.setShip(ships[0].myClone(), assets);

//        setScreen(new StartMenuScreen(this));
        setScreen(new GameScreen(this, player, levels[0]));//skip menu for debugging
    }

    public static Starscroller getGame()
    {
        return game;
    }
}
