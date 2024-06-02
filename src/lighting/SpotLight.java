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
        return this.setkC(kC);
    }

    @Override
    public SpotLight setkL(double kL) {
        return this.setkL(kL);
    }

    @Override
    public SpotLight setkQ(double kQ) {
        return this.setkQ(kQ);
    }

    @Override
    public Color getIntensity(Point point) {
        double projection = this._dir.dotProduct(getL(point));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }

        double factor = Math.max(0, projection);
        Color pointLightIntensity = super.getIntensity(point);

        return (pointLightIntensity.scale(factor));
    }

}
