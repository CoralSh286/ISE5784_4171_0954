package primitives;


/**
 * A class that represents a vector by a point in space
 */
public class Vector extends Point {
    /**
     * constructor
     *
     * @param x For the first coordinate
     * @param y For the second coordinate
     * @param z For the third coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ZERO vector is not allowed");
    }

    /**
     * constructor
     *
     * @param xyz a point in space
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ZERO vector is not allowed");
    }

    /**
     * Connecting vectors
     *
     * @param vector For connecting 2 vectors
     * @return New vector
     */
    public Vector add(Vector vector) {
        return new Vector(_xyz.add(vector._xyz));
    }

    /**
     * Multiplication of a vector by a scalar
     *
     * @param num to multiply by a vector
     * @return New vector
     */
    @SuppressWarnings("unused")
    public Vector scale(double num) {
        return new Vector(_xyz.scale(num));
    }

    /**
     * Scalar product between vectors
     *
     * @param vector which we will multiply by the given vector
     * @return A number
     */
    public double dotProduct(Vector vector) {
        return _xyz.d1 * vector._xyz.d1 + _xyz.d2 * vector._xyz.d2 + _xyz.d3 * vector._xyz.d3;
    }

    /**
     * Vector product between 2 vectors
     *
     * @param vector which we will multiply by the given vector
     * @return New vector
     */
    public Vector crossProduct(Vector vector) {
        // Calculation of a vector cross product according to the formula of Linear Algebra
        return new Vector(
                _xyz.d2 * vector._xyz.d3 - vector._xyz.d2 * _xyz.d3,
                _xyz.d3 * vector._xyz.d1 - vector._xyz.d3 * _xyz.d1,
                _xyz.d1 * vector._xyz.d2 - vector._xyz.d1 * _xyz.d2);
    }

    /**
     * Calculation of the squared length of the vector
     *
     * @return A number
     */
    public double lengthSquared() {
        // Multiplying each coordinate by itself and adding them together
        return _xyz.d1 * _xyz.d1 + _xyz.d2 * _xyz.d2 + _xyz.d3 * _xyz.d3;
    }

    /**
     * Calculation of the length of the vector
     *
     * @return A number
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes the vector to a vector of length 1
     *
     * @return New vector
     */
    public Vector normalize() {
        return new Vector(_xyz.reduce(length()));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return object instanceof Vector && super.equals(object);
    }

    @Override
    public String toString() {
        return "v" + super.toString();
    }
}
