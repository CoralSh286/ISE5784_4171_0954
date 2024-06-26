//package mainModel;
//
//import geometries.*;
//import lighting.PointLight;
//import lighting.SpotLight;
//import org.junit.jupiter.api.Test;
//import primitives.*;
//import renderer.Camera;
//import renderer.ImageWriter;
//import renderer.SimpleRayTracer;
//import scene.Scene;
//
//import java.util.Random;
//
//import static java.awt.Color.*;
//
//public class mainModel {
//
//    private Scene scene;
//
//    @Test
//    public void room() {
//
//        scene = new Scene("Test scene snell");
//
//        final Camera.Builder camera = Camera.getBuilder()
//                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0));
//
//        scene._geometries.add(
//                new Polygon(
//                        // Represents the floor of the room
//                        new Point(-60.0, -50.0, 200.0),
//                        new Point(60.0, -50.0, 200.0),
//                        new Point(60.0, -50.0, -200.0),
//                        new Point(-60.0, -50.0, -200.0)
//                )
//                        .setEmission(new Color(79,21,7).scale(5).add(new Color(WHITE).reduce(4)).add(new Color(GRAY).reduce(4)))
//                        .setMaterial(new Material()
//                                .setKs(0.3).setKd(0.7))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(-60.0, -49.6, 200.0),
//                        new Point(-55.0, -49.6, 200.0),
//                        new Point(-55.0, -49.6, -200.0),
//                        new Point(-60.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(-50.0, -49.6, 200.0),
//                        new Point(-45.0, -49.6, 200.0),
//                        new Point(-45.0, -49.6, -200.0),
//                        new Point(-50.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(-40.0, -49.6, 200.0),
//                        new Point(-35.0, -49.6, 200.0),
//                        new Point(-35.0, -49.6, -200.0),
//                        new Point(-40.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(-30.0, -49.6, 200.0),
//                        new Point(-25.0, -49.6, 200.0),
//                        new Point(-25.0, -49.6, -200.0),
//                        new Point(-30.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(-20.0, -49.6, 200.0),
//                        new Point(-15.0, -49.6, 200.0),
//                        new Point(-15.0, -49.6, -200.0),
//                        new Point(-20.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(-10.0, -49.6, 200.0),
//                        new Point(-5.0, -49.6, 200.0),
//                        new Point(-5.0, -49.6, -200.0),
//                        new Point(-10.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(0.0, -49.6, 200.0),
//                        new Point(5.0, -49.6, 200.0),
//                        new Point(5.0, -49.6, -200.0),
//                        new Point(0.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(10.0, -49.6, 200.0),
//                        new Point(15.0, -49.6, 200.0),
//                        new Point(15.0, -49.6, -200.0),
//                        new Point(10.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(20.0, -49.6, 200.0),
//                        new Point(25.0, -49.6, 200.0),
//                        new Point(25.0, -49.6, -200.0),
//                        new Point(20.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(30.0, -49.6, 200.0),
//                        new Point(35.0, -49.6, 200.0),
//                        new Point(35.0, -49.6, -200.0),
//                        new Point(30.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(40.0, -49.6, 200.0),
//                        new Point(45.0, -49.6, 200.0),
//                        new Point(45.0, -49.6, -200.0),
//                        new Point(40.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the floor z of the room
//                        new Point(50.0, -49.6, 200.0),
//                        new Point(55.0, -49.6, 200.0),
//                        new Point(55.0, -49.6, -200.0),
//                        new Point(50.0, -49.6, -200.0)
//                )
//                        .setEmission(new Color(0,0,0))
//                        .setMaterial(new Material()
//                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//                ,
//                new Polygon(
//                        // Represents the ceiling of the room
//                        new Point(-60.0, 59.9, 200.0),
//                        new Point(60.0, 59.9, 200.0),
//                        new Point(60.0, 59.9, -200.0),
//                        new Point(-60.0, 59.9, -200.0)
//                )
//                        .setEmission(new Color(WHITE).scale(0.8))
//                        .setMaterial(new Material()
//                                .setKr(0.1).setKs(0.3).setKd(0.7))
//                ,
//                new Polygon(
//                        // Represents the wall on the positive Z-axis side of the room
//                        new Point(-60.0, -50.0, -200.0),
//                        new Point(60.0, -50.0, -200.0),
//                        new Point(60.0, 60.0, -200.0),
//                        new Point(-60.0, 60.0, -200.0)
//                )
//                        .setEmission(new Color(YELLOW).scale(4).add(new Color(WHITE).reduce(1.4)))
//                        .setMaterial(new Material()
//                                .setKs(0.3).setKd(0.7))
//                ,
//                new Plane(new Point(0,0,10000),new Vector(0,0,1)).setEmission(new Color(YELLOW).scale(4).add(new Color(WHITE).reduce(1.4)))
//                        .setMaterial(new Material()
//                                .setKs(0.3).setKd(0.7))
//                ,
//                new Polygon(
//                        // Represents the mirror
//                        new Point(-20.0, -35.0, -199.0),
//                        new Point(20.0, -35.0, -199.0),
//                        new Point(20.0, 20.0, -199.0),
//                        new Point(-20.0, 20.0, -199.0)
//                )
//                        .setEmission(new Color(GRAY))
//                        .setMaterial(new Material()
//                                .setKs(0.3).setKd(0.7).setKr(0.4))
//                ,
//                new Polygon(
//                        // Represents the wall on the positive y-axis side of the room
//
//                        new Point(-60.0, -50.0, 200.0),
//                        new Point(-60.0, -50.0, -200.0),
//                        new Point(-60.0, 60.0, -200.0),
//                        new Point(-60.0, 60.0, 200.0)
//                )
//                        .setEmission(new Color(GREEN).scale(4).add(new Color(WHITE).reduce(1.4)))
//                        .setMaterial(new Material()
//                                .setKs(0.3).setKd(0.7)),
//
//
//                new Polygon(
//                        // Represents the wall on the negative Y-axis side of the room
//                        new Point(60.0, -50.0, 200.0),
//                        new Point(60.0, -50.0, -200.0),
//                        new Point(60.0, 60.0, -200.0),
//                        new Point(60.0, 60.0, 200.0)
//                )
//                        .setEmission(new Color(GREEN).scale(4).add(new Color(WHITE).reduce(1.4)))
//                        .setMaterial(new Material()
//                                .setKs(0.3).setKd(0.7)
//                        ),
//
//
//
//                new Sphere(
//                        new Point(0, 50, -50), 3d)
//                        .setEmission(new Color(YELLOW).add(new Color(WHITE).reduce(7)))
//                        .setMaterial(
//                                new Material()
//                                        .setKd(0.8)
//                                        .setKs(0.6)
//                                        .setKt(0.09).setKr(0.4).setNShininess(100)
//                                ),
//
//                // Table top
//                new Polygon(
//                        new Point(-30.0, -30.0, 50.0),
//                        new Point(30.0, -30.0, 50.0),
//                        new Point(30.0, -30.0, -50.0),
//                        new Point(-30.0, -30.0, -50.0)
//                ).setMaterial(new Material().setKs(0.3).setKd(0.7).setKr(0.4)
//                        )
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//
//                ,
//                // Table top
//                new Polygon(
//                        new Point(-30.0, -32.0, 50.0),
//                        new Point(30.0, -32.0, 50.0),
//                        new Point(30.0, -32.0, -50.0),
//                        new Point(-30.0, -32.0, -50.0)
//                ).setMaterial(new Material().setKs(0.3).setKd(0.7).setKr(0.4)
//                        )
//
//                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//
//                ,
//                // Table top
//                new Polygon(
//                        new Point(-30.0, -30.0, 50.0),
//                        new Point(30.0, -30.0, 50.0),
//                        new Point(30.0, -32.0, 50.0),
//                        new Point(-30.0, -32.0, 50.0)
//                ).setMaterial(new Material().setKs(0.3).setKd(0.7).setKr(0.4)
//                        )
//
//                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//
//                ,
//                // Table top
//                new Polygon(
//                        new Point(-30.0, -30.0, -50.0),
//                        new Point(30.0, -30.0, -50.0),
//                        new Point(30.0, -32.0, -50.0),
//                        new Point(-30.0, -32.0, -50.0)
//                ).setMaterial(new Material().setKs(0.3).setKd(0.7).setKr(0.4)
//                        )
//
//                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//
//                ,
//                // Table top
//                new Polygon(
//                        new Point(-30.0, -32.0, 50.0),
//                        new Point(-30.0, -30.0, 50.0),
//                        new Point(-30.0, -30.0, -50.0),
//                        new Point(-30.0, -32.0, -50.0)
//                ).setMaterial(new Material().setKs(0.3).setKd(0.7).setKr(0.4)
//                        )
//
//                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//
//                ,
//                // Table top
//                new Polygon(
//                        new Point(30.0, -32.0, 50.0),
//                        new Point(30.0, -30.0, 50.0),
//                        new Point(30.0, -30.0, -50.0),
//                        new Point(30.0, -32.0, -50.0)
//                ).setMaterial(new Material().setKs(0.3).setKd(0.7).setKr(0.4)
//                        )
//
//                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg left depth --
//                new Polygon(
//                        new Point(25.0, -30.0, 47.0),
//                        new Point(25.0, -50.0, 47.0),
//                        new Point(27.0, -50.0, 47.0),
//                        new Point(27.0, -30.0, 47.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg left depth +-
//                new Polygon(
//                        new Point(25.0, -30.0, -47.0),
//                        new Point(25.0, -50.0, -47.0),
//                        new Point(27.0, -50.0, -47.0),
//                        new Point(27.0, -30.0, -47.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg right depth +-
//                new Polygon(
//                        new Point(-25.0, -30.0, -47.0),
//                        new Point(-25.0, -50.0, -47.0),
//                        new Point(-27.0, -50.0, -47.0),
//                        new Point(-27.0, -30.0, -47.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg right depth --
//                new Polygon(
//                        new Point(-25.0, -30.0, 47.0),
//                        new Point(-25.0, -50.0, 47.0),
//                        new Point(-27.0, -50.0, 47.0),
//                        new Point(-27.0, -30.0, 47.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg left depth -+
//                new Polygon(
//                        new Point(25.0, -30.0, 48.0),
//                        new Point(25.0, -50.0, 48.0),
//                        new Point(27.0, -50.0, 48.0),
//                        new Point(27.0, -30.0, 48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg left depth ++
//                new Polygon(
//                        new Point(25.0, -30.0, -48.0),
//                        new Point(25.0, -50.0, -48.0),
//                        new Point(27.0, -50.0, -48.0),
//                        new Point(27.0, -30.0, -48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg right depth ++
//                new Polygon(
//                        new Point(-25.0, -30.0, -48.0),
//                        new Point(-25.0, -50.0, -48.0),
//                        new Point(-27.0, -50.0, -48.0),
//                        new Point(-27.0, -30.0, -48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg right depth --
//                new Polygon(
//                        new Point(-25.0, -30.0, 48.0),
//                        new Point(-25.0, -50.0, 48.0),
//                        new Point(-27.0, -50.0, 48.0),
//                        new Point(-27.0, -30.0, 48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg side
//                new Polygon(
//                        new Point(25.0, -30.0, 48.0),
//                        new Point(25.0, -50.0, 47.0),
//                        new Point(27.0, -50.0, 47.0),
//                        new Point(27.0, -30.0, 48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg side
//                new Polygon(
//                        new Point(25.0, -30.0, -48.0),
//                        new Point(25.0, -50.0, -47.0),
//                        new Point(27.0, -50.0, -47.0),
//                        new Point(27.0, -30.0, -48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg side
//                new Polygon(
//                        new Point(-25.0, -30.0, -48.0),
//                        new Point(-25.0, -50.0, -47.0),
//                        new Point(-27.0, -50.0, -47.0),
//                        new Point(-27.0, -30.0, -48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                //leg side
//                new Polygon(
//                        new Point(-25.0, -30.0, 48.0),
//                        new Point(-25.0, -50.0, 47.0),
//                        new Point(-27.0, -50.0, 47.0),
//                        new Point(-27.0, -30.0, 48.0)
//                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))
//
//                        .setEmission(new Color(139, 69, 19))
//                        .setMaterial(new Material()
//                                .setKd(0.7).setKs(0.3))
//                ,
//                new Sphere(new Point(0,-30,0),5d)
//                        .setEmission(new Color(BLUE))
//                        .setMaterial(
//                                new Material()
//                                        .setKd(0.7)
//                                        .setKs(0.3)
//                                        .setKt(0.9)
//                        )
//                    ,
//
//
//            new Polygon(
//                // Represents the floor z of the room
//                    new Point(-500, -50, 1500),
//                    new Point(500, -50, 1500),
//                    new Point(500, -50, -1500),
//                    new Point(-500, -50, -1500)
//        )
//                .setEmission(new Color(199, 135, 82))
//                .setMaterial(new Material()
//                        .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4))
//
//        );
//
//            int[] randomX = new int[100];
//            Random random = new Random();
//            int[] randomY = new int[100];
//            int[] randomZ = new int[100];
//
//            for (int i = 0; i < randomX.length; i++) {
//                    randomX[i] = random.nextInt(-500, 500);// Generates a random integer
//                    randomZ[i] = random.nextInt(-1000, 1000);
//                    randomY[i] = random.nextInt(100, 300);
//            }
//
//            for (int i = 0; i < 100; ++i) {
//
//                    scene._geometries
//                            .add(new Sphere(new Point(randomX[i], randomY[i],  randomZ[i]),2)
//                                    .setEmission(new Color(WHITE).reduce(2.2))
//                                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setNShininess(301).setKt(0.3)));
//
//            }
//
//
//        camera.setLocation(new Point(10, 10, 1800))
//                .setVPDistance(1000)
//                .setVpSize(200, 200)
//                .setRayTracer(new SimpleRayTracer(scene))
//                .setImageWriter(new ImageWriter("the room for the diamond", 600, 600))
//                .setMultithreading(0)
//                .build()
//                .renderImage()
//                .writeToImage();
//    }
//
//}
//
//
//
//

