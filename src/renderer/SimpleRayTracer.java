package renderer;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A class that inherits from the RayTracerBase class and implements the method
 */
public class SimpleRayTracer extends RayTracerBase {

    //stage 7
    private static final double INITIAL_K = 1.0;
    //stage 7
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    //stage 7
    private static final double MIN_CALC_COLOR_K = 0.001;
    //stage 7
    private static final double DELTA = 0.1;

    //stage 7
    private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1);
        Vector espVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = geoPoint.point.add(espVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null || intersections.isEmpty())
            return true;
        double distance = light.getDistance(geoPoint.point);
        Vector direction = light.getL(geoPoint.point).normalize();
        for (GeoPoint geoIntersection : intersections) {
            Vector directionIntersection = light.getL(geoIntersection.point).normalize();
            if ((light.getDistance(geoIntersection.point) < distance) && (directionIntersection.dotProduct(direction) > 0))
                if ((geoIntersection.geometry.getKt().equals(new Double3(0))))
                    return false;
        }
        return true;
    }


    /**
     * constructor
     *
     * @param scene A scene where the department is initialized
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }


//    /**
//     * Get the color of an intersection point
//     *
//     * @param point point of intersection
//     * @param ray   for the ray
//     * @return Color of the intersection point
//     */
//    private Color calcColor(Intersectable.GeoPoint point, Ray ray) {
//        return this._scene._ambientLight.getIntensity()
//                .add(point.geometry.getEmission())
//                .add(calcLocalEffects(point, ray));
//    }

    //stage 7
    /**
     * calculating the color of a specific point, taking into account the lightning,
     * transparency of the point itself and other affects of the surrounding are of the point in space
     *
     * @param geoPoint calculate the color of this point
     * @param ray the ray that the geoPoint on it.
     * @return for now - the ambient light's intensity
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        if (1 == level)
            return color;
        return color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    //stage 7
    private Color calcColor(GeoPoint geoPoint, Ray ray)
    {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K)).add(_scene._ambientLight.getIntensity());
    }

    @Override
    public Color traceRay(Ray ray) {

        var intersections = this._scene._geometries.findGeoIntersections(ray);
        return intersections == null
                ? this._scene._background
                : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * This method calculates the local effects (diffuse and specular) of lighting at a given intersection point.
     *
     * @param intersection The intersection point and geometry information.
     * @param ray          The ray that intersects with the geometry.
     * @return The color result of local lighting effects.
     */
    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray, Double3 k) {

        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0) return Color.BLACK;
        int nShininess = intersection.geometry.getMaterial().nShininess;

        Double3 kd = intersection.geometry.getMaterial().kD;
        Double3 ks = intersection.geometry.getMaterial().kS;

        Color color = Color.BLACK;
        for (LightSource lightSource : _scene._lights) {

            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(intersection, lightSource, l, n, nl)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        return calcColorGlobalEffect(constructRefractedRay(gp, ray), material.kR,level, k).add(calcColorGlobalEffect(constructReflectedRay(gp, ray), material.kT,level, k));}

    private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene._background :calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    private Ray constructReflectedRay(GeoPoint gp, Ray ray) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(v.dotProduct(n));
        Vector r = v.subtract(n.scale(2d * nv)).normalize();
        return new Ray(gp.point, r, n); //use the constructor with the normal for moving the head
    }

    private Ray constructRefractedRay(GeoPoint gp, Ray ray) {
        return new Ray(gp.point, ray.getDir(), gp.geometry.getNormal(gp.point));
    }

    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * This method calculates the diffuse component of lighting at a given point.
     *
     * @param kd             The diffuse reflection coefficient.
     * @param l              The direction vector from the light source to the point.
     * @param n              The normal vector at the point.
     * @param lightIntensity The intensity of the light at the point.
     * @return The color result of the diffuse component.
     */
    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
        double lN = l.normalize().dotProduct(n.normalize());
        return lightIntensity.scale(kd.scale(Math.abs(lN)));
    }

    /**
     * This method calculates the specular component of lighting at a given point.
     *
     * @param ks             The specular reflection coefficient.
     * @param l              The direction vector from the light source to the point.
     * @param n              The normal vector at the point.
     * @param v              The direction vector of the viewer (or camera).
     * @param nShininess     The shininess factor of the material.
     * @param lightIntensity The intensity of the light at the point.
     * @return The color result of the specular component.
     */
    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity) {

        Vector r = l.subtract(n.scale(l.dotProduct(n)).scale(2)).normalize();
        double max = Math.max(0, -v.dotProduct(r));

        double maxNs = Math.pow(max, nShininess);
        Double3 ksMaxNs = ks.scale(maxNs);

        return lightIntensity.scale(ksMaxNs);
    }


}

