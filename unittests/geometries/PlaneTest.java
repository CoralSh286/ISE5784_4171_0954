package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    /** Test method for {@link geometries.Plane#findIntersections(Ray ray)} */
    @Test
    void findIntersections() {
        Plane plane = new Plane(new Point(1,0,1), new Point(0,1,1), new Point(1,1,1));

        // ================ EP: The Ray must be neither orthogonal nor parallel to the plane ==================
        //TC01: Ray intersects the plane
        assertEquals(List.of(new Point(1,0.5,1)),
                plane.findIntersections(new Ray(new Point(0,0.5,0),new Vector(1,0,1))),
                "Ray intersects the plane");

        //TC02: Ray does not intersect the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,0.5,2), new Vector(1,2,5))),
                "Ray does not intersect the plane");

        // ====================== Boundary Values Tests =======================//
        // **** Group: Ray is parallel to the plane
        //TC10: The ray included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,1), new Vector(1,0,0))),
                "Ray is parallel to the plane, the ray included in the plane");

        //TC11: The ray not included in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,2), new Vector(1,0,0))),
                "Ray is parallel to the plane, the ray not included in the plane");

        // **** Group: Ray is orthogonal to the plane
        //TC12: according to 𝑃0, before the plane
        assertEquals(List.of(new Point(1,1,1)),
                plane.findIntersections(new Ray(new Point(1,1,0), new Vector(0,0,1))),
                "Ray is orthogonal to the plane, according to p0, before the plane");

        //TC13: according to 𝑃0, in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,1), new Vector(0,0,1))),
                "Ray is orthogonal to the plane, according to p0, in the plane");

        //TC14: according to 𝑃0, after the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,2,2), new Vector(0,0,1))),
                "Ray is orthogonal to the plane, according to p0, after the plane");

        // **** Group: Ray is neither orthogonal nor parallel to
        //TC15: Ray begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(2,4,1), new Vector(2,3,5))),
                "Ray is neither orthogonal nor parallel to ray and begin at the plane");

        //TC16: Ray begins in the same point which appears as reference point in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,0,1), new Vector(2,3,5))),
                "Ray is neither orthogonal nor parallel to ray and begins in the same point " +
                        "which appears as reference point in the plane");
    }
}