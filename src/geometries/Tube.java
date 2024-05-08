package geometries;

import primitives.*;

/** A class that represents a tube */
public class Tube extends RadialGeometry {
    protected final Ray _ray;

    /**
     * constructor
     * @param ray for the main axis
     * @param radius for the radius
     */
    public Tube(Ray ray, double radius) {
        super(radius);
        _ray = ray;
    }

    public Vector getNormal(Point point) {
        return  null;
    }
}
