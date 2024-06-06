package primitives;

/**
 * A class for a material
 */
public class Material {
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
}
