package geometries;

import primitives.Point;

/** A class that represents a triangle */
public class Triangle extends Polygon {

    /**
     * constructor
     * @param p1 For the first point
     * @param p2 For the second point
     * @param p3 For the third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2,p3);
    }
}
