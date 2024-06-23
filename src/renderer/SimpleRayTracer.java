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
     * A field that indicates whether to use the feature or not
     */
    public boolean blurryGlass = true;

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
     * @param gp    The intersection point.
     * @param ray   The ray that intersected with the geometry.
     * @param level The recursion level.
     * @param k     The attenuation factor.
     * @return The color at the intersection point.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double vn = v.dotProduct(n);
        if (Util.isZero(vn))
            return Color.BLACK;

        Color color = calcLocalEffects(gp, ray, k).add(gp.geometry.getEmission());

        return 1 == level ? color : color.add(calcGlobalEffects(gp, v, n, vn, level, k));
    }


    @Override
    public Color traceRay(Ray ray) {
        var point = this.findClosestIntersection(ray);
        return point == null ? _scene._background : calcColor(point, ray);
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
     * Calculate global effects such as reflection and refraction
     *
     * @param gp    the intersection point
     * @param v     the incident ray direction vector
     * @param n     the normal vector at the intersection point
     * @param vn    the dot product of the incident ray direction vector and the normal vector
     * @param level recursion level
     * @param k     coefficient
     * @return the color with global effects applied
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, Vector n, double vn, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        Ray reflectedRay = constructReflectedRay(gp.point, v, n, vn);
        Ray refractedRay = constructRefractedRay(gp.point, v, n);
        return calcGlobalEffect(material, n, refractedRay, level, material.kT, k)
                .add(calcGlobalEffect(material, n, reflectedRay, level, material.kR, k));
    }

    /**
     * Calculate global effects such as reflection or refraction
     *
     * @param material the material of the intersected geometry
     * @param n        the normal vector at the intersection point
     * @param ray      the incident ray
     * @param level    recursion level
     * @param kx       coefficient
     * @param k        coefficient
     * @return the color with global effects applied
     */
    private Color calcGlobalEffect(Material material, Vector n, Ray ray, int level, Double3 kx, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;

        var rays = ray.generateBeam(n, material.blurGlassRadius, material.blurGlassDistance, blurryGlass ? material.numOfRays : 1);
        return calcAverageColor(rays, level - 1, kkx).scale(kx);
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
     * Calculate the reflected ray
     *
     * @param pointGeo the geometric point where the ray starts
     * @param v        the vector representing the original direction of the ray
     * @param n        the normal vector at the point of impact
     * @param vn       the dot product of the original vector and the normal vector
     * @return a new Ray representing the reflected ray
     */
    private Ray constructReflectedRay(Point pointGeo, Vector v, Vector n, double vn) {
        // 𝒓=𝒗 −𝟐∙(𝒗∙𝒏)∙𝒏
        Vector r = v.subtract(n.scale(2d * vn));
        return new Ray(pointGeo, r, n);
    }

    /**
     * Calculate refracted ray
     *
     * @param pointGeo the geometric point where the ray starts
     * @param v        the incident ray direction vector
     * @param n        the normal vector at the point of refraction
     * @return a new Ray representing the refracted ray
     */
    private Ray constructRefractedRay(Point pointGeo, Vector v, Vector n) {
        return new Ray(pointGeo, v, n);
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

    /**
     * Get the average color of the intersection of rays
     *
     * @param rays  list of rays
     * @param level recursion level
     * @param kkx   coefficient
     * @return the average color of the intersection of the rays
     */
    Color calcAverageColor(List<Ray> rays, int level, Double3 kkx) {
        Color color = Color.BLACK;

        for (Ray ray : rays) {
            GeoPoint intersection = findClosestIntersection(ray);
            color = color.add(intersection == null ? _scene._background : calcColor(intersection, ray, level - 1, kkx));
        }

        return color.reduce(rays.size());
    }

}
