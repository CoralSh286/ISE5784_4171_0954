package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;

import java.util.*;

/**
 * Composite class for all geometries object implementing {@link Intersectable}
 */
public class Geometries extends Intersectable {

    /**
     * If true, then the geometries class will use axis aligned bounding box in the calculations, and vice versa.
     */
    public static boolean axisAlignedBoundingBox = true;

    /**
     * List of geometries
     */
    private List<Intersectable> _intersectables = new LinkedList<>();

    /**
     * Empty constructor
     */
    public Geometries() {
    }

//    /**
//     * constructor
//     *
//     * @param geometries Some geometries
//     */
//    public Geometries(Intersectable... geometries) {
//        add(geometries);
//    }

//    /**
//     * Adding geometrics to the list
//     *
//     * @param geometries Some geometries
//     */
//    public void add(Intersectable... geometries) {
//        Collections.addAll(_intersectables, geometries);
//    }

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

    //MP2
    public Geometries(Intersectable... geometries) {
        if (axisAlignedBoundingBox) {
            this._intersectables = List.of(geometries);

            // create a list of all the geometries in the scene
            List<Intersectable> geos = new ArrayList<>(List.of(geometries));

            // a list of all the boundable geometries in the scene
            List<Boundable> boundables = new LinkedList<>();

            // move all the boundables from geos to boundables list
            for (Intersectable g : geometries) {
                if (g instanceof Boundable) {
                    geos.remove(g);
                    boundables.add((Boundable) g);
                }
            }

            // create an axis aligned bounding box tree for the boundable geometries and add the tree to the geometry list
            geos.add(AxisAlignedBoundingBox.createTree(boundables));
            this._intersectables = geos;
        } else
            this._intersectables = List.of(geometries);
    }

    /**
     * add Intersectable object to our composite
     * @param geometries a list of none specified length of Intersectable object
     */
    public void add(Intersectable... geometries) {
        if (axisAlignedBoundingBox) {
            //create a list of all the geometries already existing in the scene
            List<Intersectable> geos = new ArrayList<>();
            //add all the un-boundable ones to the ones that are bounded in boxes
            for (Intersectable item : this._intersectables) {
                if (item instanceof Boundable)
                    geos.addAll(((Boundable) item).getAxisAlignedBoundingBox().getAllGeometries());
                else
                    geos.add(item);

            }
            // Add all new geometries to the existing ones
            geos.addAll(Arrays.asList(geometries));

            //a list of all the boundable geometries in the scene
            List<Boundable> boundables = new ArrayList<>();

            //move all the boundables from geos to boundables list
            for (Intersectable g : geometries) {
                if (g instanceof Boundable) {
                    geos.remove(g);
                    boundables.add((Boundable) g);
                }
            }
            // create an axis aligned bounding box tree for the boundable geometries and add the tree to the geometry list
            AxisAlignedBoundingBox axisAlignedBoundingBox = AxisAlignedBoundingBox.createTree(boundables);
            if (axisAlignedBoundingBox != null)
                geos.add(axisAlignedBoundingBox);
            this._intersectables = geos;
        } else
            this._intersectables.addAll(Arrays.asList(geometries));
    }

    /**
     * @return the list of geometry in the Composite patter
     */
    public List<Intersectable> getGeometries() {
        return _intersectables;
    }

}


