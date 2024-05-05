package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry {

     final protected double radius;

    RadialGeometry(double r){
        radius = r;
    }

    public Vector getNormal (Point point){
        return null;
    }
}
