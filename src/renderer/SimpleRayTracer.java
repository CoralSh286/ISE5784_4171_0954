package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 *A class that inherits from the RayTracerBase class and implements the method
 */
public class SimpleRayTracer extends RayTracerBase{

    /**
     * constructor
     *
     * @param scene A scene where the department is initialized
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections  = this._scene._geometries.findIntersections(ray);
        if (intersections == null)
            return this._scene._background;
        return calcColor(ray.findClosestPoint(intersections));
    }

    /**
     * Get the color of an intersection point
     * @param point point of intersection
     * @return Color of the intersection point
     */
    private Color calcColor(Point point) {
        return this._scene._ambientLight.getIntensity();
    }

//    /**
//     * Get the color of an intersection point
//     *
//     * @param point point of intersection
//     * @return Color of the intersection point
//     */
    //stage 6
//    private Color calcColor(Intersectable.GeoPoint point, Ray ray) {
//        return this._scene._ambientLight.getIntensity()
//                .add(point.geometry.getEmission())
//                .add(calcLocalEffects(point, ray));
//    }
//
//    //stage 6
//    private Color calcLocalEffects(Intersectable.GeoPoint intersection, Ray ray) {
//
//        Vector v = ray.getDir();
//        Vector n = intersection.geometry.getNormal(intersection.point);
//        double nv = alignZero(n.dotProduct(v));
//
//        if (nv == 0) return Color.BLACK;
//        int nShininess = intersection.geometry.getMaterial().nShininess;
//
//        Double3 kd = intersection.geometry.getMaterial().kD;
//        Double3 ks = intersection.geometry.getMaterial().kS;
//
//        Color color = Color.BLACK;
//        for (LightSource lightSource : _scene._lights) {
//
//            Vector l = lightSource.getL(intersection.point);
//            double nl = alignZero(n.dotProduct(l));
//
//            if (nl * nv > 0) { // sign(nl) == sing(nv)
//                Color lightIntensity = lightSource.getIntensity(intersection.point);
//                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
//                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
//            }
//        }
//        return color;
//    }
//
//    //stage 6
//    private Color calcDiffusive(Double3 kd, Vector l, Vector n, Color lightIntensity) {
//        double lN = l.normalize().dotProduct(n.normalize());
//        return lightIntensity.scale(kd.scale(Math.abs(lN)));
//    }
//
//    //stage 6
//    private Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, double nShininess, Color lightIntensity) {
//
//        Vector r = l.subtract(n.scale(l.dotProduct(n)).scale(2)).normalize();
//        double max = Math.max(0, -v.dotProduct(r));
//
//        double maxNs = Math.pow(max, nShininess);
//        Double3 ksMaxNs = ks.scale(maxNs);
//
//        return lightIntensity.scale(ksMaxNs);
//    }
//
//    //stage 6
//    @Override
//    public Color traceRay(Ray ray) {
//
//        List<Intersectable.GeoPoint> intersections = this._scene._geometries.findGeoIntersections(ray);
//        return intersections == null
//                ? this._scene._background
//                : calcColor(ray.findClosestGeoPoint(intersections), ray);
//    }
}

