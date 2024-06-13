package primitives;

import geometries.Intersectable.GeoPoint;
import geometries.Intersectable;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;


/**
 * A class for representing a beam by a point and a vector
 */
public class Ray {

    private static final double DELTA = 0.1;

    /**
     * Creating the point
     */
    private final Point _p0;

    /**
     * Creating the vector
     */
    private final Vector _dir;

    /**
     * constructor
     *
     * @param p0  for a point
     * @param dir for a vector
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    /**
     * getter function
     *
     * @return the point _p0
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * getter function
     *
     * @return the direction vector
     */
    public Vector getDir() {
        return _dir;
    }

    @Override
    public String toString() {
        return "Ray= " + "_p0: " + _p0 + ", _dir: " + _dir + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return (object instanceof Ray other)
                && this._p0.equals(other._p0)
                && this._dir.equals(other._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    /**
     * get Point at specific distance in the ray's direction
     *
     * @param t is a distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        return isZero(t) ? _p0 : _p0.add(_dir.scale(t));
    }

    /**
     * The method search the intersection point closest to the small head
     *
     * @param points List of intersection points of the beam with the body
     * @return the nearest intersection point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Return the closest GeoPoint from all intersection GeoPoints
     *
     * @param geoPointList list of intersections
     * @return {@link Intersectable.GeoPoint}
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {

        Intersectable.GeoPoint closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        double geoPointDistance; // the distance between the "this.p0" to each point in the list

        if (geoPointList != null && !geoPointList.isEmpty()) {
            for (var geoPoint : geoPointList) {
                geoPointDistance = this._p0.distance(geoPoint.point);
                if (geoPointDistance < minDistance) {
                    minDistance = geoPointDistance;
                    closestPoint = geoPoint;
                }
            }
        }
        return closestPoint;
    }


    /**
     * Constructor to initialize ray
     *
     * @param p0  point of the ray
     * @param n   normal vector
     * @param dir direction vector of the ray - it must be normalized when calling this constructor!
     */
    public Ray(Point p0, Vector dir, Vector n) {
        double delta = dir.dotProduct(n) >= 0 ? DELTA : -DELTA;
        this._p0 = p0.add(n.scale(delta));
        this._dir = dir;
    }
}
