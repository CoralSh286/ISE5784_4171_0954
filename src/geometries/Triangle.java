package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

/**
 * A class that represents a triangle
 */
public class Triangle extends Polygon {

    /**
     * constructor
     *
     * @param p1 For the first point
     * @param p2 For the second point
     * @param p3 For the third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        var intersections = plane.findIntersections(ray);
        //Check if the ray intersect the plane.
        if (intersections == null) return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        // the three vectors from the same starting point
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double s1 = alignZero(n1.dotProduct(v));
        if (isZero(s1)) return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double s2 = alignZero(n2.dotProduct(v));
        if (s1 * s2 <= 0) return null;

        //we want to get a normal for each pyramid's face, so we do the crossProduct
        Vector n3 = v3.crossProduct(v1).normalize();
        double s3 = alignZero(n3.dotProduct(v));
        if (s1 * s3 <= 0) return null;

        return intersections;
    }
}