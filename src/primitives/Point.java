package primitives;

/**
 * A class which represents a point in 3D Euclidean coordinate system
 */
public class Point {
    /**
     * The point at the head of the 3D Euclidean coordinate system
     */
    public static final Point ZERO = new Point(Double3.ZERO);
    /**
     * The point that consists of 3 numbers
     */
    protected final Double3 _xyz;

    /**
     * constructor
     *
     * @param xyz a point in space
     */
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    /**
     * constructor
     *
     * @param x For the first coordinate
     * @param y For the second coordinate
     * @param z For the third coordinate
     */
    public Point(double x, double y, double z) {
        _xyz = new Double3(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this._xyz.equals(other._xyz);
    }

    @Override
    public String toString() {
        return "" + _xyz;
    }

    @Override
    public int hashCode() {
        return _xyz.hashCode();
    }

    /**
     * Point subtraction.
     * Calculates subtraction between two points and return the result which is actually
     * the vector from other point to this point
     *
     * @param other another point to subtract from this point
     * @return the vector from other point to this point
     */
    public Vector subtract(Point other) {

        Double3 result = _xyz.subtract(other._xyz);

        if (result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return new Vector(result);
    }

    /**
     * Adding a vector to a point
     *
     * @param vector so that we can connect a point to it
     * @return A new point
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }

    /**
     * Calculates the distance between 2 points in a square
     *
     * @param other Another point from which the distance is calculated
     * @return A number
     */
    public double distanceSquared(Point other) {
        double dx = other._xyz.d1 - _xyz.d1;
        double dy = other._xyz.d2 - _xyz.d2;
        double dz = other._xyz.d3 - _xyz.d3;
        double result = dx * dx + dy * dy + dz * dz;
        if(result == 0)
            throw new IllegalArgumentException("ZERO distance is not allowed");
        return result;
    }

    /**
     * Calculates the distance between 2 points
     *
     * @param other Another point from which the distance is calculated
     * @return A number
     */
    public double distance(Point other) {
        double result = distanceSquared(other);
        return Math.sqrt(result);
    }
}