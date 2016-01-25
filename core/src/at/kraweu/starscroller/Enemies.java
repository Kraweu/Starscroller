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

    private Level level;
    private List<Enemy> enemies = new LinkedList<Enemy>();

    public Enemies(Level level)
    {
        this.level = level;
    }

    Enemy getEnemiebyId(int id)
    {
        Iterator iter = getIterator();
        while (iter.hasNext())
        {
            Enemy enem = (Enemy) iter.next();
            if (enem.getId() == id)
                return enem;
        }
        System.out.println("Enemy Id not found: " + id);
        return null;
    }

    Iterator<Enemy> getIterator()
    {
        return enemies.iterator();
    }

    List<Enemy> getList()
    {
        return enemies;
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
            if (enem.isDestroyed())
            {
                iter.remove();
            } else
                enem.update(delta, assets, this);
        }
    }

    void addEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }

    void deleteEnemy(Enemy enemy)
    {
        enemies.remove(enemy);
    }

    public void addEnemies(Enemies enemies)
    {
        Iterator iter = enemies.getIterator();
        while (iter.hasNext())
            this.addEnemy((Enemy) iter.next());
    }

    public void start()
    {
        Iterator iter = this.getIterator();
        while (iter.hasNext())
            ((Enemy) iter.next()).moveAI.start();
    }

    public boolean isEmpty()
    {
        return enemies.isEmpty();
    }
}
