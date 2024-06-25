/**
 *
 */
package renderer;

import static java.awt.Color.*;

import geometries.*;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        scene._geometries.add(
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setNShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100)));
        scene._lights.add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVPDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        scene._geometries.add(
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setNShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setNShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene._lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                .setKl(0.00001).setKq(0.000005));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setVPDistance(10000)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        scene._geometries.add(
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(60)),
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(30).setKt(0.6)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene._lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVPDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of five objects lighted by a spot light and point light
     * to show all the effects in one picture
     */
    @Test
    public void reflectionRefractionFiveObjectsTest() {

        this.scene.setAmbientLight(new AmbientLight(new Color(YELLOW), new Double3(0.15)));

        this.scene._geometries.add( //
                new Triangle(new Point(-150, -150, -115),
                        new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setNShininess(60)), //

                new Triangle(new Point(-150, -150, -115),
                        new Point(-70, 70, -140),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5)
                                .setNShininess(60)), //

                new Sphere(new Point(60, 50, -50), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2)
                                .setNShininess(30).setKt(0.6)),

                new Sphere(new Point(-50, -100, 100), 25.7) //
                        .setEmission(new Color(green)) //
                        .setMaterial(new Material().setKd(0.002).setKs(0.2)
                                .setNShininess(30).setKt(0.9)),

                new Sphere(new Point(-50, -80, 100), 17) //
                        .setEmission(new Color(green)) //
                        .setMaterial(new Material().setKd(0.002).setKs(0.2)
                                .setNShininess(30).setKt(0.9)),

                new Sphere(new Point(30, -60, 100), 22) //
                        .setEmission(new Color(cyan)) //
                        .setMaterial(new Material().setKd(0.002).setKs(0.2)
                                .setNShininess(30).setKt(0.9)),

                new Sphere(new Point(-60, 50, 100), 10) //
                        .setEmission(new Color(yellow)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2)
                                .setNShininess(2).setKt(0.8)));

        this.scene._lights.add(new SpotLight(new Color(700, 400, 400),
                new Point(30, 25, 0),
                new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        this.scene._lights.add(new PointLight(new Color(160, 80, 240),
                new Point(-100, -100, 100))//
                .setKl(0.00000000001).setKq(0.0000000001));

        cameraBuilder.setLocation(new Point(0, 0, 2000))
                .setVPDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("reflectionRefractionFiveObjectsTest", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * the bonus for stage 7, Produces a spectacular image with many bodies.
     */
    @Test
    public void myShapeBonus() {

        final Camera.Builder bonuscameraBuilder = Camera.getBuilder()
                .setDirection(new Vector(1, 0, 0), new Vector(0, 0, 1));

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255).reduce(6), new Double3(0.15)));
        scene._lights.add(new SpotLight(new Color(RED), new Point(-300, 6, 10), new Vector(1, 0, 0)));

        double angle = 0;
        double height = 0;

        scene._geometries.add(new Plane(new Point(-4, 4, 0), new Vector(0, 0, 1))
                .setMaterial(new Material().setKd(0.8).setKs(0.6).setNShininess(100).setKt(0.7).setKr(0.5)));

        java.awt.Color[] colors = {YELLOW, RED, ORANGE, BLUE};

        for (int i = 25; i < 200; ++i) {
            int colorIndex = i % colors.length;

            scene._geometries
                    .add(new Sphere(new Point(i / 25.0 * Math.cos(angle), i / 25.0 * Math.sin(angle), height), 0.5)
                            .setEmission(new Color(colors[colorIndex]).reduce(2.2))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(80).setKt(0.3)));

            angle += Math.PI / 15.0;
            height += 0.15;
        }

        java.awt.Color[] colors2 = {BLUE, GREEN, PINK, BLACK, RED, GRAY};

        height = 10;
        for (int i = 25; i < 100; ++i) {
            int colorIndex = i % colors2.length;

            scene._geometries.add(new Sphere(new Point(i / 25.0 * Math.cos(angle), i * Math.sin(angle), height), 0.3)
                    .setEmission(new Color(colors2[colorIndex]).reduce(2.2))
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(80).setKt(0.3)));

            angle += Math.PI / 30.0;
            height += 0.5;
        }

        height = 10;
        for (int i = 25; i < 300; ++i) {

            scene._geometries.add(new Sphere(new Point(i / 25.0 * Math.cos(angle), i * Math.sin(angle), height), 0.05)
                    .setEmission(new Color(WHITE))
                    .setMaterial(new Material().setKd(1).setKs(1d).setNShininess(100).setKt(1)));

            angle += Math.PI / 60.0;
            height += 0.1 % 50;
        }

        scene._lights.add(new SpotLight(new Color(255, 255, 255).reduce(2), new Point(-150, 0, 5), new Vector(1, 0, 0)));
        scene._lights.add(new SpotLight(new Color(GREEN).reduce(2), new Point(50, 0, 5), new Vector(1, 0, 0)));

        scene.setBackground(new Color(BLUE).reduce(TRANSLUCENT));

        ImageWriter imageWriter = new ImageWriter("myShapeBonus", 500, 500);

        bonuscameraBuilder.setLocation(new Point(-330, 0, 5))
                .setVPDistance(1000)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene))
                .setImageWriter(imageWriter)
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Test for MP1 with the improvement Blurry Glass
     */
    @Test
    public void testBlurryGlass0() {
        blurryGlass(0, "blurryGlass-0");
    }

    /**
     * Test for MP1 with the improvement Blurry Glass
     */
    @Test
    public void testBlurryGlassStream() {
        blurryGlass(-1, "blurryGlass-stream");
    }

    /**
     * Test for MP1 with the improvement Blurry Glass
     */
    @Test
    public void testBlurryGlass3() {
        blurryGlass(3, "blurryGlass-3");
    }

    /**
     * Test for MP1 with the improvement Blurry Glass
     */
    @Test
    public void testBlurryGlass2() {
        blurryGlass(2, "blurryGlass-2");
    }

    private void blurryGlass(int threads, String file) {

        Vector vTo = new Vector(0, 1, 0);

        scene.setAmbientLight(new AmbientLight(new Color(gray).reduce(2), new Double3(0.15)));

        final Camera.Builder newcameraBuilder = Camera.getBuilder()
                .setDirection(vTo, new Vector(0, 0, 1));

        // Add geometries to the scene
        for (int i = -4; i < 6; i += 2) {
            scene._geometries.add(
                    new Sphere(new Point(5 * i, -1.50, -3), 3)
                            .setEmission(new Color(255, 102, 102).reduce(4).reduce(2.2))
                            .setMaterial(new Material().setKd(0.2).setKs(1d).setNShininess(80).setKt(0)),

                    new Sphere(new Point(5 * i, 5, 3), 3)
                            .setEmission(new Color(102, 255, 178).reduce(2.2))
                            .setMaterial(new Material().setKd(0.2).setKs(1d).setNShininess(80).setKt(0)),

                    new Sphere(new Point(5 * i, -8, -8), 3)
                            .setEmission(new Color(255, 255, 153).reduce(2.2))
                            .setMaterial(new Material().setKd(0.2).setKs(1d).setNShininess(80).setKt(0)),

                    new Polygon(
                            new Point(5 * i - 4, -5, -11),
                            new Point(5 * i - 4, -5, 5),
                            new Point(5 * i + 4, -5, 5),
                            new Point(5 * i + 4, -5, -11))
                            .setEmission(new Color(255, 245, 235).reduce(2.5))
                            .setMaterial(new Material().setKd(0.001).setKs(0.002).setNShininess(1).setKt(0.95)
                                    .setBlurGlass(i == 4 ? 1 : 100, 0.3 * (i + 5), 1))
            );
        }

        scene._geometries.add(new Plane(new Point(1, 10, 1), new Point(2, 10, 1), new Point(5, 10, 0))
                .setEmission(new Color(240, 248, 255).reduce(3))
                .setMaterial(new Material().setKd(0.2).setKs(0d).setNShininess(0).setKt(0))
        );

        // Add lights to the scene
        scene._lights.add(new DirectionalLight(new Color(WHITE).reduce(1.3), new Vector(-0.4, 1, 0)));
        scene._lights.add(new SpotLight(new Color(WHITE).reduce(2), new Point(20.43303, -7.37104, 13.77329),
                new Vector(-20.43, 7.37, -13.77)).setKl(0.6));

        newcameraBuilder.setLocation(new Point(0, -230, 0).add(vTo.scale(-13)))
                .setVPDistance(1000)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene))
                .setImageWriter(new ImageWriter(file, 1000, 1000))
                .setMultithreading(threads)
                .build()
                .renderImage()
                .writeToImage();
    }

}




