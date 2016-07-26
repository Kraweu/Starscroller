package at.kraweu.starscroller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * Created by Kraweu on 26.07.2016.
 */
public class StartMenuScreenTest implements Screen
{
    private Starscroller game;
    private Stage stage;
    private SceneLoader sl;
    private Viewport viewport;

    public StartMenuScreenTest(Starscroller starscroller)
    {
        game = starscroller;
        viewport = new FitViewport(120f, 213.5f); // this should be the size of camera in WORLD units. make sure you check that in editor first.
        sl = new SceneLoader(); // default scene loader loads all resources from default RM as usual.
        sl.loadScene("MainScene", viewport); // loading scene as usual
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        sl.getEngine().update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height)
    {

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
