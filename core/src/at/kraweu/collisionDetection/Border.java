package at.kraweu.collisionDetection;


import java.awt.*;

/**
 * Contains methods correlated with the collision detection with a Border<p>
 * Created by Kraweu on 30.07.2015.
 */
@SuppressWarnings("JavaDoc")
public class Border
{
    /**
     * Returns if coordinate is completely inside the Border 0,0 - borderEnd x,y
     *
     * @param coordinate
     * @param borderEnd
     * @return
     */
    public static boolean inside(Point coordinate, Point borderEnd)
    {
        return inside(coordinate, borderEnd, new Point(0, 0));
    }

    /**
     * Returns if coordinate+size is completely inside the Border 0,0 - borderEnd x,y
     * @param coordinate
     * @param borderEnd
     * @param sizex
     * @param sizey
     * @return
     */
    public static boolean inside(Point coordinate, Point borderEnd, int sizex, int sizey)
    {
        return inside(coordinate, borderEnd, new Point(0, 0), sizex, sizey);
    }

    /**
     * Returns if coordinate is completely inside the Border borderBeginn x,y - borderEnd x,y
     * @param coordinate
     * @param borderEnd
     * @param borderBeginn
     * @return
     */
    public static boolean inside(Point coordinate, Point borderEnd, Point borderBeginn)
    {
        return inside(coordinate, borderEnd, borderBeginn, 0, 0);
    }

    /**
     * Returns if coordinate+size is completely inside the Border
     * @param coordinate
     * @param borderEnd
     * @param borderBeginn
     * @param sizex
     * @param sizey
     * @return
     */
    public static boolean inside(Point coordinate, Point borderEnd, Point borderBeginn, int sizex, int sizey)
    {
        try
        {
            if (coordinate == null || borderEnd == null || borderBeginn == null)//Checks for Null
                throw new NullPointerException();
            if (borderBeginn.getX() > borderEnd.getX() || borderBeginn.getY() > borderEnd.getY())//Checks if BorderEnd smaller than Borderbeginn
                throw new falseborderexception();
            if (sizex < 0 || sizey < 0)//Checks if size is Valid
                throw new InvalidSizeException(sizex, sizey);
            if (coordinate.getX() < borderBeginn.getX() || coordinate.getX() + sizex > borderEnd.getX())//Checks if x coordinate is completely inside Border
                return false;
            if (coordinate.getY() < borderBeginn.getY() || coordinate.getY() + sizey > borderEnd.getY())//Checks if y coordinate is completely inside Border
                return false;

            return true;

        } catch (falseborderexception e)
        {
            e.printStackTrace();
            System.out.print("Beginn: " + borderBeginn.getX() + "/");
            System.out.print(borderBeginn.getY() + " ");
            System.out.print("End: " + borderEnd.getX() + "/");
            System.out.print(borderEnd.getY() + " ");
            System.out.println();
            return false;
        } catch (InvalidSizeException e)
        {
            return false;
        } catch (NullPointerException e)
        {
            e.printStackTrace();
            System.out.println("NullPointerException");
            System.out.println("Coordinate: " + coordinate + " BorderEnd: " + borderEnd + " BorderBeginn: " + borderBeginn + " ");
            return false;
        }
    }

    /**
     * One Dimensional check<p>
     * Returns if coordinate+size is completely inside the Border
     *
     * @param coordinate
     * @param borderEnd
     * @param sizex
     * @return
     */
    public static boolean insidex(int coordinate, int borderEnd, int sizex)
    {
        return inside(new Point(coordinate, 0), new Point(borderEnd, 0), new Point(0, 0), sizex, 0);
    }
    private static class falseborderexception extends Throwable
    {
    }

}
