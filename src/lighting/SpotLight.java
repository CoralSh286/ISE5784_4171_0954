package lighting;

import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * SpotLight class represents a spot light in the scene
 */
public class SpotLight extends PointLight {
    private final Vector direction;

    private double narrowBeam;

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
        if (Util.isZero(cos)) {
            return Color.BLACK;
        }
        Color pointLightIntensity = super.getIntensity(point);
        return (pointLightIntensity.scale(Math.max(0, cos)));
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



//package lighting;
//
//import primitives.*;
//
////==== the SpotLight represented source light like Spot =====//
//
//public class SpotLight extends PointLight {
//
//    private Vector dir;
//
//    /**
//     * constructor for the intensity
//     *
//     * @param color     of the intensity of the source of the light
//     * @param direction
//     */
//    public SpotLight(Color color, Point position, Vector direction) {
//        super(color, position);
//        this.dir = direction.normalize();
//    }
//
//    @Override
//    public Color getIntensity(Point point) {
//        double projection = this.dir.dotProduct(getL(point));
//
//        if (Util.isZero(projection)) {
//            return Color.BLACK;
//        }
//
//        double factor = Math.max(0, projection);
//        Color pointLightIntensity = super.getIntensity(point);
//
//        return (pointLightIntensity.scale(factor));
//    }
//}
