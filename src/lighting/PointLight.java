package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class for PointLight
 */
public class PointLight extends Light implements LightSource {

    /**
     * The position point of the light source in the space
     */
    private Point _position;

    /**
     * kC is The specular attenuation factor, required to ensure that the denominator in getIntensity > 1
     * kL is The light source attenuation factor
     * kQ is The attenuation factor of the energy coming to the point
     * <p>
     * the formula is: Il = I0/(Kc + Ki*d + Kq*d^2);
     */
    private double kC = 1, kL = 0d, kQ = 0d;

    /**
     * constructor for the intensity
     *
     * @param intensity of the intensity of the source of the light
     */
    protected PointLight(Color intensity, Point position) {
        super(intensity);
        this._position = position;
    }

    /**
     * setkC function
     *
     * @param kC specular attenuation factor
     * @return specular attenuation factor
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setkL function
     *
     * @param kL light source attenuation factor
     * @return light source attenuation factor
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setkQ function
     *
     * @param kQ The attenuation factor
     * @return The attenuation factor
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        double distance = this._position.distance(point);
        double distanceSquared = distance * distance;
        double factor = this.kC + this.kL * distance + this.kQ * distanceSquared;

        //Return the final intensity
        return getIntensity().reduce(factor);
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(this._position).normalize();
    }

}
