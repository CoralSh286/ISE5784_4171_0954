package primitives;

import java.util.Objects;

import static primitives.Util.isZero;


/**
 * A class for representing a beam by a point and a vector
 */
public class Ray {

    /**
     * Creating the point
     */
    private final Point _p0;

    /**
     * Creating the vector
     */
    private final Vector _dir;

    /**
     * constructor
     *
     * @param p0  for a point
     * @param dir for a vector
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
    }

    /**
     * getter function
     *
     * @return the point _p0
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * getter function
     *
     * @return the direction vector
     */
    public Vector getDir() {
        return _dir;
    }

    @Override
    public String toString() {
        return "Ray= " + "_p0: " + _p0 + ", _dir: " + _dir + '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return (object instanceof Ray other)
                && this._p0.equals(other._p0)
                && this._dir.equals(other._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    /**
     * get Point at specific distance in the ray's direction
     *
     * @param t is a distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t) {
        return isZero(t) ? _p0 : _p0.add(_dir.scale(t));
    }
}
