package geometries;

import primitives.Point;
import primitives.Vector;

/** An abstract class that implements the geometry interface */
public abstract class RadialGeometry implements Geometry {

    /** Radius for a round shape */
     final protected double radius;

     /** Constructor for the radius */
    RadialGeometry(double r){
        radius = r;
    }

    /** Implementation of the method getNormal */
    public Vector getNormal (Point point){
        return null;
    }
}
