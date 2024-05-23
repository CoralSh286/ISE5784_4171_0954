package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test for sphere
 */
class SphereTest {
    private final Point p100 = new Point(1, 0, 0);
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}
     */
    @Test
    void getNormal() {
        Sphere sphere = new Sphere(new Point(0, 0, 0), 5);
        // ============ Equivalence Partitions Tests ==============//
        // TC01: tests for calculation of normal to the plane//
        assertEquals(new Vector(0, 0, 1), sphere.getNormal(new Point(0, 0, 5)), "ERROR: The calculation of normal to the Sphere is not calculated correctly");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {

        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        final Vector v = new Vector(1, 0, 0);
        final Vector v1 = new Vector(0, 1, 1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(List.of(gp2),
                sphere.findIntersections(new Ray(new Point(0.8, 0.6, 0), v310)),
                "Ray crosses sphere on one point");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), v110)),
                "Ray's line after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 point)
        assertEquals(List.of(gp2),
                sphere.findIntersections(new Ray(gp1, v310)),
                "Ray starts at sphere and goes inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), v310)),
                "Ray starts at sphere and goes inside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals(List.of(new Point(0, 0, 0), new Point(2, 0, 0)),
                sphere.findIntersections(new Ray(p01, v)),
                "Ray starts before the sphere and need to be 2 intersections");

        // TC14: Ray starts at sphere and goes inside (1 point)
        assertEquals(List.of(new Point(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point(0, 0, 0), v)),
                "Ray starts at sphere and goes inside");

        // TC15: Ray starts inside (1 point)
        assertEquals(List.of(new Point(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point(0.5, 0, 0), v)),
                "Ray starts inside ");

        // TC16: Ray starts at the center (1 point)
        assertEquals(List.of(new Point(2, 0, 0)),
                sphere.findIntersections(new Ray(p100, new Vector(0.5, 0, 0))),
                "Ray starts at the center ");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), v)),
                "Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), v)),
                "Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, -1, -1), v1)),
                "Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), v1)),
                "Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 2, 2), v1)),
                "Ray starts after the tangent point");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1))),
                "Ray starts after the tangent point");
    }
}