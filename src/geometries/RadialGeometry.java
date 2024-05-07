package geometries;

/**
 * An abstract class that implements the geometry interface
 */
public abstract class RadialGeometry implements Geometry {
    /**
     * Radius for a round shape
     */
    final protected double _radius;
    /**
     * Radius for a round shape
     */
    final protected double _radiusSquared;

    /**
     * Constructor for the radius
     */
    RadialGeometry(double r) {
        _radius = r;
        _radiusSquared = r * r;
    }
}
