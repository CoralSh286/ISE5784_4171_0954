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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        var intersection = plane.findGeoIntersections(ray);
        if (intersection == null) return null;

        Point p0 = ray.getP0();
        Vector rayDir = ray.getDir();
        Vector vector1 = vertices.get(0).subtract(p0);
        Vector vector2 = vertices.get(1).subtract(p0);
        Vector n1 = vector1.crossProduct(vector2);
        double dot1 = alignZero(rayDir.dotProduct(n1));
        if (dot1 == 0) return null;

        Vector vector3 = vertices.get(2).subtract(p0);
        Vector n2 = vector2.crossProduct(vector3);
        double dot2 = alignZero(rayDir.dotProduct(n2));
        if (dot1 * dot2 <= 0) return null;

        Vector n3 = vector3.crossProduct(vector1);
        double dot3 = alignZero(rayDir.dotProduct(n3));
        if (dot1 * dot3 <= 0) return null;

        intersection.getFirst().geometry = this;
        return intersection;
    }

}