package geometries;

import primitives.*;

import java.util.List;

/**
 * interface for finding the intersection point between the ray and the object
 */
public interface Intersectable {
    /**
     * Finding intersection points between the ray and body
     *
     * @param ray {@link Ray} pointing toward the object
     * @return list of intersection Point between the ray and the object
     */
    List<Point> findIntersections(Ray ray);
}
