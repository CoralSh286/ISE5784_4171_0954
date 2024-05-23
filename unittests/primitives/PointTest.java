package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing for points
 */
class PointTest {
    /**
     * First point for the test
     */
    Point p1 = new Point(1, 2, 3);
    /**
     * Second point for the test
     */
    Point p2 = new Point(2, 3, 4);
    /**
     * Vector for the test
     */
    Vector v1 = new Vector(2, 5, 7);

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============//
        //TC01 Subtract random point and vector
        assertEquals(new Vector(-1, -1, -1), p1.subtract(p2), "Error: Subtract() wrong value");
        // =============== Boundary Values Tests ==================
        //TC11 Subtract Connecting a point vector that creates the zero vector
        assertThrows(IllegalArgumentException.class, () -> p2.subtract(p2), "Error: There is no abnormal throw for the ZERO point");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(new Point(3, 7, 10), p1.add(v1), "Error: add() wrong value");
    }


    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(3, p1.distanceSquared(p2), "Error: disanceSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(Math.sqrt(3), p1.distance(p2), "Error: distance() wrong value");
    }
}