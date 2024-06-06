package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient Light for all object in 3D space
 * this class represented us the Ambient Light
 */
public class AmbientLight extends Light {

    /**
     * Field is initialized to default - the color black
     */
    static public AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * constructor for knowing the intensity after the light factor
     *
     * @param iA - Light illumination (RGB)
     * @param kA - Light factor
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * constructor for knowing the intensity after the light factor
     *
     * @param iA - Light illumination (RGB)
     * @param kA - Light attenuation factor
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }

}
