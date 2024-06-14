/**
 *
 */
package renderer;

import static java.awt.Color.*;

import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
    /** Scene for the tests */
    private final Scene          scene         = new Scene("Test scene");
    /** Camera builder for the tests with triangles */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1),new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /** Produce a picture of a sphere lighted by a spot light */
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

    /** Produce a picture of a sphere lighted by a spot light */
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

    /** Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow */
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

        this. scene._geometries.add( //
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

//    /** Produce a picture of Newton's cradle with spheres and strings */
//    @Test
//    public void newtonsCradle() {
//        scene._geometries.add(
//                // Newton's cradle balls
//                new Sphere(new Point(240, 0, 150), 60).setEmission(new Color(DARK_GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.5)),
//                new Sphere(new Point(120, 0, 150), 60).setEmission(new Color(DARK_GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.5)),
//                new Sphere(new Point(0, 0, 150), 60).setEmission(new Color(DARK_GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.5)),
//                new Sphere(new Point(-120, 0, 150), 60).setEmission(new Color(DARK_GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.5)),
//                new Sphere(new Point(-240, 0, 150), 60).setEmission(new Color(DARK_GRAY))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(100).setKr(0.5)),
//
//                // Strings
//                new Triangle(new Point(240, 0, 500), new Point(240, 0, 150), new Point(120, 0, 150))
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(900)),
//                new Triangle(new Point(120, 0, 500), new Point(120, 0, 150), new Point(0, 0, 150))
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(900)),
//                new Triangle(new Point(0, 0, 500), new Point(0, 0, 150), new Point(-120, 0, 150))
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(900)),
//                new Triangle(new Point(-120, 0, 500), new Point(-120, 0, 150), new Point(-240, 0, 150))
//                        .setEmission(new Color(BLACK))
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(900))
//        );
//
//        scene.setAmbientLight(new AmbientLight(new Color(BLUE), new Double3(0.15)));
//        scene._lights.add(new SpotLight(new Color(255, 215, 0), new Point(-400, 1000, 1500), new Vector(400, -1000, -1500))
//                .setKl(1E-5).setKq(1.5E-7).setSharp(3));
//
//        cameraBuilder.setLocation(new Point(0, 10000, 5200)).setVPDistance(13900.13562)
//                .setVpSize(3000, 3000)
//                .setImageWriter(new ImageWriter("newtonsCradle", 500, 500))
//                .build()
//                .renderImage()
//                .writeToImage();
//    }
//
//    /** Produce a picture of 32 spheres with light */
//    @Test
//    public void spheres32() {
//        scene.setBackground(new Color(65, 105, 225));
//        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
//
//        Color c = new Color(0, 0, 0);
//        int x = -150;
//        int z = 100;
//        for (int j = 0; j < 4; j++) {
//            x = -150;
//            for (int i = 0; i < 4; i++) {
//                scene._geometries.add(new Sphere(new Point(x, 70, z), 50).setEmission(c)
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(100)));
//                x += 100;
//            }
//
//            x = -150;
//            for (int i = 0; i < 4; i++) {
//                scene._geometries.add(new Sphere(new Point(x, 170, z), 50).setEmission(c)
//                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(100)));
//                x += 100;
//            }
//            z -= 100;
//        }
//
//        scene._lights.addAll(List.of(
//                new PointLight(new Color(0, 50, 200), new Point(-200, -200, -150)).setKl(0.00001).setKq(0.000001),
//                new SpotLight(new Color(0, 50, 200), new Point(200, -200, -150), new Vector(-1, 1, 4))
//                        .setKl(0.00001).setKq(0.000005)));
//
//        cameraBuilder.setLocation(new Point(0, -620, -800)).setVPDistance(1000)
//                .setVpSize(200, 200)
//                .setImageWriter(new ImageWriter("spheres32", 600, 600))
//                .build()
//                .renderImage()
//                .writeToImage();
//    }
//
//    /** Produce a picture of 10 squares with random colors and additional shapes */
//    @Test
//    public void tenSquares() {
//        scene.setBackground(new Color(65, 105, 225));
//        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
//
//        int x = -200;
//        int y = -200;
//        for (int i = 0; i < 10; i++) {
//            scene._geometries.add(new Polygon(new Point(x, y, 0), new Point(x + 50, y, 0), new Point(x + 50, y + 50, 0), new Point(x, y + 50, 0))
//                    .setEmission(new Color(java.awt.Color.getHSBColor((float) Math.random(), 1, 1)))
//                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(30)));
//            x += 100;
//            if (x > 200) {
//                x = -200;
//                y += 100;
//            }
//        }
//
//        scene._geometries.add(new Triangle(new Point(100, 100, 0), new Point(150, 200, 0), new Point(200, 100, 0))
//                .setEmission(new Color(GREEN))
//                .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(30)));
//        scene._geometries.add(new Sphere(new Point(-100, -100, 50), 30)
//                .setEmission(new Color(RED))
//                .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(30)));
//
//        scene._lights.add(new PointLight(new Color(255, 215, 0), new Point(0, -200, -100))
//                .setKl(0.0005).setKq(0.00005));
//
//        cameraBuilder.setLocation(new Point(0, -620, -800)).setVPDistance(1000)
//                .setVpSize(200, 200)
//                .setImageWriter(new ImageWriter("tenSquares", 600, 600))
//                .build()
//                .renderImage()
//                .writeToImage();
//    }

}