package mainModel;

import static org.junit.jupiter.api.Assertions.*;
import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Sphere;
import lighting.DirectionalLight;
import lighting.SpotLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;

import scene.*;

//class minip2 {

//    @Test
//    void MiniProject2Test() {
//
//
//
//    }

//    @Test
//    void testManualBvh() {
//
//        final Camera.Builder camera = Camera.getBuilder()
//                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0));
//
//        long startTime = System.currentTimeMillis();
//
//
//        long endTime = System.currentTimeMillis();
//        long executionTime = endTime - startTime;
//
//        System.out.println("Test execution time: " + executionTime + " milliseconds");
//
//        Color yellowColor = new Color(YELLOW);
//        Material sphereMaterial = new Material().setKd(0.8).setKs(0.2).setNShininess(200).setKr(0.1);
//
//        Scene scene = new Scene("Test scene");
//
//        for (int col1 = -80; col1 < 80; col1 += 5 * 16) {
//            var geos1 = new Geometries();
//            for (int row1 = -40; row1 < 40; row1 += 5 * 8) {
//                var geos2 = new Geometries();
//                for (int col2 = col1; col2 < col1 + 5 * 16; col2 += 5 * 8) {
//                    var geos3 = new Geometries();
//                    for (int row2 = row1; row2 < row1 + 5 * 8; row2 += 5 * 4) {
//                        var geos4 = new Geometries();
//                        for (int col3 = col2; col3 < col2 + 5 * 8; col3 += 5 * 4) {
//                            var geos5 = new Geometries();
//                            for (int row3 = row2; row3 < row2 + 5 * 4; row3 += 5 * 2) {
//                                var geos6 = new Geometries();
//                                for (int col4 = col3; col4 < col3 + 5 * 4; col4 += 5 * 2) {
//                                    var geos7 = new Geometries();
//                                    for (int row4 = row3; row4 < row3 + 5 * 2; row4 += 5) {
//                                        var geos8 = new Geometries();
//                                        for (int col5 = col4; col5 < col4 + 5 * 2; col5 += 5)
//                                            geos8.add(new Sphere(new Point(col5, row4, 800), 0.5)
//                                                    .setEmission(yellowColor).setMaterial(sphereMaterial));
//                                        geos7.add(geos8);
//                                    }
//                                    geos6.add(geos7);
//                                }
//                                geos5.add(geos6);
//                            }
//                            geos4.add(geos5);
//                        }
//                        geos3.add(geos4);
//                    }
//                    geos2.add(geos3);
//                }
//                geos1.add(geos2);
//            }
//            scene._geometries.add(geos1);
//        }
//        scene._lights.add(new DirectionalLight(new Color(YELLOW), new Vector(0, 1, 0)));
//        scene._lights
//                .add(new SpotLight(new Color(WHITE), new Point(100, 100, 1000), new Vector(1, -1, 1)).setKq(0.000001));
//
//        camera.setLocation(new Point(3, 0, 1000))
//                .setVPDistance(1000)
//                .setVpSize(1000, 1000)
//                .setRayTracer(new SimpleRayTracer(scene))
//                .setImageWriter(new ImageWriter("SpheresDofManualBvh", 1000, 1000))
//                .setMultithreading(0)
//                .build()
//                .renderImage()
//                .writeToImage();
//    }
//}


