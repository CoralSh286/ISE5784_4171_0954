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
     * Creates a constructor with a datum that is a Point object
     */
    Point(Double3 xyz) {
        _xyz = xyz;
    }


    /**
     * Creates a constructor by 3 points that are received as parameters
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
     * Adds a vector to a point and a new point is obtained
     */
    public Point add(Vector vector) {
        return new Point(_xyz.add(vector._xyz));
    }


    /**
     * Calculates the distance between 2 points in a square
     */
    public double distanceSquared(Point other) {
        double dx = other._xyz.d1 - _xyz.d1;
        double dy = other._xyz.d2 - _xyz.d2;
        double dz = other._xyz.d3 - _xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Calculates the distance between 2 points
     */
    public double distance(Point other) {
        double result = distanceSquared(other);
        return Math.sqrt(result);
    }
}

