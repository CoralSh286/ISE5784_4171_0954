package geometries;

import primitives.Ray;

public class Cylinder extends Tube{
    final private double _high;

    public Cylinder(Ray ray, double radius, double high) {
        super(ray, radius);
        this._high = high;
    }

}
