package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * A class for SpotLight
 */
public class SpotLight extends PointLight {

    private Vector _dir;

    /**
     * constructor for the intensity
     *
     * @param intensity of the intensity of the source of the light
     * @param position for the position
     */
    protected SpotLight(Color intensity, Point position) {
        super(intensity, position);
    }

    /**
     * constructor for the intensity
     *
     * @param color     of the intensity of the source of the light
     * @param direction for the direction
     */
    protected SpotLight(Color color, Point position, Vector direction) {
        super(color, position);
        this._dir = direction.normalize();
    }

    @Override
    public SpotLight setkC(double kC) {
        return (SpotLight) super.setkC(kC);
    }

    @Override
    public SpotLight setkL(double kL) {
        return (SpotLight) super.setkC(kL);
    }

    @Override
    public SpotLight setkQ(double kQ) {
        return (SpotLight) super.setkC(kQ);
    }

    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(Math.max(0, _dir.dotProduct(getL(point).normalize())));
    }

}
