package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** A class that represents a tube */
public class Tube extends RadialGeometry {
    protected Ray _ray;

    /** constructor */
    public Tube(Ray ray, double radius) {
        super(radius);
        _ray = ray;
    }

    /** Implementation of the method getNormal */
    public Vector getNormal(Point point) {
        return  null;
    }
}
