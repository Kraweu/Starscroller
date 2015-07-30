package test.at.kraweu.collisionDetection; 

import at.kraweu.collisionDetection.Border;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.awt.*;

/** 
* Border Tester. 
* 
* @author <Authors name> 
* @since <pre>07/30/2015</pre> 
* @version 1.0 
*/ 
public class BorderTest extends TestCase { 
public BorderTest(String name) { 
super(name); 
} 

public void setUp() throws Exception { 
super.setUp(); 
} 

public void tearDown() throws Exception { 
super.tearDown(); 
} 

/** 
* 
* Method: inside(Point coordinate, Point borderEnd) 
* 
*/ 
public void testInsideForCoordinateBorderEnd() throws Exception {

    assertEquals(true, Border.inside(new Point(0, 0), new Point(0, 0)));
    assertEquals(false, Border.inside(new Point(1, 1), new Point(0, 0)));

    assertEquals(true, Border.inside(new Point(0, 0), new Point(1, 1)));
    assertEquals(true, Border.inside(new Point(1, 1), new Point(1, 1)));

    assertEquals(false, Border.inside(new Point(-1,0),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(0,-1),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(-1,-1),new Point(200,200)));
    assertEquals(true, Border.inside(new Point(0,0),new Point(200,200)));

    assertEquals(true, Border.inside(new Point(0,200),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(0,201),new Point(200,200)));

    assertEquals(true, Border.inside(new Point(200,0),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(201,0),new Point(200,200)));

    assertEquals(true, Border.inside(new Point(200,200),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(201,200),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(200,201),new Point(200,200)));
    assertEquals(false, Border.inside(new Point(201,201),new Point(200,200)));

}

/** 
* 
* Method: inside(Point coordinate, Point borderEnd, Point borderBeginn) 
* 
*/ 
public void testInsideForCoordinateBorderEndBorderBeginn() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: inside(Point coordinate, Point borderEnd, Point borderBeginn, int sizex, int sizey) 
* 
*/ 
public void testInsideForCoordinateBorderEndBorderBeginnSizexSizey() throws Exception { 
//TODO: Test goes here... 
} 



public static Test suite() { 
return new TestSuite(BorderTest.class); 
} 
} 
