package renderer;

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

    private static final Double3 INITIAL_K = Double3.ONE;

    private static final int MAX_CALC_COLOR_LEVEL = 10;

    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * Checks if a point is unshaded from a specific light source.
     *
     * @param geoPoint The point to check for shading.
     * @param light    The light source.
     * @param l        The vector from the light source to the point.
     * @param n        The normal vector at the point.
     * @return {@code true} if the point is unshaded, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geoPoint.point);

        var intersections = _scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null) {
            return true;
        }

        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geoPoint.point) - lightDistance) <= 0) {
                if (gp.geometry.getKt().equals(new Double3(0))) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Calculates the transparency factor for a point with respect to a light source.
     *
     * @param geopoint The point for which transparency is calculated.
     * @param light    The light source.
     * @param l        The vector from the light source to the point.
     * @param n        The normal vector at the point.
     * @return The transparency factor as a Double3 representing (r, g, b) values.
     */
    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); //build ray with delta
        double lightDistance = light.getDistance(geopoint.point);

        var intersections = this._scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null) {
            return Double3.ONE; //no intersections
        }
        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr = ktr.product(gp.geometry.getMaterial().kT); //the more transparency the less shadow
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * constructor
     *
     * @param scene A scene where the department is initialized
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Get the color of an intersection point
     *
     * @param point point of intersection
     * @param ray   for the ray
     * @return Color of the intersection point
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(this._scene._ambientLight.getIntensity());
    }

    /**
     * Calculates the color at a given intersection point.
     *
     * @param geoPoint The intersection point.
     * @param ray      The ray that intersected with the geometry.
     * @param level    The recursion level.
     * @param k        The attenuation factor.
     * @return The color at the intersection point.
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission()
                .add(calcLocalEffects(geoPoint, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    @Override
    public Color traceRay(Ray ray) {
        var point = this.findClosestIntersection(ray);
        if (point == null) {
            return _scene._background;
        }
        return calcColor(point, ray);
    }


    /**
     * Calculates the local effects (diffuse and specular) at a given intersection point.
     *
     * @param gp  The intersection point.
     * @param ray The ray that intersected with the geometry.
     * @param k   The attenuation factor.
     * @return The color contribution from local effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;

        Material material = gp.geometry.getMaterial();
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                Double3 ktr = transparency(gp, lightSource, l, n); //intensity of shadow
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, v)));
                }
            }
        }
        return color;
    }


    /**
     * Calculates the global effects (reflection and refraction) at a given intersection point.
     *
     * @param gp    The intersection point.
     * @param ray   The ray that intersected with the geometry.
     * @param level The recursion level.
     * @param k     The attenuation factor.
     * @return The color contribution from global effects.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();

        color = color.add(calcRayEffect(gp, ray, level, k, material.kR, true));
        color = color.add(calcRayEffect(gp, ray, level, k, material.kT, false));

        return color;
    }

    /**
     * Calculates the color effect for a given ray (either reflection or refraction) at a given geo-point.
     *
     * @param gp the geo-point where the effect is calculated
     * @param ray the original ray that intersects with the geo-point
     * @param level the recursion depth level
     * @param k the attenuation coefficient from previous recursions
     * @param kEffect the reflection (kR) or refraction (kT) coefficient of the material at the geo-point
     * @param isReflection true if calculating reflection effect, false if calculating refraction effect
     * @return the color effect due to reflection or refraction
     */
    private Color calcRayEffect(GeoPoint gp, Ray ray, int level, Double3 k, Double3 kEffect, boolean isReflection) {
        if (kEffect.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        Double3 kkEffect = k.product(kEffect);
        if (kkEffect.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        Ray effectRay = isReflection ? constructReflected(gp, ray) : constructRefracted(gp, ray);
        GeoPoint effectPoint = findClosestIntersection(effectRay);
        if (effectPoint == null)
            return this._scene._background;

        return calcColor(effectPoint, effectRay, level - 1, kkEffect).scale(kEffect);
    }



    /**
     * find the closest intersection to the starting point of the ray
     *
     * @param ray the ray that intersect with the geometries of the scene
     * @return the geoPoint that is point is the closest point to the starting point of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = this._scene._geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    /**
     * Constructs a reflected ray at a given intersection point.
     *
     * @param gp  The intersection point.
     * @param ray The incident ray.
     * @return The reflected ray.
     */
    private Ray constructReflected(GeoPoint gp, Ray ray) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(v.dotProduct(n));
        // r = v - 2 * (v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();
        return new Ray(gp.point, r, n); //use the constructor with the normal for moving the head
    }


   /**
     * Constructs a refracted ray at a given intersection point.
     *
     * @param gp  The intersection point.
     * @param ray The incident ray.
     * @return The refracted ray.
     */
    private Ray constructRefracted(GeoPoint gp, Ray ray) {
        return new Ray(gp.point, ray.getDir(), gp.geometry.getNormal(gp.point));
    }


    /**
     * Calculation of specular light component
     *
     * @param material Attenuation coefficient for specular light component
     * @param n        normal to point
     * @param l        direction vector from light to point
     * @param v        direction of ray shot to point
     * @return Color of specular light component
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v) {
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n))).normalize();
        double minusVR = -v.dotProduct(r);
        return alignZero(minusVR) <= 0 ? Double3.ZERO
                : material.kS.scale(Math.pow(minusVR, material.nShininess));
    }

    /**
     * Calculation of diffusion light component
     *
     * @param material normal to point
     * @param nl       dot product between n-normal to point and l-direction vector from light to point
     * @return Color of diffusion light component
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

}
