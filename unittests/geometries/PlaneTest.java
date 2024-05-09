package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test for planes
 */
class PlaneTest {
    /** Test method for {@link geometries.Plane#Plane(Point p1, Point p2, Point p3)}  */
    @Test
    void testPlane() {
        // =============== Boundary Values Tests ==================
        //TC01 three points on the same line
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(1, 1, 2);
        Point p3 = new Point(1, 1, 3);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3), "ERROR: three points on the same line");
        //TC11 two points are the same
        Point p4 = new Point(1, 2, 3);
        Point p5 = new Point(1, 2, 3);
        Point p6 = new Point(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> new Plane(p4, p5, p6), "ERROR:  two points are the same");
    }

    /** Test method for {@link geometries.Plane#getNormal(Point)}  */
    @Test
    void getNormal() {
            Point p1 = new Point(1, 0, 0);
            Point p2 = new Point(0, 1, 0);
            Point p3 = new Point(0, 0, 1);
            Point p4 = new Point(1, 0, 0);
            Plane plane = new Plane(p1, p2, p3);
            // ============ Equivalence Partitions Tests ==============//
            // TC01: tests for calculation of normal to the plane//
            double sqrt3 = Math.sqrt(1d / 3); // the expected Normal
            //check the positive and negative direction of the normal//
            assertTrue(plane.getNormal(p4).equals(new Vector(sqrt3, sqrt3, sqrt3)) ||
                    plane.getNormal(p4).scale(-1).equals(new Vector(sqrt3, sqrt3, sqrt3)), "ERROR: The calculation of normal to the plane is not calculated correctly");
    }
}