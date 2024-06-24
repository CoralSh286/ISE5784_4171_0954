
package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

import static primitives.Util.random;

/**
 * Camera class represents a camera in the 3D space
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width = 0d;
    private double height = 0d;
    private double distance = 0d;

    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    //MP2
    private int threadsCount = 0; // -2 auto, -1 range/stream, 0 no threads, 1+ number of threads
    private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private double printInterval = 1; // printing progress percentage interval

    //NP2


    /**
     * Camera getter
     *
     * @return the location of the camera
     */
    @SuppressWarnings("unused")
    public Point getP0() {
        return p0;
    }

    /**
     * Camera getter
     *
     * @return the up direction of the camera
     */
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Camera getter
     *
     * @return the direction of the camera
     */
    @SuppressWarnings("unused")
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Camera getter
     *
     * @return the right direction of the camera
     */
    @SuppressWarnings("unused")
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Camera getter
     *
     * @return the width of the view plane
     */
    @SuppressWarnings("unused")
    public double getWidth() {
        return width;
    }

    /**
     * Camera getter
     *
     * @return the height of the view plane
     */
    @SuppressWarnings("unused")
    public double getHeight() {
        return height;
    }

    /**
     * Camera getter
     *
     * @return the distance between the camera and the view plane
     */
    @SuppressWarnings("unused")
    public double getDistance() {
        return distance;
    }

    /**
     * Camera builder
     */
    public static class Builder {

        private final Camera camera = new Camera();

        /**
         * Set the location of the camera
         *
         * @param p0 the location of the camera
         * @return the builder object itself for chaining
         */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * Set the direction of the camera
         *
         * @param vTo the direction of the camera
         *            (the vector from the camera to the "look-at" point)
         * @param vUp the up direction of the camera
         *            (the vector from the camera to the up direction)
         * @return the direction
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!Util.isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("vTo and vUp must be orthogonal");
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * Set the size of the view plane
         *
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return the size
         */
        public Builder setVpSize(double width, double height) {
            if (width <= 0 || height <= 0)
                throw new IllegalArgumentException("width and height must be positive");
            camera.width = width;
            camera.height = height;
            return this;
        }


        /**
         * Set the distance between the camera and the view plane
         *
         * @param distance the distance between the camera and the view plane
         * @return the distance
         */
        public Builder setVPDistance(double distance) {
            if (distance <= 0)
                throw new IllegalArgumentException("distance from camera to view must be positive");
            camera.distance = distance;
            return this;
        }

        /**
         * Initializes the method imageWriter
         *
         * @param imageWriter for creating the file
         * @return this field for the camera
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Initializes the method that combining a scene and its color
         *
         * @param rayTracer The parameter for adding the color to the point
         * @return this field for the camera
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        public Builder setMultithreading(int threads) {
            if (threads < -2)
                throw new IllegalArgumentException("Multithreading must be -2 or higher");
            if (threads >= -1)
                camera.threadsCount = threads;
            else { // == -2
                int cores = Runtime.getRuntime().availableProcessors() - camera.SPARE_THREADS;
                camera.threadsCount = cores <= 2 ? 1 : cores;
            }
            return this;
        }

        public Builder setDebugPrint(double interval) {
            camera.printInterval = interval;
            return this;
        }

        /**
         * Build the camera
         *
         * @return the camera
         */
        public Camera build() {
            String className = "Camera";
            String description = "values not set";

            if (camera.p0 == null)
                throw new MissingResourceException(className, description, "p0");
            if (camera.vUp == null)
                throw new MissingResourceException(className, description, "vUp");
            if (camera.vTo == null)
                throw new MissingResourceException(className, description, "vTo");
            if (camera.rayTracer == null)
                throw new MissingResourceException(className, description, "imageWriter");
            if (camera.imageWriter == null)
                throw new MissingResourceException(className, description, "rayTracer");
            if (Util.alignZero(camera.width) == 0d)
                throw new MissingResourceException(className, description, "width");
            if (Util.alignZero(camera.height) == 0d)
                throw new MissingResourceException(className, description, "height");
            if (Util.alignZero(camera.distance) == 0d)
                throw new MissingResourceException(className, description, "distance");

            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            if (!Util.isZero(camera.vTo.dotProduct(camera.vRight)) ||
                    !Util.isZero(camera.vTo.dotProduct(camera.vUp)) ||
                    !Util.isZero(camera.vRight.dotProduct(camera.vUp)))
                throw new IllegalArgumentException("vTo, vUp and vRight must be orthogonal");

            if (camera.vTo.length() != 1 || camera.vUp.length() != 1 || camera.vRight.length() != 1)
                throw new IllegalArgumentException("vTo, vUp and vRight must be normalized");

            if (camera.width <= 0 || camera.height <= 0)
                throw new IllegalArgumentException("width and height must be positive");

            if (camera.distance <= 0)
                throw new IllegalArgumentException("distance from camera to view must be positive");

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException ignore) {
                return null;
            }
        }
    }

    /**
     * Camera constructor
     */
    Camera() {
    }

    /**
     * Builder getter
     *
     * @return the camera builder
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * construct a ray through a pixel
     *
     * @param nX the number of pixels in the x direction
     * @param nY the number of pixels in the y direction
     * @param j  the x index of the pixel
     * @param i  the y index of the pixel
     * @return the ray that passes through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        //view plane center Point
        Point pc = p0.add(vTo.scale(distance));

        //pixels ratios (pixels width and height)
        double rx = width / nX;
        double ry = height / nY;

        //Pij point[i,j] in view-plane coordinates
        Point pij = pc;

        //delta values for moving on the view plane
        double xj = (j - (nX - 1) / 2d) * rx;
        double yi = -(i - (nY - 1) / 2d) * ry;

        //if not on zero coordinates add the delta distance
        // to the center of point (i,j)
        // to reach it
        if (!Util.isZero(xj))
            pij = pij.add(vRight.scale(xj));
        if (!Util.isZero(yi))
            pij = pij.add(vUp.scale(yi));

        // vector from camera's eye in the direction of point(i,j) in the view plane
        return new Ray(p0, pij.subtract(p0));
    }


//    /**
//     * Casts a ray for each pixel
//     *
//     * @return a camera
//     */
//    public Camera renderImage() {
//        if (this.imageWriter == null)
//            throw new UnsupportedOperationException("Missing imageWriter");
//        if (this.rayTracer == null)
//            throw new UnsupportedOperationException("Missing rayTracerBase");
//        int x = this.imageWriter.getNx();
//        int y = this.imageWriter.getNy();
//        for (int i = 0; i < x; i++) {
//            for (int j = 0; j < y; j++) {
//                castRay(i, j, x, y);
//            }
//        }
//        return this;
//    }

    /**
     * Creates a network of lines
     *
     * @param interval for the amount of pixels in a square
     * @param color    for the color
     * @return A camera type object
     */
    public Camera printGrid(int interval, Color color) {
        //running on the view plane
        double x = this.imageWriter.getNx();
        double y = this.imageWriter.getNy();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                //create the net
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
        return this;
    }

    /**
     * Creates the image by delegation
     */
    public void writeToImage() {
        this.imageWriter.writeToImage();
    }

    /**
     * Creates a beam through the center of the pixel
     *
     * @param i i for the latitude index
     * @param j j for the longitude index
     * @param x num of pixels on the x-axis
     * @param y num of pixels on the y-axis
     */
    private void castRay(int i, int j, int x, int y) {
        Ray ray = constructRay(x, y, j, i);
        this.imageWriter.writePixel(j, i, this.rayTracer.traceRay(ray));
        Pixel.pixelDone();
    }

    public Camera renderImage() {
        int nX = this.imageWriter.getNx();
        int nY = this.imageWriter.getNy();
        Pixel.initialize(nY, nX, printInterval);
        if (threadsCount == 0)
            for (int i = 0; i < nY; ++i)
                for (int j = 0; j < nX; ++j)
                    castRay(i, j, nX, nY);
        else if (threadsCount == -1) {
            IntStream.range(0, nY).parallel() //
                    .forEach(i -> IntStream.range(0, nX).parallel() //
                            .forEach(j ->   castRay(i, j, nX, nY)));
        }
        else {
                var threads = new LinkedList<Thread>();
                while (threadsCount-- > 0)
                    threads.add(new Thread(() -> {
                        Pixel pixel;
                        while ((pixel = Pixel.nextPixel()) != null)
                            castRay(pixel.row(), pixel.col(),nX,nY);
                    }));
                for (var thread : threads) thread.start();
                try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}}
            return this;
    }

    /**
     * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
     * critical section for all the threads, and main Pixel object data is the shared data of this critical* section.<br/>
     * The function provides next pixel number each call.
     * @param target target secondary Pixel object to copy the row/column of the next pixel
     * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is* finished, any other value - the progress percentage (only when it changes)
     */
//    private synchronized int nextP(Pixel target) {
//        ++col; ++counter;
//        if (col < maxCols) {
//            target.row = this.row; target.col = this.col;
//            if (print && counter == nextCounter) {
//                ++percents; nextCounter = pixels * (percents + 1) / 100; return percents;}
//            return 0;
//        }
//        ++row;
//        if (row < maxRows) {
//            col = 0;
//            if (print && counter == nextCounter) {
//                ++percents; nextCounter = pixels * (percents + 1) / 100; return percents;}
//            return 0;
//        }
//        return -1;
//    }

}

