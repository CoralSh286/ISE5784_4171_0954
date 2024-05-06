package primitives;


/** A class that represents a vector by a point in space */
public class Vector extends Point {

    /** Creating a constructor that accepts 3 coordinates as parameters */
    public Vector(double x, double y, double z) {
        super(x, y, z);

        if(_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ZERO vector is not allowed");
    }


    /** Creates a constructor that accepts as a parameter a point */
    public Vector(Double3 xyz) {
        super(xyz);

        if(_xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("ZERO vector is not allowed");
    }


    /** Connecting 2 vectors and returning a new vector */
    public Vector add(Vector vector) {

        return new Vector(_xyz.add(vector._xyz));
    }


    /** Multiplication of a vector by a scalar */
    public Vector scale(double num) {

        return new Vector(_xyz.scale(num));
    }


    /** Scalar product between 2 vectors */
    public double dotProduct(Vector vector) {
        return _xyz.d1 * vector._xyz.d1 + _xyz.d2 * vector._xyz.d2 + _xyz.d3 * vector._xyz.d3;
    }


    /** Vector product between 2 vectors, Returns a vector perpendicular to both vectors */
    public Vector crossProduct(Vector vector) {
        double u1 = _xyz.d1;
        double u2 = _xyz.d2;
        double u3 = _xyz.d3;
        double v1 = vector._xyz.d1;
        double v2 = vector._xyz.d2;
        double v3 = vector._xyz.d3;

        /** Calculation of a vector product according to the formula in the course */
        return new Vector((u2*v3-v2*u3),-(u1*v3-v1*u3),(u1*v2-v1*u2));
    }


    /** Calculation of the squared length of the vector */
    public double lengthSquared(){

        /** Multiplying each coordinate by itself and adding them together */
        return _xyz.d1*_xyz.d1 + _xyz.d2*_xyz.d2 + _xyz.d3*_xyz.d3;
    }


    /** Calculate the length of the vector */
    public double length(){
        return Math.sqrt(lengthSquared());
    }


    /** Turns the vector into a normalized vector - length 1 */
    public Vector normalize(){
        double len = length();
        return new Vector(_xyz.reduce(len));
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "Vector: (" + _xyz.d1 + "," + _xyz.d2 + "," + _xyz.d3 + ")";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
