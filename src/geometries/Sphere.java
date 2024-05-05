package geometries;

import primitives.*;

public class Sphere extends RadialGeometry{

    final private Point _center;

    public Sphere(Point center, double radius) {
    super(radius);
    _center = center;
}

    public Vector getNormal(Point point) {
        return  null;
    }
}
