package lighting;

import primitives.*;

import static primitives.Util.*;

/**
 * SpotLight class represents a spotlight in the scene
 */
public class SpotLight extends PointLight {
    private final Vector direction;

    private double narrowBeam = 1;

    /**
     * for field initialization
     *
     * @param _narrowBeam the parameter
     * @return the field
     */
    public LightSource setNarrowBeam(double _narrowBeam) {
        this.narrowBeam = _narrowBeam;
        return this;
    }

    /**
     * SpotLight constructor
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        double cos = this.direction.dotProduct(getL(point));
        if (alignZero(cos) <= 0)
            return Color.BLACK;
        return super.getIntensity(point).scale(Math.pow(cos, narrowBeam));
    }

    /**
     * set the constant attenuation factor
     *
     * @param kc the constant attenuation factor
     * @return the SpotLight object
     */
    public SpotLight setKc(double kc) {
        return (SpotLight) super.setKc(kc);
    }

    /**
     * set the linear attenuation factor
     *
     * @param kl the linear attenuation factor
     * @return the SpotLight object
     */
    public SpotLight setKl(double kl) {
        return (SpotLight) super.setKl(kl);
    }

    /**
     * set the quadratic attenuation factor
     *
     * @param kq the quadratic attenuation factor
     * @return the SpotLight object
     */
    public SpotLight setKq(double kq) {
        return (SpotLight) super.setKq(kq);
    }

}
