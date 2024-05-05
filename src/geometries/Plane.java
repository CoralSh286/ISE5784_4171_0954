package geometries;

import primitives.*;

public class Plane implements Geometry{
    final private Point _p0;
    final private Vector _normal;

    public Plane(Point p, Vector vector) {
        _p0 = p;
        _normal = vector.normalize();
    }

    public Plane(Point p1, Point p2, Point p3) {
        _normal = null;
        _p0 = p1;
    }

    public Vector getNormal() {
        return _normal;
    }

    public Vector getNormal(Point point) {
        return  null;
    }
}
