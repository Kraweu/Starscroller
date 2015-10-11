package at.kraweu.starscroller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kraweu on 07.10.2015.
 */
public class Enemies
{
    private int ammount;

    private List<Enemy> enemies = new LinkedList<Enemy>();

    Enemy getEnemiebyId(int id)
    {
        Iterator iter = enemies.iterator();
        while (iter.hasNext())
        {
            Enemy enem = (Enemy) iter.next();
            if (enem.getId() == id)
                return enem;
        }
        System.out.println("Enemy Id not found: " + id);
        return null;
    }

    Iterator getIterator()
    {
        return enemies.iterator();
    }

    void render(SpriteBatch batch, Assets assets)
    {
        Iterator iter = getIterator();
        while (iter.hasNext())
        {
            Enemy enem = (Enemy) iter.next();
            enem.getShip().render(batch, assets);
        }
    }

    void update(float delta, Assets assets)
    {
        Iterator iter = getIterator();
        while (iter.hasNext())
        {
            Enemy enem = (Enemy) iter.next();
            enem.update(delta, assets);
        }
    }

}
