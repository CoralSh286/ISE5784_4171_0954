package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;
/**
 * A class that represents a tube
 */
public class Tube extends RadialGeometry {
    /**
     * A ray for the direction of the tube
     */
    protected final Ray _ray;

    /**
     * constructor
     *
     * @param ray    for the main axis
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
        // Calculating O when O is a point on direction tube vector (o = p0 + proj * v)//
        Point tubeCenterPoint = isZero(projection) ? p0 : p0.add(tubeCenterVector.scale(projection));
        //Calculate the normal
        return point.subtract(tubeCenterPoint).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
