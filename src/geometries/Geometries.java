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
public class Geometries implements Intersectable {

    /**
     * List of geometries
     */
    private final List<Intersectable> _intersectables = new LinkedList<>();

    /**
     * Empty constructor
     */
    public Geometries(){}

    /**
     * constructor
     * @param geometries Some geometries
     */
    public Geometries(Intersectable... geometries){
        add(geometries);
     }

    /**
     * Adding geometrics to the list
     * @param geometries Some geometries
     */
    public void add(Intersectable... geometries){
        Collections.addAll(_intersectables,geometries);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        LinkedList<Point> points=null;
        for(var geometry: _intersectables){
            var geometryList=geometry.findIntersections(ray);
            if(geometryList!=null){
                if(points==null){
                    points=new LinkedList<>();
                }
                points.addAll(geometryList);
            }
        }
        return points;
    }
}
