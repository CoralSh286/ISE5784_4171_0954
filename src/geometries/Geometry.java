package geometries;

import primitives.*;

/**
 * An interface that characterizes a geometric body
 */
public abstract class Geometry extends Intersectable {

    /**
     * field for emission lighting
     */
    //stage 6
    protected Color _emission = Color.BLACK;

    /**
     * the material the geometry has made of
     */
    //stage 6
    private Material _material = new Material();

    /**
     * Get material of the geometry
     *
     * @return Material of the geometry
     */
    //stage 6
    public Material getMaterial() {
        return this._material;
    }

    /**
     * Set material of the geometry
     *
     * @param material the Material of the geometry
     * @return the geometry itself
     */
    //stage 6
    public Geometry setMaterial(Material material) {
        this._material = material;
        return this;
    }

    /**
     * getEmission function
     *
     * @return the geometry's color
     */
    //stage 6
    public Color getEmission() {
        return this._emission;
    }

    /**
     * setEmission function
     *
     * @param emission for the appropriate field
     * @return the field
     */
    //stage 6
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
