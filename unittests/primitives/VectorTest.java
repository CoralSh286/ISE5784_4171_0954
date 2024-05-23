package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing for vectors
 */
class VectorTest {
    /**
     * First vector for the test
     */
    Vector v1 = new Vector(1, 2, 3);
    /**
     * Second vector for the test
     */
    Vector v2 = new Vector(-2, -4, -6);
    /**
     * Third vector for the test
     */
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector#Vector(double p1, double p2, double p3)}
     */
    @Test
    void testVector() {
        // =============== Boundary Values Tests ==================
        //TC01 for the zero vector
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: zero vector does not an exception");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Point point)}
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============//
        //TC01 subtract 2 random vectors
        assertEquals(new Vector(1, -1, 5), v1.subtract(v3), "ERROR: add() function wrong value");
        // =============== Boundary Values Tests ==================
        //TC11 for the zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), "Error: There is no abnormal throw for the ZERO vector");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============//
        //TC01 add 2 random vectors
        assertEquals(new Vector(1, 5, 1), v1.add(v3), "ERROR: add() function wrong value");
        // =============== Boundary Values Tests ==================
        //TC11 for the zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1, -2, -3)), "Error: There is no abnormal throw for the ZERO vector");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double num)}
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "ERROR: scale()function wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}
     */
    @Test
    void dotProduct() {
        // =============== Boundary Values Tests ==================
        //TC01 for the zero vector
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");
        //TC11 ============ Equivalence Partitions Tests ==============//
        //Calculation for 2 random vectors
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v2), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(Math.sqrt(14), v1.length(), "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    void normalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        //TC01 Test that the length of the normal equals 1
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        //TC02 Check that they are in the same direction
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n), "normalized vector is not in the same direction");
        //TC03 Test that the normal vector is obtained
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }
}