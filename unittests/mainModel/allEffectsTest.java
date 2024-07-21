//package mainModel;
//
//import static java.awt.Color.*;
//
//import geometries.*;
//
//import lighting.AmbientLight;
//import lighting.DirectionalLight;
//import lighting.PointLight;
//import lighting.SpotLight;
//import org.junit.jupiter.api.Test;
//import primitives.*;
//
//import renderer.Camera;
//import renderer.ImageWriter;
//import renderer.SimpleRayTracer;
//import scene.Scene;
//
//public class allEffectsTest {
//    private Scene scene = new Scene("Test scene");
//
//    @Test
//    public void testAllAffects() {
//
//
//        //scene.setIsAABB(true);
//        scene._geometries.add( //
//                new Triangle(new Point(0, 0, -115), new Point(0, 75, -75),
//                        new Point(-75, 0, -75)).setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKs(0.5).setNShininess(60).setKr(0.2)),
//                new Plane(new Point(0, 0, -115), new Point(10, 0, -115), new Point(0, -10, -115)).setMaterial(new Material().setKs(0.8).setKd(0.1).setNShininess(60).setKr(0.5)).setEmission(new Color(20, 20, 20)),
//                new Triangle(new Point(0, 0, -115), new Point(0, 75, -75),
//                        new Point(75, 0, -75)).setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKs(0.5).setNShininess(60).setKr(0.2)),
//                new Triangle(new Point(0, 0, -115), new Point(-30, -120, -75),
//                        new Point(75, 0, -75)).setEmission(new Color(20, 20, 20)).setMaterial(new Material().setKs(0.5).setNShininess(60).setKr(0.2)),
//                new Sphere(new Point(0, 0, -88), 20).setEmission(new Color(GRAY)).setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(30).setKr(0.95)),
//                new Polygon(new Point(-59, -62, -94), new Point(-59, -38, -94), new Point(-41, -38, -94), new Point(-41, -62, -94)).setEmission(new Color(magenta)),
//                new Sphere(new Point(-50, -50, -94), 15).setEmission(new Color(BLUE)).setMaterial(new Material().setKd(0.2).setKs(0.2).setNShininess(30).setKt(0.75)),
//                new Tube(new Ray(new Point(-50, -100, -115), new Vector(1, 1, 0)), 6).setEmission(new Color(darkGray)).setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(60).setKr(0.2)),
//                new Triangle(new Point(-59, -62, -94), new Point(-59, -38, -94), new Point(-55, -55, -107)).setEmission(new Color(magenta)).setMaterial(new Material().setKd(0.7).setKs(0.2).setNShininess(30).setKt(0.9)),
//                new Triangle(new Point(-59, -38, -94), new Point(-41, -38, -94), new Point(-55, -55, -107)).setEmission(new Color(magenta)).setMaterial(new Material().setKd(0.7).setKs(0.2).setNShininess(30).setKt(0.9)),
//                new Triangle(new Point(-41, -38, -94), new Point(-41, -62, -94), new Point(-55, -55, -107)).setEmission(new Color(magenta)).setMaterial(new Material().setKd(0.7).setKs(0.2).setNShininess(30).setKt(0.9)),
//                new Triangle(new Point(-41, -62, -94), new Point(-59, -62, -94), new Point(-55, -55, -107)).setEmission(new Color(magenta)).setMaterial(new Material().setKd(0.7).setKs(0.5).setNShininess(30).setKt(0.9))
//        );
//
//        scene._lights.add( //
//                new SpotLight(new Color(cyan), new Point(-100, -100, 900), new Vector(1, 1, -6)) //
//                        .setKl(0.0000004).setKq(0.000000006));
//
//        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
//
//        final Camera.Builder camera = Camera.getBuilder()
//                .setDirection(new Vector(0.3, 0.6, -0.45).normalize(), new Vector(-2, 1, 0).normalize())
//                .setLocation(new Point(-300, -600, 350))
//                .setVpSize(150, 150)
//                .setVPDistance(1000)
//                .setImageWriter(new ImageWriter("allAffects", 900, 900)) //
//                .setRayTracer(new SimpleRayTracer(scene))
//                .setMultithreading(3)
//                .setDebugPrint(0.1);
//                camera.build()
//                .renderImage()
//                .writeToImage();
//
//    }
//
//}