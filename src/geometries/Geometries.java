package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Composite class for all geometries object implementing {@link Intersectable}
 */
public class Geometries extends Intersectable {

    /**
     * List of geometries
     */
    private final List<Intersectable> _intersectables = new LinkedList<>();

    /**
     * Empty constructor
     */
    public Geometries() {
    }

    /**
     * constructor
     *
     * @param geometries Some geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adding geometrics to the list
     *
     * @param geometries Some geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(_intersectables, geometries);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> points = null;

        //go threw all the geometries and add their intersections
        for (var geometry : _intersectables) {
            var currentIntersection = geometry.findGeoIntersections(ray);
            if (currentIntersection != null) {
                if (points == null)
                    points = new LinkedList<>(currentIntersection);
                else
                    points.addAll(currentIntersection);
            }
        }

        return points;
    }


}
