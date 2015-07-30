package at.kraweu.collisionDetection;

import java.awt.*;

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
        return false;
    }
}
