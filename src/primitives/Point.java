package primitives;

import java.util.Objects;

/**
 * A class for a point in space that consists of 3 coordinates
 */
public class Point {

    public static final Point ZERO = new Point(Double3.ZERO) ;
    /** The point that consists of 3 numbers */
   protected final Double3 _xyz;


   /** Creates a constructor with a datum that is a Point object */
   Point(Double3 xyz) {
        _xyz = xyz;
    }


    /** Creates a constructor by 3 points that are received as parameters */
    public Point(double x, double y, double z) {
       _xyz = new Double3(x,y,z);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this._xyz.equals(other._xyz);
    }


    @Override
    public String toString() {
        return "Point : (" + _xyz.d1 + "," + _xyz.d2 + "," + _xyz.d3 + ")";
    }


    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }


    /** vector subtraction.
     * Returns a vector which is the vector received as a parameter minus the given vector */
    public Vector subtract(Point other){

        Double3 result = _xyz.subtract(other._xyz);

        if (result.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("ZERO vector not allowed");
        }
        return new Vector(result);
    }


    /** Adds a vector to a point and a new point is obtained */
    public Point add(Vector vector){
       return new Point(_xyz.add(vector._xyz));
    }


    /** Calculates the distance between 2 points in a square */
    public double distanceSquared(Point other){
       double x1 = _xyz.d1;
       double y1 = _xyz.d2;
       double z1 = _xyz.d3;
       double x2 = other._xyz.d1;
       double y2 = other._xyz.d2;
       double z2 = other._xyz.d3;


       /** Subtracts the coordinates between the 2 vectors and multiplies by itself */
       return((x2 -x1) * (x2 -x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }


    /** Calculates the distance between 2 points */
    public double distance(Point other){
        double result = distanceSquared(other);
        return Math.sqrt(result);
    }
}

