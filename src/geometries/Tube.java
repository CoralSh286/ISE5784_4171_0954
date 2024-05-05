package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    protected Ray _ray;


    public Tube(Ray ray, double radius) {
        super(radius);
        _ray = ray;
    }

    public Vector getNormal(Point point) {
        return  null;
    }
}
