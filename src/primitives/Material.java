package primitives;

/**
 * A class for a material
 */
public class Material {
    /**
     * for the attenuation coefficient
     */
    public Double3 kT = Double3.ZERO;
    /**
     * for the attenuation coefficient
     */
    public Double3 kR = Double3.ZERO;

    /**
     * the Diffuse light factor of the object material type
     */
    public Double3 kD = Double3.ZERO;

    /**
     * the specular light factor of the object material type
     */
    public Double3 kS = Double3.ZERO;

    /**
     * the shininess factor of the object material type
     */
    public int nShininess = 0;

    // Parameters for blur glass
    /**
     * for the number of rays
     */
    public int numOfRays = 1;
    /**
     * The distance of the glass from the body
     */
    public double blurGlassDistance = 1;

    /**
     * The area on which we will throw rays
     */
    public double  blurGlassRadius = 1;

    /**
     * for field initialization
     *
     * @param kT the parameter
     * @return the field
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }


    /**
     * for field initialization
     *
     * @param kT the parameter from double type
     * @return the field
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * for field initialization
     *
     * @param kR the parameter
     * @return the field
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }


    /**
     * for field initialization
     *
     * @param kR the parameter from type double
     * @return the field
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * set KD function - the diffuse light factor
     *
     * @param kD light factor (Double3)
     * @return the field
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set KD function - the diffuse light factor
     *
     * @param kD light factor (double)
     * @return the field
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * set kS function - the specular light factor
     *
     * @param kS light factor (Double3)
     * @return the field
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set kS function the specular light factor
     *
     * @param kS light factor (double)
     * @return the field
     */
    public Material setKs(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Set the shininess factor of the material
     *
     * @param nShininess shininess factor of the material (int)
     * @return this (Material)
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the parameters for blur glass rendering.
     *
     * @param numOfRays The number of rays to set.
     * @param distance  The distance to set.
     * @param radius    The radius to set.
     * @return This Material object.
     * @throws IllegalArgumentException if any of the parameters is invalid.
     */
    public Material setBlurGlass(int numOfRays, double distance, double radius) {
        if (numOfRays < 1 || distance <= 0 || radius <= 0)
             throw new IllegalArgumentException("Illegal argument in setBlurGlass");

        this.numOfRays = numOfRays;
        this.blurGlassDistance = distance;
        this.blurGlassRadius = radius;

        return this;
    }

}
