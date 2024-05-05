package geometries;

import primitives.Ray;

/** A class that represents a Cylinder */
public class Cylinder extends Tube{

    /** Stave for the high */
    final private double _high;


    /** constructor */
    public Cylinder(Ray ray, double radius, double high) {
        super(ray, radius);
        this._high = high;
    }

}
