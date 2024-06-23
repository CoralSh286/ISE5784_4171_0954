package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents a Cylinder
 */
public class Cylinder extends Tube {
    /**
     * Stave for the high
     */
    final private double _height;

    /**
     * constructor
     *
     * @param ray    for the main axis
     * @param radius for the radius
     * @param height for the height
     */
    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        this._height = height;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Initialize intersections list
        List<Point> intersections = new LinkedList<>();

        // Find intersections with the infinite cylinder
        Tube tube = new Tube(_ray, _radius);
        List<Point> infiniteCylinderIntersections = tube.findIntersections(ray);
        if (infiniteCylinderIntersections != null) {
            intersections.addAll(infiniteCylinderIntersections);
        }

        // Remove intersections outside the cylinder height
        Iterator<Point> iterator = intersections.iterator();
        while (iterator.hasNext()) {
            Point intersection = iterator.next();
            double t = _ray.getDir().dotProduct(intersection.subtract(_ray.getPoint(0d)));
            if (t <= 0d || t >= _height) {
                iterator.remove();
            }
        }

        // Define planes for the bottom and top bases
        Plane bottomBase = new Plane(_ray.getPoint(0d), _ray.getDir());
        Plane topBase = new Plane(_ray.getPoint(_height), _ray.getDir());

        // Return intersections if there are exactly 2 (so they are on the sides of the cylinder)
        if (intersections.size() == 2) {
            return List.of(new GeoPoint(this, intersections.get(0)), new GeoPoint(this, intersections.get(1)));
        }


        // Find intersections with the bottom base
        List<Point> bottomBaseIntersections = bottomBase.findIntersections(ray);
        if (bottomBaseIntersections != null) {
            Point intersection = bottomBaseIntersections.getFirst();
            if (_ray.getPoint(0d).distanceSquared(intersection) <= _radius * _radius) {
                intersections.add(intersection);
            }
        }

        // Find intersections with the top base
        List<Point> topBaseIntersections = topBase.findIntersections(ray);
        if (topBaseIntersections != null) {
            Point intersection = topBaseIntersections.getFirst();
            if (_ray.getPoint(_height).distanceSquared(intersection) <= _radius * _radius) {
                intersections.add(intersection);
            }
        }

        // if the ray is tangent to the cylinder
        if (intersections.size() == 2 && _ray.getPoint(0).distanceSquared(intersections.get(0)) == _radius * _radius &&
                _ray.getPoint(_height).distanceSquared(intersections.get(1)) == _radius * _radius) {
            Vector v = intersections.get(1).subtract(intersections.get(0));
            if (v.normalize().equals(_ray.getDir()) || v.normalize().equals(_ray.getDir().scale(-1d)))
                return null;
        }

        // Return null if no valid intersections found
        List<GeoPoint> geoPoints = new LinkedList<>();
        for (Point p : intersections) {
            geoPoints.add(new GeoPoint(this, p));
        }

        return geoPoints.isEmpty() ? null : geoPoints;
    }
}
