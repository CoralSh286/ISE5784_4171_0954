package mainModel;

import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

public class mainModel3 {

    @Test
    public void finalPicture() {

        final Camera.Builder camera = Camera.getBuilder()
                .setDirection(new Vector(0, 1, 0), new Vector(0, 0, -1));

        Scene scene = new Scene("Final picture");
        scene._geometries.add(

                //Walls
                new Polygon(new Point(200, 200, 1), new Point(-200, 200, 1),
                        new Point(-200, -200, 1), new Point(200, -200, 1))
                        .setEmission(new Color(30,40,50))
                        .setMaterial(new Material().setKd(0.9).setKs(0.1).setKr(0).setNShininess(10)),

                new Polygon(new Point(200, -200, 1), new Point(-200, -200, 1),
                        new Point(-250, -250, 250), new Point(250, -250, 250))
                        .setEmission(new Color(40,50,60))
                        .setMaterial(new Material().setKd(0.9).setKs(0.1).setKr(0.5).setNShininess(10)),

                new Polygon(new Point(240, 250, 200), new Point(200, 200, 0),
                        new Point(200, -200, 0), new Point(250, -250, 250))
                        .setEmission(new Color(50,60,70))
                        .setMaterial(new Material().setKd(0.9).setKs(0.1).setKr(0.5).setNShininess(10)),

                new Polygon(new Point(-240, 250, 200), new Point(-200, 200, 0),
                        new Point(-200, -200, 0), new Point(-250, -250, 250))
                        .setEmission(new Color(60,70,80))
                        .setMaterial(new Material().setKd(0.9).setKs(0.1).setKr(0.5).setNShininess(10)),

                new Polygon(new Point(240, 250, 200), new Point(200, 200, 1),
                        new Point(-200, 200, 1), new Point(-240, 250, 200))
                        .setEmission(new Color(70,80,90))
                        .setMaterial(new Material().setKd(0.9).setKs(0.1).setKr(0.5).setNShininess(10)),



                new Sphere(new Point(0, -200, 100), 20).setEmission(new Color(20,20,20))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10)),

                new Sphere(new Point(20, -170, 120), 20).setEmission(new Color(40,40,40))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setKt(0.8)),

                new Sphere(new Point(40, -140, 140), 20).setEmission(new Color(60,60,60))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setKt(0.8)),

                new Sphere(new Point(60, -110, 120), 20).setEmission(new Color(80,80,80))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10).setKt(0.8)),

                new Sphere(new Point(40, -80, 100), 20).setEmission(new Color(100,100,100))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10).setKt(0.8)),

                new Sphere(new Point(20, -50, 120), 20).setEmission(new Color(120,120,120))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10).setKt(0.1)),

                new Sphere(new Point(40, -20, 140), 20).setEmission(new Color(140,140,140))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10).setKt(0.8)),

                new Sphere(new Point(60, 10, 120), 20).setEmission(new Color(160,160,160))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10).setKt(0.8)),

                new Sphere(new Point(40, 40, 100), 20).setEmission(new Color(180,180,180))
                        .setMaterial(new Material().setKs(0.5).setKd(0.5).setNShininess(10).setKt(0.8)),


                // Mirror
                new Polygon(new Point(100,-200,100), new Point(-70,-200,40), new Point(-70,20,50),new Point(100,20,110))
                        .setMaterial(new Material().setKd(0.1).setKs(0.9).setKr(0.9))
                        .setEmission(new Color(java.awt.Color.gray)),

                // cher
                new Polygon(new Point(-200,-180,20), new Point(-200,-200, 290), new Point(-100,-200,290),new Point(-100, -180, 20))
                        .setMaterial(new Material().setKd(0.1).setKs(0.9).setKr(0.9))
                        .setEmission(new Color(java.awt.Color.gray)),

                new Triangle(new Point(-200,-200,290), new Point(-100,-200,290), new Point(-150,-240,290)),

                //head
                new Sphere(new Point(-150,0,200), 30).setEmission(new Color(181, 122, 71)),
                //neck
                new Polygon(new Point(-145,-28,190),new Point(-155,-28,200),new Point(-155,-55,200), new Point(-145,-55,190) )
                        .setEmission(new Color(181, 122, 71)),
                //body
                new Polygon(new Point(-180,-55,220),new Point(-120,-55,180),new Point(-120,-155,180), new Point(-180,-155,220) )
                        .setEmission(new Color(32, 161, 152)),

                //lower body
                new Polygon(new Point(-120,-155,180),new Point(-180,-155,220),new Point(-170,-180,220), new Point(-110,-180,180) )
                        .setEmission(new Color(32, 161, 152)),

                // Left leg
                new Polygon(new Point(-170,-180,220),new Point(-145,-180,200),new Point(-70, -195, 220), new Point(-95, -195, 240) )
                        .setEmission(new Color(173, 130, 55)),
                new Polygon(new Point(-70, -195, 220),new Point(-95, -195, 240),new Point(-100, -240, 250), new Point(-75, -240, 230) )
                        .setEmission(new Color(173, 130, 55)),

                //Right leg
                new Polygon(new Point(-135,-180,200),new Point(-110,-180,180),new Point(-35, -185, 220), new Point(-60, -185, 240) )
                        .setEmission(new Color(173, 130, 55)),
                new Polygon(new Point(-35, -185, 220),new Point(-60, -185, 240),new Point(-65, -240, 250), new Point(-40, -240, 230) )
                        .setEmission(new Color(173, 130, 55))

        );
//
//        scene._lightSourceList.add(new PointLight(new Color(40, 80, 100), new Point(1,199,100)).setKq(0.001));
//        scene._lightSourceList.add(new PointLight(new Color(255,255,255), new Point(-45,-150,80)).setKq(0.001));
//        scene._lightSourceList.add(new SpotLight(new Color(31, 0, 125), new Point(0, -200, 170), new Vector(0,20,1)).setKq(0.00001));
//        scene._lightSourceList.add(new SpotLight(new Color(31, 0, 125), new Point(1,-240,250), new Vector(-1,0.25,1)).setKq(0.00001));


        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.blue), 0.15));
        ImageWriter imageWriter = new ImageWriter("finalPicturtest4", 600, 600);


//        Render render = new Render()
////                .setSuperSampling(true)
//                .setAdaptiveGrid(true)
//                .setCamera(camera)
//                .setRayTracerBase(new SimpleRayTracer(scene))
//                .setImageWriter(imageWriter)
//                .setMultithreading(0)
//                .setDebugPrint();

//        render.renderImage();
//        render.writeToImage();

//        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 1, 0), new Vector(0, 0, -1))
//                .setViewPlaneDistance(1000)
//                .setViewPlaneSize(800, 800)
//                .setApertureRadiusSize(30)
//                .setFocalDistance(new Point(0, 0, 1000).distance(new Point(-150,0,200)));

        camera.setLocation(new Point(10, 10, 1800))
                .setVPDistance(1000)
                .setVpSize(800, 800)
                .setRayTracer(new SimpleRayTracer(scene))
                .setImageWriter(new ImageWriter("the room", 600, 600))
                .setMultithreading(0)
                .build()
                .renderImage()
                .writeToImage();
    }
}
