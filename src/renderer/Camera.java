package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {

    private Point _p0;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vRight;
    private double _distance = 0.0;
    private double _width = 0.0;
    private double _height = 0.0;

    private Camera(){

    }

    public Builder getBuilder(){
        return new Builder(this);
    }

    public Ray constructRay(int nX, int nY, int j, int i){

        //view plane center Point
        Point Pc = _p0.add(_vTo.scale(_distance));

        //pixels ratios
        double Rx = _width / nX;
        double Ry = _height / nY;

        //Pij point[i,j] in view-plane coordinates
        Point Pij = Pc;

        //delta values for moving on the view=plane
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (!isZero(Xj)) {
            Pij = Pij.add(_vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(_vUp.scale(Yi));
        }

        // vector from camera's eye in the direction of point(i,j) in the viewplane
        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0, Vij);
    }

    public static class Builder{

        private final Camera _camera;

        public Builder(Camera camera) {
            _camera = camera;
        }

        public Builder setVpDistance(double distance) {
            _camera._distance = distance;
            return this;
        }

        public Builder setVpSize(double width, double height) {
            _camera._width = width;
            _camera._height =height;

            return this;
        }

        public Builder setLocation(Point p) {
            _camera._p0 = p;
            return this;
        }

        public Builder setDirection(Vector vTo, Vector vUp) {
            if(vTo.dotProduct(vUp) != 0)
                throw new IllegalArgumentException("They are not perpendicular to each other");
            else{
                _camera._vTo = vTo.normalize();
                _camera._vUp = vUp.normalize();
                return this;
            }
        }

    }

    }

