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
public class Cylinder extends Tube implements Boundable {
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
    public Cylinder(int ray, Ray radius, double height) {
        super(radius, ray);
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

    @Override
    public AxisAlignedBoundingBox getAxisAlignedBoundingBox() {
        double minX, minY, minZ, maxX, maxY, maxZ;
        Point o1 = _ray.getP0(); // middle of first end
        Point o2 = o1.add(_ray.getDir().scale(_height)); // middle of second end
        double o2X = o2.getX();
        double o1X = o1.getX();
        // middle point of side circles plus a radius offset is a good approximation for the bounding box
        if (o1X > o2X) {
            maxX = o1X + _radius;
            minX = o2X - _radius;
        } else {
            maxX = o2X + _radius;
            minX = o1X - _radius;
        }
        double o2Y = o2.getY();
        double o1Y = o1.getY();
        if (o1Y > o2Y) {
            maxY = o1Y + _radius;
            minY = o2Y - _radius;
        } else {
            maxY = o2Y + _radius;
            minY = o1Y - _radius;
        }
        double o2Z = o2.getZ();
        double o1Z = o1.getZ();
        if (o1Z > o2Z) {
            maxZ = o1Z + _radius;
            minZ = o2Z - _radius;
        } else {
            maxZ = o2Z + _radius;
            minZ = o1Z - _radius;
        }
        AxisAlignedBoundingBox res = new AxisAlignedBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        res.addToContains(this);

        return res;
    }

    @Override
    public void constructBox() {
        Point endPoint1 = _ray.getP0().add(_ray.getDir().scale(_height));
        Point startPoint = _ray.getP0();
        // Determine the minimum and maximum coordinates
        double minX = Math.min(startPoint.getX(), endPoint1.getX()) - _radius;
        double maxX = Math.max(startPoint.getX(), endPoint1.getX()) + _radius;
        double minY = Math.min(startPoint.getY(), endPoint1.getY()) - _radius;
        double maxY = Math.max(startPoint.getY(), endPoint1.getY()) + _radius;
        double minZ = Math.min(startPoint.getZ(), endPoint1.getZ()) - _radius;
        double maxZ = Math.max(startPoint.getZ(), endPoint1.getZ()) + _radius;
        box = new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public boolean isIntersectBox(Ray ray) {
        return box.intersects(ray);
    }
}
