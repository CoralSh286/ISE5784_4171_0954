package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Department for representation Sphere
 */
public class Sphere extends RadialGeometry {
    /**
     * field for the center point
     */
    final private Point _center;

    /**
     * constructor
     *
     * @param center for the center point
     * @param radius for the radius
     */
    public Sphere(Point center, double radius) {
        super(radius);
        _center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(_center).normalize();
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0(); // ray's starting point
        Vector v = ray.getDir(); // "the v vector" from the presentation

        // if p0 on center, calculate with line parametric representation
        // the direction vector normalized.
        if (_center.equals(p0)) return List.of(new GeoPoint(this, (p0.add(v.scale(_radius)))));

        Vector u = _center.subtract(p0);
        double tm = v.dotProduct(u);
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = _radiusSquared - dSquared;
        if (alignZero(thSquared) <= 0) return null;

        double th = Math.sqrt(thSquared);

        double t2 = alignZero(tm + th);
        if (t2 <= 0) return null;

        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2)))
                : List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
    }


}