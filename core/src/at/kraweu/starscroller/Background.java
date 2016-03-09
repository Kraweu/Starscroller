package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kraweu on 13.10.2015.
 */
public class Background
{
    List<Layer> backgroundlayers = new LinkedList<Layer>();

    /**
     * Clear backgroundlayers and add asset
     **/
    public void setAsset(String asset, float speed)
    {
        backgroundlayers.clear();
        backgroundlayers.add(new Layer(asset,speed));
    }

    public void render(SpriteBatch batch, Assets assets, float delta)
    {
        for (int i = 0; i < backgroundlayers.size(); i++)
        {
            backgroundlayers.get(i).position += delta;
//            beginn at the bottom of the image
            float layerpositon = -(backgroundlayers.get(i).position * backgroundlayers.get(i).speed);
            batch.draw(assets.getRegion(backgroundlayers.get(i).asset), 0, layerpositon);
            if (layerpositon < -assets.getRegion(backgroundlayers.get(i).asset).originalHeight + Starscroller.gameheight)
                batch.draw(assets.getRegion(backgroundlayers.get(i).asset), 0, layerpositon + assets.getRegion(backgroundlayers.get(i).asset).originalHeight);
            if (layerpositon < -assets.getRegion(backgroundlayers.get(i).asset).originalHeight)
                backgroundlayers.get(i).position = 0;
        }
//        batch.draw(assets.getRegion(backgroundlayers.get(1).asset), 0, -45);
    }

    /**
     * Add asset to backgroundlayers
     **/
    public void addLayer(String asset, float speed)
    {
        backgroundlayers.add(new Layer(asset, speed));
    }
}

class Layer
{
    String asset;
    float speed;
    float position = 0;

    Layer(String asset, float speed)
    {
        this.asset = asset;
        this.speed = speed;
    }
}