package at.kraweu.starscroller;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Kraweu on 07.10.2015.
 */
public class Enemies
{
    private int ammount;

    private List<Enemy> enemies;

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
}
