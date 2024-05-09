package geometries;

import primitives.*;

/** A class that represents a tube */
public class Tube extends RadialGeometry {
    protected final Ray _ray;

    /**
     * constructor
     * @param ray for the main axis
     * @param radius for the radius
     */
    public Tube(Ray ray, double radius) {
        super(radius);
        _ray = ray;
    }

    @Override
    public Vector getNormal(Point point) {
            Vector tubeCenterVector = _ray.getDir();
            Point p0 = _ray.getP0();
            double projection = tubeCenterVector.dotProduct(point.subtract(p0));
            if (projection == 0) {
                throw new IllegalArgumentException("the projection must not be 0");
            }
            // Calculating O when O is a point on direction tube vector (o = p0 + proj * v)//
            Point tubeCenterPoint = p0.add(tubeCenterVector.scale(projection));
            //Calculate the normal
            return point.subtract(tubeCenterPoint).normalize();
    }
}
