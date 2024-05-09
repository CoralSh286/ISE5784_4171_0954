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

    /** Test method for {@link primitives.Vector#add(primitives.Vector)} */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(new Vector(1, 5, 1), v1.add(v3), "ERROR: add() function wrong value");
    }

    /** Test method for {@link primitives.Vector#scale(double num)} */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============//
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "ERROR: scale()function wrong value");
    }

    /** Test method for {@link primitives.Vector#dotProduct(primitives.Vector)} */
    @Test
    void dotProduct() {
        // =============== Boundary Values Tests ==================
        // for the zero vector
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
        // ============ Equivalence Partitions Tests ==============//
        //Calculation for 2 random vectors
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
    }

    /** Test method for {@link primitives.Vector#crossProduct(primitives.Vector)} */
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
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /** Test method for {@link Vector#lengthSquared()} */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(14, v1.lengthSquared(), "ERROR: lengthSquared() wrong value");
    }

    /** Test method for {@link Vector#length()}  */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(Math.sqrt(14), v1.length(), "ERROR: length() wrong value");
    }

    /** Test method for {@link Vector#normalize()}  */
    @Test
    void normalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertFalse(v == n, "normalized() changes the vector itself");
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n), "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }
}