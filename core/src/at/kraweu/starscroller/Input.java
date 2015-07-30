package at.kraweu.starscroller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

/**
 * Created by Alex on 17.05.2015.
 */
public class Input implements InputProcessor
{
    Starscroller game;
    public Input(Starscroller game)
    {
        this.game=game;
    }

    @Override
    public boolean keyDown(int keycode)
    {
        switch(keycode)
        {
            //Movement

            case Keys.LEFT:
            case Keys.A:
                game.player.setMoveLeft(true);
                break;
            case Keys.RIGHT:
            case Keys.D:
                game.player.setMoveRight(true);
                break;
            case Keys.UP:
            case Keys.W:
                game.player.setMoveUp(true);
                break;
            case Keys.DOWN:
            case Keys.S:
                game.player.setMoveDown(true);
                break;

            //Shooting

            case Keys.SPACE:
                game.player.setShoot(true);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        switch(keycode)
        {

            //Movement

            case Keys.LEFT:
            case Keys.A:
                game.player.setMoveLeft(false);
                break;
            case Keys.RIGHT:
            case Keys.D:
                game.player.setMoveRight(false);
                break;
            case Keys.UP:
            case Keys.W:
                game.player.setMoveUp(false);
                break;
            case Keys.DOWN:
            case Keys.S:
                game.player.setMoveDown(false);
                break;

            //Shooting

            case Keys.SPACE:
                game.player.setShoot(false);
                break;
        }
        return false;    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
