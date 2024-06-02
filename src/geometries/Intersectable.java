package geometries;

import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 * interface for finding the intersection point between the ray and the object
 */
public abstract class Intersectable {
    /**
     * Finding intersection points between the ray and body
     *
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersection Point between the ray and the object
     */
    public abstract List<Point> findIntersections(Ray ray);

//    public List<GeoPoint> findGeoIntersections(Ray ray) {
//        return findGeoIntersectionsHelper(ray);
//    }

    //======== the NVI design pattern =======//
//    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


    /**
     * this class has been written because we want to know the specific geometry the ray cross it over
     * because we added the emission light for each geometry and if we want to calculate the color at the point
     * we have to mind the geometry's color (this class is PDS)
     */
    public static class GeoPoint {
        /**
         * field for a geometry
         */
        public Geometry geometry;
        /**
         * field for a point
         */
        public Point point;

        /**
         * constructor
         * @param geometry for a geometry
         * @param point for a point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && point.equals(geoPoint.point);
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
