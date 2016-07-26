package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Kraweu on 30.03.2016.
 */
public abstract class MenuButton
{
    Button menuButton;

    /**
     * TextButton
     */
    public MenuButton(String text, Skin skin)
    {
        menuButton = new TextButton(text, skin);
        menuButton.setName(text);
        menuButton.pad(10).padLeft(15);
        addListener();
    }

    /**
     * TextButton with tooltip
     */
    public MenuButton(String text, Skin skin, String tooltip)
    {
        this(text, skin);
        addTooltipListener(tooltip, skin);
    }

    /**
     * ImageButton
     */
    public MenuButton(String name, TextureRegion up, TextureRegion down)
    {
        menuButton = new ImageButton(new TextureRegionDrawable(up), new TextureRegionDrawable(down));
        menuButton.setName(name);
        addListener();
    }

    /**
     * ImageButton with tooltip
     */
    public MenuButton(String name, TextureRegion up, TextureRegion down, Skin skin, String tooltip)
    {
        this(name, up, down);
        addTooltipListener(tooltip, skin);
    }

    /**
     * Add a TooltipListener to menuButton
     */
    private void addTooltipListener(String tooltip, Skin skin)
    {
        menuButton.addListener(new TextTooltip(tooltip, skin)
        {
        });
    }

    /**
     * Add a ClickListener to menuButton
     */
    private void addListener()
    {
        menuButton.addListener(new ChangeListener()
        {
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("Pressed " + menuButton.getName() + " Button");
                mychanged(event, actor);
            }
        });
    }

    /**
     * Returns Button menuButton, instanceof Textbutton or ImageButton
     */
    Button getbutton()
    {
        return menuButton;
    }

    public abstract void mychanged(ChangeListener.ChangeEvent event, Actor actor);

}
