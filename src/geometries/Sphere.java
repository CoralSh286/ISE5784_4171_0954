package geometries;

import primitives.*;

/**
 * Department for representation Sphere
 */
public class Sphere extends RadialGeometry {
    /**
     * field for the center point
     */
    final private Point _center;

    /**
     * constructor
     * @param center for the center point
     * @param radius for the radius
     */
    public Sphere(Point center, double radius) {
        super(radius);
        _center = center;
    }

    public Vector getNormal(Point point) {
        return null;
    }
}
