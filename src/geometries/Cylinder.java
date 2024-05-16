package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** A class that represents a Cylinder */
public class Cylinder extends Tube{

    /** Stave for the high */
    final private double _height;

    /**
     * constructor
     * @param ray for the main axis
     * @param radius for the radius
     * @param height for the height
     */
    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        this._height = height;
    }

    @Override
    public Vector getNormal(Point point) {

        // define the center of cylinder's sides
        Vector cylinderCenterVector = _ray.getDir();

        Point centerOfOneSide = _ray.getP0();
        Point centerOfSecondSide = _ray.getP0().add(_ray.getDir().scale(_height));

        //The normal at a base will be simply equal to central ray's
        //direction vector 𝑣 or opposite to it (−𝑣) so we check it
        if (point.equals(centerOfOneSide)) {
            return cylinderCenterVector.scale(-1);
        }
        else if (point.equals(centerOfSecondSide)){
            return cylinderCenterVector;
        }

        //If the point on one of the cylinder's bases, but it's not the center point
        double projection = cylinderCenterVector.dotProduct(point.subtract(centerOfOneSide));
        if (projection == 0) {
            Vector v1 = point.subtract(centerOfOneSide);
            return v1.normalize();
        }

        //If the point on the side of the cylinder.
        Point center = centerOfOneSide.add(cylinderCenterVector.scale(projection));
        Vector v = point.subtract(center);

        return v.normalize();
    }

}
