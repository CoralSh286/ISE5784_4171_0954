//
//package mainModel;
//
//import geometries.*;
//import lighting.*;
//import org.junit.jupiter.api.Test;
//import primitives.*;
//import renderer.*;
//import scene.*;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class PictureTest {
//
//    private List<Sphere> initializeBalls() {
//
//        List<Sphere> balls = new LinkedList<>();
//
//        // Material material = new
//        // Material().setKd(0.4).setKs(1).setShininess(50).setKt(0).setKr(0.5).setKs(0.5);
//        Material material1 = new Material().setKd(0.4).setKs(1d).setNShininess(100).setKt(0).setKr(0.9);
//
//        for (double i = 0, j = 6; i < 500; i += 1, j += 0.04) {
//            double x = Math.cos(i);
//            double y = Math.sin(i);
//            balls.add((Sphere) new Sphere(new Point(i * y, i * x, (i * 2 - 150)), j).setMaterial(material1)
//                    .setEmission(new Color(255, 0, 0)));
//        }
//        return balls;
//    }
//
//    @Test
//    public void pictureTest() {
//        Scene scene = new Scene("final picture").setBackground(new Color(0, 0, 0));
//
//        final Camera.Builder camera = Camera.getBuilder()
//                .setDirection(new Vector(0, 1, 0), new Vector(0, 0, 1));
//
//
//        Material material = new Material().setKd(0.4).setKs(1d).setNShininess(50).setKt(0).setKr(0.5).setKs(0.5);
//        Material material1 = new Material().setKd(0.4).setKs(1d).setNShininess(100).setKt(0).setKr(0);
//        SpotLight light = new SpotLight(new Color(255, 255, 255), new Point(0, -50, 25), new Vector(0, 2, -1));
//        SpotLight light2 = new SpotLight(new Color(255, 255, 255), new Point(0, 50, 25), new Vector(0, -2, -1));
//        light.setKc(0).setKl(0.01).setKq(0.05);
//        // light.setNarrowBeam(5);
//        light2.setKc(0).setKl(0.01).setKq(0.05);
//        // light2.setNarrowBeam(5);
//
//        DirectionalLight directionalLight1 = new DirectionalLight(new Color(100, 100, 100), new Vector(0, 0, -1));
//        DirectionalLight directionalLight2 = new DirectionalLight(new Color(100, 100, 100), new Vector(1, 0, 0));
//        DirectionalLight directionalLight3 = new DirectionalLight(new Color(100, 100, 100), new Vector(-1, 0, 0));
//        PointLight pointLight = new PointLight(new Color(255, 255, 255), new Point(200, 50, -100));
//
//        // scene.lights.add(light);
//        scene._lights.add(directionalLight1);
//        scene._lights.add(directionalLight2);
//        scene._lights.add(directionalLight3);
//        scene._lights.add(pointLight);
//
//        Sphere sphere = new Sphere(new Point(0, 0, 220), 130);
//
//        Sphere sphere3 = new Sphere(new Point(200, -100, 50), 100);
//        Sphere sphere4 = new Sphere(new Point(-200, -100, 50), 100);
//
//        sphere.setMaterial(material1).setEmission(new Color(102, 178, 255));
//
//        // sphere.setEmission(new Color(255, 0, 0)).setMaterial(material1);
//        sphere3.setEmission(new Color(169, 50, 50));
//        sphere4.setEmission(new Color(50, 50, 169));
//
//        Sphere sphere2 = new Sphere(new Point(0, 0, 0), 70);
//        sphere2.setEmission(new Color(100, 69, 0)).setMaterial(material);
//
//        Polygon sqr1 = new Polygon(new Point(-10000, 10000, -10000), new Point(-10000, 10000, 10000),
//                new Point(10000, 10000, 10000), new Point(10000, 10000, -10000));
//        // .setEmission(new Color(255, 215, 0))
//        sqr1.setMaterial(material1).setEmission(new Color(0, 0, 0));
//
//        Polygon sqrt2 = new Polygon(new Point(-150, 100, -100), new Point(-150, 100, 100), new Point(-150, -100, 100),
//                new Point(-150, -100, -100));
//        // .setEmission(new Color(0, 0, 0))
//        sqrt2.setMaterial(material);
//
//        Polygon sqrt3 = new Polygon(new Point(150, -100, -100), new Point(150, -100, 100), new Point(150, 100, 100),
//                new Point(150, 100, -100));
//        // .setEmission(new Color(0, 0, 0))
//        sqrt3.setMaterial(material);
//
//        Polygon sqrt4 = new Polygon(new Point(150, -100, 150), new Point(-100, -100, 150), new Point(-100, 100, 150),
//                new Point(100, 100, 150));
//        // .setEmission(new Color(0, 0, 0))
//        sqrt4.setMaterial(material);
//
//        Polygon sqrt5 = new Polygon(new Point(100, -100, -150), new Point(-100, -100, -150), new Point(-100, 100, -150),
//                new Point(100, 100, -150));
//        // .setEmission(new Color(0, 0, 0));
//        sqrt5.setMaterial(material);
//
//        Plane pln = new Plane(new Point(100, -100, -150), new Vector(0, 0, 1));
//        // pln.setEmission(new Color(255,255,255));
//        // .setEmission(new Color(0, 0, 0));
//        pln.setMaterial(material).setEmission(new Color(0, 0, 0));
//        List<Sphere> balls = initializeBalls();
//        scene._geometries.add(pln, sphere);
//        for (Sphere item : balls) {
//            scene._geometries.add(item);
//        }
//
//        scene._geometries.add(new Polygon(
//                new Point(5  - 4, -5, -11),
//                new Point(5 - 4, -5, 5),
//                new Point(5 + 4, -5, 5),
//                new Point(5 + 4, -5, -11))
//                .setEmission(new Color(255, 245, 235).reduce(2.5))
//                .setMaterial(new Material().setKd(0.001).setKs(0.002).setNShininess(1).setKt(0.95)
//                        .setBlurGlass( 100, 0.3 * 5, 1)));
//
//        camera.setLocation(new Point(0, -600, 10))
//                .setVPDistance(100)
//                .setVpSize(150, 150)
//                .setRayTracer(new SimpleRayTracer(scene))
//                .setImageWriter(new ImageWriter("final picture", 1000, 1000))
//                .setMultithreading(0)
//                .build()
//                .renderImage()
//                .writeToImage();
//    }
//}
