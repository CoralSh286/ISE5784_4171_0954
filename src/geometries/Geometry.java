package geometries;

import primitives.*;

/** An interface that characterizes a geometric shape */
public interface Geometry {

    /**
     * Calculation of the normal vector
     * @param point A point on the plane
     * @return the normal vector
     */
    Vector getNormal(Point point);
}
