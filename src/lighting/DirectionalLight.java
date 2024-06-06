package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

//stage 6
/**
 * A class for DirectionalLight
 */
public class DirectionalLight extends Light implements LightSource{

    private final Vector direction;

    /**
     * constructor for the intensity
     *
     * @param dir for the direction
     * @param color of the intensity of the source of the light
     */
    protected DirectionalLight(Color color, Vector dir) {
        super(color);
        this.direction = dir.normalize();
    }


    @Override
    public Color getIntensity(Point p) {
        return this.intensity;
    }


    @Override
    public Vector getL(Point p) {
        return this.direction.normalize();
    }

}
