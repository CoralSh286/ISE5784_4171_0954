package primitives;

import java.util.Objects;


/** A class for representing a beam by a point and a vector */
public class Ray {

    /** Creating the point */
    private final Point _p0;

    /** Creating the vector */
    private final Vector _dir;

    /**
     * constructor
     * @param p0 for a point
     * @param dir for a vector
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();
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
}
