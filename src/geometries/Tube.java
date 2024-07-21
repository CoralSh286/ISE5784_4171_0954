package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
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
        Point tubeCenterPoint = _ray.getPoint(projection);
        //Calculate the normal
        return point.subtract(tubeCenterPoint).normalize();
    }


//    @Override
//    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
//        return null;
//    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Vector rayDirection = ray.getDir();
        Vector axisDirection = this._ray.getDir();
        Point rayOrigin = ray.getPoint(0d);

        double dirDotAxis = alignZero(rayDirection.dotProduct(axisDirection)); // (D|V)

        Vector dirMinusDVV;
        if (dirDotAxis == 0d) {
            // that means dir - (D|V)*V = dir, so we can use dir as is
            dirMinusDVV = rayDirection;
        } else {
            // calculate (D|V)*V (DV is not zero)
            Vector dirDV = axisDirection.scale(dirDotAxis);
            try {
                // Subtract the scaled vector from the ray direction
                dirMinusDVV = rayDirection.subtract(dirDV);
            } catch (IllegalArgumentException e1) {
                return null; // if the ray direction is the same as (D|V)*V
            }
        }

        // Calculate the squared length of dir - (D|V)*V
        double A = alignZero(dirMinusDVV.lengthSquared());
        // Calculate the parameter t
        double t = alignZero(Math.sqrt(this._radius * this._radius / A));

        Vector deltaP;
        try {
            // Calculate the vector from the axis origin to the ray origin
            deltaP = rayOrigin.subtract(_ray.getPoint(0d));
        } catch (IllegalArgumentException e1) {
            // If the ray starts at the head of the axis
            if (dirDotAxis == 0d) {
                // Return intersection at distance radius if (D|V) is zero
                return t <= 0d ? null : List.of(new GeoPoint(this, ray.getPoint(_radius)));
            } else {
                // Return intersection at distance t otherwise
                return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // Calculate the dot product of deltaP and the axis direction
        double deltaP_dot_axis = alignZero(deltaP.dotProduct(axisDirection));
        Vector deltaPMinusDPV;
        if (deltaP_dot_axis == 0d) {
            // If the dot product is zero, use deltaP as is
            deltaPMinusDPV = deltaP;
        } else {
            // Scale the axis direction by the dot product
            Vector deltaP_VV = axisDirection.scale(deltaP_dot_axis);
            try {
                // Subtract the scaled vector from deltaP
                deltaPMinusDPV = deltaP.subtract(deltaP_VV);
            } catch (IllegalArgumentException e1) {
                // Return intersection at distance t if subtraction is not possible
                return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // Calculate the coefficients B and C for the quadratic equation
        double B = 2 * alignZero(dirMinusDVV.dotProduct(deltaPMinusDPV));
        double C = alignZero(deltaPMinusDPV.lengthSquared() - _radius * _radius);

        // Calculate the discriminant of the quadratic equation
        double discriminant = alignZero(B * B - 4 * A * C);
        if (discriminant <= 0d) {
            // If the discriminant is negative, there are no intersection points
            // if the discriminant is zero, there is one intersection point which is not possible because we intersect
            // infinite ray
            return null;
        }

        // Calculate the square root of the discriminant
        double discriminantSqrt = Math.sqrt(discriminant);

        // Create a list to store potential intersection points
        List<Point> intersections = new LinkedList<>();
        // Calculate the two potential intersection distances
        double t1 = alignZero((-B - discriminantSqrt) / (2 * A));
        double t2 = alignZero((-B + discriminantSqrt) / (2 * A));

        if (t1 > 0d) {
            intersections.add(ray.getPoint(t1));
        }

        if (t2 > 0d) {
            intersections.add(ray.getPoint(t2));
        }

        // Return the list of intersection points, or null if there are none
        return intersections.isEmpty() ? null : intersections.stream().map(p -> new GeoPoint(this, p)).toList();
    }

    @Override
    public void constructBox() {
        return;
    }

    @Override
    public boolean isIntersectBox(Ray ray) {
        return true;
    }
}
