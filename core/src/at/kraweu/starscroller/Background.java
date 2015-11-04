package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class Background
{
    String asset;

    public void setAsset(String asset)
    {
        this.asset = asset;
    }

    public void render(SpriteBatch batch, Assets assets)
    {
        batch.draw(assets.getRegion(asset), 0, 0);
    }
}
