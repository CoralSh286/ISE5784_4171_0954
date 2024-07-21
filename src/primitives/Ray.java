package primitives;

import geometries.Intersectable.GeoPoint;
import geometries.Intersectable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * A class for representing a ray by a point and a vector.
 */
public class Ray {
    /**
     * Flag for whether to improve the ray intersection.
     */
    public boolean Improve;

    /**
     * Sets the improve flag.
     *
     * @param Improve The new value for the improve flag.
     */
    public void setBImprove(boolean Improve) {
        this.Improve = Improve;
    }

    private static final double DELTA = 0.1;

    /**
     * The starting point of the ray.
     */
    private final Point _p0;

    /**
     * The direction vector of the ray.
     */
    private final Vector _dir;

    /**
     * Constructor for creating a new ray.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction vector of the ray.
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    /**
     * Getter for the starting point of the ray.
     *
     * @return The starting point of the ray.
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * Getter for the direction vector of the ray.
     *
     * @return The direction vector of the ray.
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
     * Get the point at a specific distance in the ray's direction.
     *
     * @param t The distance for reaching the new point.
     * @return The new point.
     */
    public Point getPoint(double t) {
        return isZero(t) ? _p0 : _p0.add(_dir.scale(t));
    }

    /**
     * Find the closest intersection point from a list of points.
     *
     * @param points List of intersection points of the ray with the object.
     * @return The closest intersection point.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Return the closest GeoPoint from all intersection GeoPoints.
     *
     * @param geoPointList List of intersection GeoPoints.
     * @return The closest GeoPoint.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        Intersectable.GeoPoint closestPoint = null;
        double minDistance = Double.MAX_VALUE;
        double geoPointDistance; // The distance between this.p0 to each point in the list

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
     * Constructor to initialize a ray with a normal vector.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction vector of the ray.
     * @param n   The normal vector.
     */
    public Ray(Point p0, Vector dir, Vector n) {
        double delta = dir.dotProduct(n) >= 0 ? DELTA : -DELTA;
        this._p0 = p0.add(n.scale(delta));
        this._dir = dir;
    }

    /**
     * Get the point on the ray at a given distance.
     *
     * @param length Distance from the start of the ray.
     * @return The new point.
     */
    public Point getTargetPoint(double length) {
        return isZero(length) ? _p0 : _p0.add(_dir.scale(length));
    }

    /**
     * Generates a beam of rays within a specified radius and distance.
     *
     * @param n         The normal vector to the geometry.
     * @param radius    The radius of the beam circle.
     * @param distance  The distance to the beam circle.
     * @param numOfRays The number of rays in the beam.
     * @return List of beam rays.
     */
    public List<Ray> generateBeam(Vector n, double radius, double distance, int numOfRays) {
        if (numOfRays == 1 || isZero(radius)) // The component (glossy surface / diffuse glass) is turned off
            return List.of(this);

        List<Ray> rays = new LinkedList<>();
        // The 2 vectors that create the virtual grid for the beam
        Vector nX = _dir.createNormal();
        Vector nY = _dir.crossProduct(nX);

        Point centerCircle = this.getTargetPoint(distance);
        Point randomPoint;
        Vector v12;

        double rand_x, rand_y, delta_radius = radius / (numOfRays - 1);
        double nv = n.dotProduct(_dir);

        for (int i = 0; i < numOfRays; i++) {
            randomPoint = centerCircle;
            rand_x = Util.random(-radius, radius);
            rand_y = Util.randomSign() * Math.sqrt(radius * radius - rand_x * rand_x);

            try {
                randomPoint = randomPoint.add(nX.scale(rand_x));
            } catch (Exception ex) {
                i--;
            }

            try {
                randomPoint = randomPoint.add(nY.scale(rand_y));
            } catch (Exception ex) {
                i--;
            }

            v12 = randomPoint.subtract(_p0).normalize();

            double nt = Util.alignZero(n.dotProduct(v12));

            if (nv * nt > 0) {
                rays.add(new Ray(_p0, v12));
            }
            radius -= delta_radius;
        }

        return rays;
    }
}
