package geometries;

import primitives.*;

import java.util.List;

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
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0(); // ray's starting point
        Point o = _center; //the sphere's center point
        Vector v = ray.getDir(); // "the v vector" from the presentation

        // if p0 on center, calculate with line parametric representation
        // the direction vector normalized.
        if (o.equals(p0)) {
            Point newPoint = p0.add(ray.getDir().scale(_radius));
            return List.of(newPoint);
        }

        Vector u = o.subtract(p0);
        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d >= _radius) {
            return null;
        }

        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }

        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(p1);
        }

        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        }
        return null;
    }
}
