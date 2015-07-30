package at.kraweu.collisionDetection;

import javafx.scene.shape.Line;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Kraweu on 30.07.2015.
 */
public class Border
{
    public static boolean inside(Point coordinate,Point borderEnd)
    {
        return inside(coordinate,borderEnd,new Point(0,0));
    }
    public static boolean inside(Point coordinate,Point borderEnd, Point borderBeginn)
    {
        return inside(coordinate,borderEnd,borderBeginn,0,0);
    }
    public static boolean inside(Point coordinate,Point borderEnd, Point borderBeginn, int sizex, int sizey)
    {
        if (coordinate.x<borderBeginn.x||coordinate.x+sizex>borderEnd.x)
            return false;
        if (coordinate.y<borderBeginn.y||coordinate.y+sizey>borderEnd.y)
            return false;
        return true;
    }

}
