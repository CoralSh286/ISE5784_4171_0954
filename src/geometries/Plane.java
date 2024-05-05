package geometries;

import primitives.*;

/** A class that represents a plane */
public class Plane implements Geometry{

    /** field for the p0 */
    final private Point _p0;

    /** field for the normal to the plane */
    final private Vector _normal;

    /** Constructor with parameters */
    public Plane(Point p, Vector vector) {
        _p0 = p;
        _normal = vector.normalize();
    }

    /** Builder that gets points */
    public Plane(Point p1, Point p2, Point p3) {
        _normal = null;
        _p0 = p1;
    }

    /** Returning to normal */
    public Vector getNormal() {
        return _normal;
    }

    /** Implementation of the method getNormal */
    public Vector getNormal(Point point) {
        return  null;
    }
}
