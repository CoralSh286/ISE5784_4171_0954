
package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


    /**
    * Unit tests for the Cylinder class.
    */

class CylinderTests {


    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private final double DELTA = 0.000001;

    /**
     *  Test method for {@link geometries.Cylinder#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        Cylinder cylinder2 = new Cylinder(2, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the cylinder (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1))),
                "Ray's line out of cylinder");

        // TC02: Ray starts before and crosses the cylinder (2 points)
        List<Point> result = cylinder2.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1)));
        assertEquals(2, result.size(), "Wrong number of points");

        // TC03: Ray starts inside the cylinder (1 point)
        result = cylinder2.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");

        // TC04: Ray starts after the cylinder (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 0, 3), new Vector(0, 0, 1))),
                "Ray's line out of cylinder");

        // TC05: Ray starts at the cylinder and goes outside (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0, -1))),
                "Ray's line out of cylinder");


        // TC06: Ray starts at the cylinder and goes inside (1 point)
        result = cylinder2.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");

        // TC07: Ray intersects the cylinder's top surface (1 point)
        result = cylinder2.findIntersections(new Ray(new Point(0, 0, 3), new Vector(0, 0, -1)));
        assertEquals(2, result.size(), "Wrong number of points");

        // TC10: Ray starts at the cylinder's top surface and goes inside (1 point)
        result = cylinder2.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

        // TC11: Ray intersects the tube but not the cylinder (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 0, 3), new Vector(0, 1, 0))),
                "Ray's line out of cylinder");

        // TC12: Ray tangent to the cylinder's top surface (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 1, 0))),
                "Ray's line out of cylinder");

        // TC13: Ray tangent to the cylinder's bottom surface (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 1, 0))),
                "Ray's line out of cylinder");

        // TC14: Ray tangent to the cylinder's side surface (0 points)
        assertNull(cylinder2.findIntersections(new Ray(new Point(0, 2, -1), new Vector(0, 0, 1))),
                "Ray's line out of cylinder");
    }

}