package at.kraweu.collisionDetection;


/**
 * Thrown when size is not valid<p>
 * Prints a Stacktrace, sizex and sizey<p>
 * Created by Kraweu on 31.07.2015
 */
public class InvalidSizeException extends Throwable
{

    public InvalidSizeException(int sizex, int sizey)
    {
        System.out.println();
        System.out.println("Invalid Size Exception");
        printStackTrace();
        System.out.println();
        System.out.println("x: " + sizex + " y: " + sizey);
        System.out.println();
    }
}
