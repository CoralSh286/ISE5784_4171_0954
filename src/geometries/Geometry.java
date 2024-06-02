package geometries;

import primitives.*;

/**
 * An interface that characterizes a geometric body
 */
public abstract class Geometry extends Intersectable {

    /**
     * field for emission lighting
     */
    protected Color _emission = Color.BLACK;

    /**
     * getEmission function
     *
     * @return the geometry's color
     */
    public Color getEmission() {
        return this._emission;
    }

    /**
     * setEmission function
     *
     * @param emission for the appropriate field
     * @return the field
     */
    public Geometry setEmission(Color emission) {
        this._emission = emission;
        return this;
    }

    /**
     * Calculation of the normal vector at a point on the surface of the geometry body
     *
     * @param point A point on the surface
     * @return the normal vector at the point
     */
    public abstract Vector getNormal(Point point);
}
