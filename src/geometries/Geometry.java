package geometries;

import primitives.*;

/** An interface that characterizes a geometric shape */
public interface Geometry {

    Vector getNormal(Point point);
}
