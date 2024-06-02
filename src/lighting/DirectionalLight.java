package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{

    private Vector direction;

    /**
     * constructor for the intensity
     *
     * @param color of the intensity of the source of the light
     */
    protected DirectionalLight(Color color, Vector dir) {
        super(color);
        this.direction = dir.normalize();
    }

    /**
     * Return the intensity light on specific point
     *
     * @param p the point on the object (Point3D)
     * @return the intensity (Color)
     */
    @Override
    public Color getIntensity(Point p) {
        return this.intensity;
    }

    /**
     * Return normalize direction vector from the light source to the object
     *
     * @param p the point on the object (Point)
     * @return normalize direction vector from the light source to the object (Vector)
     */
    @Override
    public Vector getL(Point p) {
        return this.direction.normalize();
    }

}
