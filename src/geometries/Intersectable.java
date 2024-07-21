package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * Abstract class for finding the intersection points between a ray and an object.
 */
public abstract class Intersectable {

    /**
     * The bounding box for the geometry.
     */
    protected Box box;

    /**
     * Constructs the bounding box for the geometry.
     */
    public abstract void constructBox();

    /**
     * Checks if the given ray intersects the bounding box of the geometry.
     *
     * @param ray The ray to check for intersection.
     * @return true if the ray intersects the bounding box, false otherwise.
     */
    public abstract boolean isIntersectBox(Ray ray);

    /**
     * Represents an Axis-Aligned Bounding Box (AABB).
     */
    public static class Box {
        private final double minX;
        private final double minY;
        private final double minZ;
        private final double maxX;
        private final double maxY;
        private final double maxZ;

        /**
         * Constructs a new Box object with the specified minimum and maximum coordinates.
         *
         * @param minX The minimum x-coordinate of the box.
         * @param minY The minimum y-coordinate of the box.
         * @param minZ The minimum z-coordinate of the box.
         * @param maxX The maximum x-coordinate of the box.
         * @param maxY The maximum y-coordinate of the box.
         * @param maxZ The maximum z-coordinate of the box.
         */
        public Box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
        }

        /**
         * Checks if the box intersects with the given ray within the maximum distance.
         *
         * @param ray The ray to check for intersection.
         * @return true if the box intersects with the ray, false otherwise.
         */
        public boolean intersects(Ray ray) {
            double dirX = ray.getDir().getX();
            double dirY = ray.getDir().getY();
            double dirZ = ray.getDir().getZ();
            double p0X = ray.getP0().getX();
            double p0Y = ray.getP0().getY();
            double p0Z = ray.getP0().getZ();
            double txmin = Util.alignZero(minX - p0X) / dirX;
            double txmax = Util.alignZero(maxX - p0X) / dirX;
            double tymin = Util.alignZero(minY - p0Y) / dirY;
            double tymax = Util.alignZero(maxY - p0Y) / dirY;
            double tzmin = Util.alignZero(minZ - p0Z) / dirZ;
            double tzmax = Util.alignZero(maxZ - p0Z) / dirZ;
            double tmin = Math.max(Math.max(Math.min(txmin, txmax), Math.min(tymin, tymax)), Math.min(tzmin, tzmax));
            double tmax = Math.min(Math.min(Math.max(txmin, txmax), Math.max(tymin, tymax)), Math.max(tzmin, tzmax));

            // if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
            if (tmax < 0) {
                return false;
            }
            // if tmin > tmax, ray doesn't intersect AABB
            return !(tmin > tmax);
        }
    }

    /**
     * Finds the intersection points between the ray and the object.
     *
     * @param ray The ray pointing toward the object.
     * @return List of intersection points between the ray and the object.
     */
    public final List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Returns the intersection points with the bodies.
     *
     * @param ray The ray from the camera.
     * @return List of intersection points.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        if (ray.Improve) {
            return (!isIntersectBox(ray)) ? null : findGeoIntersectionsHelper(ray);
        }
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Returns the intersection points with the bodies.
     *
     * @param ray The ray from the camera.
     * @return List of intersection points.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * This class is used to know the specific geometry the ray intersects.
     * It helps to calculate the color at the intersection point, taking into account the geometry's color.
     */
    public static class GeoPoint {
        /**
         * The geometry the ray intersects.
         */
        public Geometry geometry;

        /**
         * The intersection point.
         */
        public Point point;

        /**
         * Constructs a new GeoPoint with the specified geometry and point.
         *
         * @param geometry The geometry the ray intersects.
         * @param point    The intersection point.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return obj instanceof GeoPoint other &&
                    geometry.equals(other.geometry) && point.equals(other.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
