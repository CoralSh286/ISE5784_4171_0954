package geometries;

import primitives.*;

/** A class that represents a plane */
public class Plane implements Geometry{

    /** field for the p0 */
    final private Point _p0;

    /** field for the normal to the plane */
    final private Vector _normal;

    /**
     * constructor
     * @param p for a point on the plane
     * @param vector vector on the plane
     */
    public Plane(Point p, Vector vector) {
        _p0 = p;
        _normal = vector.normalize();
    }

    /**
     * constructor
     * @param p1 For the first point
     * @param p2 For the second point
     * @param p3 For the third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        _p0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector W = U.crossProduct(V);

        _normal = W.normalize();
    }

    /**
     * Getter for normal
     * @return the normal vector
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return  getNormal();
    }
}
