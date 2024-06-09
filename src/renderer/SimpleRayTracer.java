//package renderer;
//
//import geometries.Intersectable;
//import geometries.Intersectable.GeoPoint;
//import lighting.LightSource;
//import primitives.*;
//import scene.Scene;
//
//import java.util.List;
//
//import static primitives.Util.alignZero;
//
///**
// * A class that inherits from the RayTracerBase class and implements the method
// */
//public class SimpleRayTracer extends RayTracerBase {
//
//    //stage 7
//    private static final Double3 INITIAL_K = Double3.ONE;
//    //stage 7
//    private static final int MAX_CALC_COLOR_LEVEL = 10;
//    //stage 7
//    private static final double MIN_CALC_COLOR_K = 0.001;
//
//
//    //stage 7
////    private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector l, Vector n, double nl) {
////        Vector lightDirection = l.scale(-1);
////        Vector espVector = n.scale(nl < 0 ? DELTA : -DELTA);
////        Point point = geoPoint.point.add(espVector);
////        Ray lightRay = new Ray(point, lightDirection);
////        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
////        if (intersections == null || intersections.isEmpty())
////            return true;
////        double distance = light.getDistance(geoPoint.point);
////        Vector direction = light.getL(geoPoint.point).normalize();
////        for (GeoPoint geoIntersection : intersections) {
////            Vector directionIntersection = light.getL(geoIntersection.point).normalize();
////            if ((light.getDistance(geoIntersection.point) < distance) && (directionIntersection.dotProduct(direction) > 0))
////                if ((geoIntersection.geometry.getKt().equals(new Double3(0))))
////                    return false;
////        }
////        return true;
////    }
//
//    private boolean unshaded(GeoPoint geoPoint, LightSource light, Vector l, Vector n) {
//        Vector lightDirection = l.scale(-1);
//        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
//        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
//        if (intersections == null || intersections.isEmpty())
//            return true;
//        double distance = light.getDistance(geoPoint.point);
//        Vector direction = light.getL(geoPoint.point).normalize();
//        for (GeoPoint geoIntersection : intersections) {
//            Vector directionIntersection = light.getL(geoIntersection.point).normalize();
//            if ((light.getDistance(geoIntersection.point) < distance) && (directionIntersection.dotProduct(direction) > 0))
//                if ((geoIntersection.geometry.getKt().equals(new Double3(0))))
//                    return false;
//        }
//        return true;
//    }
//
//    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
//        Vector lightDirection = l.scale(-1); // from point to light source
//        Ray lightRay = new Ray(geopoint.point, lightDirection, n); //build ray with delta
//        double lightDistance = light.getDistance(geopoint.point);
//
//        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay);
//        if (intersections == null){
//            return Double3.ONE; //no intersections
//        }
//        Double3 ktr = Double3.ONE;
//        for (GeoPoint gp : intersections) {
//            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
//                ktr = ktr.product(gp.geometry.getMaterial().kT); //the more transparency the less shadow
//                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
//            }
//        }
//        return ktr;
//    }
//
//
//    /**
//     * constructor
//     *
//     * @param scene A scene where the department is initialized
//     */
//    public SimpleRayTracer(Scene scene) {
//        super(scene);
//    }
//
//
////    /**
////     * Get the color of an intersection point
////     *
////     * @param point point of intersection
////     * @param ray   for the ray
////     * @return Color of the intersection point
////     */
////    private Color calcColor(Intersectable.GeoPoint point, Ray ray) {
////        return this._scene._ambientLight.getIntensity()
////                .add(point.geometry.getEmission())
////                .add(calcLocalEffects(point, ray));
////    }
//
//    //stage 7
//    /**
//     * Get the color of an intersection point
//     *
//     * @param point point of intersection
//     * @return Color of the intersection point
//     */
//    private Color calcColor(GeoPoint point, Ray ray) {
//        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
//                .add(this._scene._ambientLight.getIntensity());
//    }
//
//    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
//        Color color = geoPoint.geometry.getEmission()
//                .add(calcLocalEffects(geoPoint, ray, k));
//
//        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
//    }
//
//    @Override
//    public Color traceRay(Ray ray) {
//
//        var intersections = this._scene._geometries.findGeoIntersections(ray);
//        return intersections == null
//                ? this._scene._background
//                : calcColor(ray.findClosestGeoPoint(intersections), ray);
//    }
//
////    /**
////     * This method calculates the local effects (diffuse and specular) of lighting at a given intersection point.
////     *
////     * @param intersection The intersection point and geometry information.
////     * @param ray          The ray that intersects with the geometry.
////     * @return The color result of local lighting effects.
////     */
////    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray) {
////
////        Vector v = ray.getDir();
////        Vector n = intersection.geometry.getNormal(intersection.point);
////        double nv = alignZero(n.dotProduct(v));
////
////        if (nv == 0) return Color.BLACK;
////        int nShininess = intersection.geometry.getMaterial().nShininess;
////
////        Double3 kd = intersection.geometry.getMaterial().kD;
////        Double3 ks = intersection.geometry.getMaterial().kS;
////
////        Color color = Color.BLACK;
////        for (LightSource lightSource : _scene._lights) {
////
////            Vector l = lightSource.getL(intersection.point);
////            double nl = alignZero(n.dotProduct(l));
////
////            if (nl * nv > 0) { // sign(nl) == sing(nv)
////                if (unshaded(intersection, lightSource, l, n, nl)) {
////                    Color lightIntensity = lightSource.getIntensity(intersection.point);
////                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
////                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
////                }
////            }
////        }
////        return color;
////    }
//
//    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
//        Vector v = ray.getDir();
//
//        Vector n = gp.geometry.getNormal(gp.point);
//        double nv = alignZero(n.dotProduct(v));
//
//        if (nv == 0) {
//            return Color.BLACK;
//        }
//
//        Material material = gp.geometry.getMaterial();
//
//        Color color = Color.BLACK;
//        for (LightSource lightSource : _scene._lights){
//            Vector l = lightSource.getL(gp.point);
//            double nl = alignZero(n.dotProduct(l));
//
//            if (nl * nv > 0) { // sign(nl) == sing(nv)
//                Double3 ktr = transparency(gp, lightSource, l, n); //intensity of shadow
//                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
//                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
//                    color = color.add(iL.scale(calcDiffusive(material, nl)),
//                            iL.scale(calcSpecular(material, n, l, v)));
//                }
//            }
//        }
//        return color;
//    }
//
//    //stage 7
//    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
//        Material material = gp.geometry.getMaterial();
//        return calcColorGlobalEffect(constructRefractedRay(gp, ray), level ,material.kR, k)
//                .add(calcColorGlobalEffect(constructReflectedRay(gp, ray), level ,material.kT, k));}
//
//    //stage 7
//    private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
//        Double3 kkx = k.product(kx);
//        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
//        GeoPoint gp = findClosestIntersection(ray);
//        return (gp == null ? _scene._background :calcColor(gp, ray, level - 1, kkx)).scale(kx);
//    }
//
//    //stage 7
//    private Ray constructReflectedRay(GeoPoint gp, Ray ray) {
//        Vector v = ray.getDir();
//        Vector n = gp.geometry.getNormal(gp.point);
//        double nv = alignZero(v.dotProduct(n));
//        Vector r = v.subtract(n.scale(2d * nv)).normalize();
//        return new Ray(gp.point, r, n); //use the constructor with the normal for moving the head
//    }
//
//    //stage 7
//    private Ray constructRefractedRay(GeoPoint gp, Ray ray) {
//        return new Ray(gp.point, ray.getDir(), gp.geometry.getNormal(gp.point));
//    }
//
//    //stage 7
//    private GeoPoint findClosestIntersection(Ray ray){
//        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(ray);
//        return ray.findClosestGeoPoint(intersections);
//    }
//
//    //    /**
////     * This method calculates the diffuse component of lighting at a given point.
////     *
////     * @param kd             The diffuse reflection coefficient.
////     * @param l              The direction vector from the light source to the point.
////     * @param n              The normal vector at the point.
////     * @param lightIntensity The intensity of the light at the point.
////     * @return The color result of the diffuse component.
////     */
////    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
////        double lN = l.normalize().dotProduct(n.normalize());
////        return lightIntensity.scale(kd.scale(Math.abs(lN)));
////    }
////
////    /**
////     * This method calculates the specular component of lighting at a given point.
////     *
////     * @param ks             The specular reflection coefficient.
////     * @param l              The direction vector from the light source to the point.
////     * @param n              The normal vector at the point.
////     * @param v              The direction vector of the viewer (or camera).
////     * @param nShininess     The shininess factor of the material.
////     * @param lightIntensity The intensity of the light at the point.
////     * @return The color result of the specular component.
////     */
////    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity) {
////
////        Vector r = l.subtract(n.scale(l.dotProduct(n)).scale(2)).normalize();
////        double max = Math.max(0, -v.dotProduct(r));
////
////        double maxNs = Math.pow(max, nShininess);
////        Double3 ksMaxNs = ks.scale(maxNs);
////
////        return lightIntensity.scale(ksMaxNs);
////    }
//
//    //stage 7
//    /**
//     * Calculation of specular light component
//     *
//     * @param material Attenuation coefficient for specular light component
//     * @param n        normal to point
//     * @param l        direction vector from light to point
//     * @param v        direction of ray shot to point
//     * @return Color of specular light component
//     */
//    private Double3 calcSpecular(Material material, Vector n, Vector l, Vector v) {
//        Vector r = l.subtract(n.scale(2 * l.dotProduct(n))).normalize();
//        return material.kS.scale(Math.pow(Math.max(0, v.scale(-1).dotProduct(r)), material.nShininess));
//    }
//
//    //stage 7
//    /**
//     * Calculation of diffusion light component
//     *
//     * @param material normal to point
//     * @param nl       dot product between n-normal to point and l-direction vector from light to point
//     * @return Color of diffusion light component
//     */
//    private Double3 calcDiffusive(Material material, double nl) {
//        return material.kD.scale(Math.abs(nl));
//    }
//
//}
//




