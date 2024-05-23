package geometries;

import primitives.*;

/**
 * An interface that characterizes a geometric body
 */
public interface Geometry extends Intersectable {

    /**
     * Calculation of the normal vector at a point on the surface of the geometry body
     *
     * @param point A point on the surface
     * @return the normal vector at the point
     */
    Vector getNormal(Point point);
}
