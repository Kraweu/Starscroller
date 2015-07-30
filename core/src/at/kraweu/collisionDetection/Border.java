package at.kraweu.collisionDetection;


import java.awt.*;

/**
 * Created by Kraweu on 30.07.2015.
 */
public class Border
{
    public static boolean inside(Point coordinate, Point borderEnd)
    {
        return inside(coordinate, borderEnd, new Point(0, 0));
    }
    public static boolean inside(Point coordinate, Point borderEnd, int sizex, int sizey)
    {
        return inside(coordinate, borderEnd, new Point(0, 0),sizex,sizey);
    }

    public static boolean inside(Point coordinate, Point borderEnd, Point borderBeginn)
    {
        return inside(coordinate, borderEnd, borderBeginn, 0, 0);
    }

    public static boolean inside(Point coordinate, Point borderEnd, Point borderBeginn, int sizex, int sizey)
    {
        try
        {
            if (coordinate==null||borderEnd==null||borderBeginn==null)
                throw new NullPointerException();
            if (borderBeginn.getX() > borderEnd.getX() || borderBeginn.getY() > borderEnd.getY())
                throw new falseborderexception();
            if (sizex < 0 || sizey < 0)
                throw new invalidsizeexception();
            if (coordinate.getX() < borderBeginn.getX() || coordinate.getX() + sizex > borderEnd.getX())
                return false;
            if (coordinate.getY() < borderBeginn.getY() || coordinate.getY() + sizey > borderEnd.getY())
                return false;
            return true;

        } catch (falseborderexception e)
        {
            System.out.print("Beginn: " + borderBeginn.getX() + "/");
            System.out.print(borderBeginn.getY() + " ");
            System.out.print("End: " + borderEnd.getX() + "/");
            System.out.print(borderEnd.getY() + " ");
            System.out.println();
            return false;
        } catch (invalidsizeexception e)
        {
            System.out.print("x: " + sizex+" y: " + sizey);
            return false;
        } catch (NullPointerException e)
        {
            System.out.println("NullPointerException");
            System.out.println("Coordinate: "+coordinate+" BorderEnd: "+borderEnd+" BorderBeginn: "+borderBeginn+" ");
            return false;
        }
    }

    private static class falseborderexception extends Throwable
    {

    }

    private static class invalidsizeexception extends Throwable
    {

    }
}
