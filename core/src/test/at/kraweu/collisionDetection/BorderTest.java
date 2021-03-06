package test.at.kraweu.collisionDetection;

import at.kraweu.collisionDetection.Border;
import junit.framework.TestCase;

import java.awt.*;

/**
 * Border Tester.
 *
 * @author Kraweu
 * @version 1.0
 * @since <pre>07/30/2015</pre>
 */
public class BorderTest extends TestCase
{
    public BorderTest(String name)
    {
        super(name);
    }

    public void setUp() throws Exception
    {
        super.setUp();
    }

    public void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Method: inside(Point coordinate, Point borderEnd)
     */
    public void testInsideForCoordinateBorderEnd() throws Exception
    {

        assertEquals(true, Border.inside(new Point(0, 0), new Point(0, 0)));
        assertEquals(false, Border.inside(new Point(1, 1), new Point(0, 0)));

        assertEquals(true, Border.inside(new Point(0, 0), new Point(1, 1)));
        assertEquals(true, Border.inside(new Point(1, 1), new Point(1, 1)));

        assertEquals(false, Border.inside(new Point(-1, 0), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(0, -1), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(-1, -1), new Point(200, 200)));
        assertEquals(true, Border.inside(new Point(0, 0), new Point(200, 200)));

        assertEquals(true, Border.inside(new Point(0, 200), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(0, 201), new Point(200, 200)));

        assertEquals(true, Border.inside(new Point(200, 0), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(201, 0), new Point(200, 200)));

        assertEquals(true, Border.inside(new Point(200, 200), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(201, 200), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(200, 201), new Point(200, 200)));
        assertEquals(false, Border.inside(new Point(201, 201), new Point(200, 200)));

    }

    /**
     * Method: inside(Point coordinate, Point borderEnd, int sizex, int sizey)
     */

    public void testInsideForCoordinateBorderEndSizexSizey() throws Exception
    {
        assertEquals(true, Border.inside(new Point(0, 0), new Point(1, 1), 1, 1));
        assertEquals(false, Border.inside(new Point(0, 0), new Point(1, 1), 2, 2));
    }

    /**
     * Method: inside(Point coordinate, Point borderEnd, Point borderBeginn)
     */
    public void testInsideForCoordinateBorderEndBorderBeginn() throws Exception
    {

        assertEquals(false, Border.inside(new Point(4, 5), new Point(200, 200), new Point(5, 5)));
        assertEquals(false, Border.inside(new Point(5, 4), new Point(200, 200), new Point(5, 5)));
        assertEquals(false, Border.inside(new Point(4, 4), new Point(200, 200), new Point(5, 5)));
        assertEquals(true, Border.inside(new Point(5, 5), new Point(200, 200), new Point(5, 5)));

    }

    /**
     * Method: inside(Point coordinate, Point borderEnd, Point borderBeginn, int sizex, int sizey)
     */
    public void testInsideForCoordinateBorderEndBorderBeginnSizexSizey() throws Exception
    {
        //TODO: Test goes here...
    }

    /**
     * Method: insidex(int coordinate, int borderEnd, int sizex)
     */
    public void testInsidex() throws Exception
    {
        assertEquals(true, Border.insidex(0, 0, 0));
        assertEquals(false, Border.insidex(1, 0, 0));
        assertEquals(true, Border.insidex(0, 1, 1));
        assertEquals(false, Border.insidex(0, 0, 1));
        assertEquals(true, Border.insidex(0, 1, 0));

        assertEquals(false, Border.insidex(-1, 200, 0));
        assertEquals(false, Border.insidex(199, 200, 2));
        assertEquals(true, Border.insidex(200, 200, 0));
        assertEquals(true, Border.insidex(0, 200, 100));
    }

}