package renderer;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class SimpleRayTracer extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Get color of the intersection of the ray with the scene
     *
     * @param ray Ray to trace
     * @return Color of intersection
     */
    @Override
    public Color traceRay(Ray ray) {

        List<GeoPoint> intersections = this._scene._geometries.findGeoIntersections(ray);

        if (intersections == null)
            return this._scene._background;

        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);

        return calcColor(closestPoint, ray);
    }


    /**
     * Get the color of an intersection point
     *
     * @param point point of intersection
     * @return Color of the intersection point
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return calcColor(point, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(this._scene._ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission()
                .add(calcLocalEffects(geoPoint, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Double3 kr = material.kR;
        Double3 kkr = k.product(kr);

        Vector n = gp.geometry.getNormal(gp.point);

        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflected(gp, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);

            if (reflectedPoint == null){
                return color.add(this._scene._background);
            }
            color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
        }

        Double3 kt = material.kT;
        Double3 kkt = k.product(kt);

        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefracted(gp, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);

            if (refractedPoint == null) {
                return color.add(this._scene._background);
            }
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }
        return color;
    }

    private Ray constructReflected(GeoPoint gp, Ray ray) {
        Vector v = ray.getDir();

        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(v.dotProduct(n));

        // r = v - 2 * (v * n) * n
        Vector r = v.subtract(n.scale(2d * nv)).normalize();

        return new Ray(gp.point, r, n); //use the constructor with the normal for moving the head
    }

    private Ray constructRefracted(GeoPoint gp, Ray ray) {
        return new Ray(gp.point, ray.getDir(), gp.geometry.getNormal(gp.point));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Vector v = ray.getDir();

        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0) {
            return Color.BLACK;
        }

        Material material = gp.geometry.getMaterial();

        Color color = Color.BLACK;
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) { // sign(nl) == sing(nv)
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

    private Double3 transparency(GeoPoint geopoint, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); //build ray with delta
        double lightDistance = light.getDistance(geopoint.point);

        var intersections = this._scene._geometries.findGeoIntersections(lightRay);
        if (intersections == null){
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
        return material.kS.scale(Math.pow(Math.max(0, v.scale(-1).dotProduct(r)), material.nShininess));
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
     * find the closest intersection to the starting point of the ray
     *
     * @param ray the ray that intersect with the geometries of the scene
     * @return the geoPoint that is point is the closest point to the starting point of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = this._scene._geometries.findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(intersections);
    }
}