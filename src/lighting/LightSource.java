package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class for the realization of light
 */
public interface LightSource {
    /**
     * Get the intensity of the light at a point
     *
     * @param p origin of the light
     * @return the intensity
     */
    public Color getIntensity(Point p);

    /**
     * Get the direction of the light from a point
     *
     * @param p the point
     * @return the direction
     */
    public Vector getL(Point p);

    /**
     * returned the distance
     *
     * @param point point
     * @return the distance
     */
    double getDistance(Point point);
}
