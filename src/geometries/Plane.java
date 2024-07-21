package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * A class that represents a plane
 */
public class Plane extends Geometry {

    /**
     * field for the p0
     */
    final private Point _p0;

    /**
     * field for the normal to the plane
     */
    final private Vector _normal;

    /**
     * constructor
     *
     * @param p      for a point on the plane
     * @param vector vector on the plane
     */
    public Plane(Point p, Vector vector) {
        _p0 = p;
        _normal = vector.normalize();
    }

    /**
     * constructor
     *
     * @param p1 For the first point
     * @param p2 For the second point
     * @param p3 For the third point
     */
    public Plane(Point p1, Point p2, Point p3) {
        _p0 = p1;
        Vector u = p2.subtract(p1);
        Vector v = p3.subtract(p1);
        Vector w = u.crossProduct(v);

        _normal = w.normalize();
    }

    /**
     * Getter for normal
     *
     * @return the normal vector
     */
    public Vector getNormal() {
        return _normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return _normal;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0(); // according to the illustration P0 is the same point of the ray's P0 (that's why the definition))
        Vector v = ray.getDir(); // according to the illustration v is the same vector of the ray's vector (that's why the definition))
        if (_p0.equals(p0)) // if the ray starting from the plane it doesn't cut the plane at all
            return null; // so return null

        double nv = _normal.dotProduct(v); // the formula's denominator of "t" (t =(n*(Q-P0))/nv)
        // ray is lying on the plane axis
        if (isZero(nv)) return null; // can't divide by zero (nv is the denominator)

        Vector q0p0 = _p0.subtract(p0);
        double np0q0 = alignZero(_normal.dotProduct(q0p0));
        // t should be bigger than 0
        if (isZero(np0q0)) return null;

        double t = alignZero(np0q0 / nv);
        // t should be bigger than 0
        return t <= 0 ? null : List.of(new GeoPoint(this, p0.add((v).scale(t))));
    }

    @Override
    public boolean isIntersectBox(Ray ray) {
        return true;
    }

    @Override
    public void constructBox() {
        return;
    }

}

