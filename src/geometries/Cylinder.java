package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * A class that represents a Cylinder
 */
public class Cylinder extends Tube {

    /**
     * Stave for the high
     */
    final private double _height;

    /**
     * constructor
     * @param ray    for the main axis
     * @param radius for the radius
     * @param height for the height
     */
    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        this._height = height;
    }
}
