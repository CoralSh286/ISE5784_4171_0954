package mainModel;

import org.junit.jupiter.api.Test;
import static java.awt.Color.*;
import geometries.Sphere;
import geometries.Triangle;
import lighting.DirectionalLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

public class Picture
{
    @Test
    public void PictureTest()
    {
        Scene scene = new Scene("Picture2")
                .setBackground(new Color(RED));

//        Camera camera = new Camera(new Point(0,0,1700), new Vector(0, 0, -1), new Vector(0, 1, 0))
//                .setVPSize(150, 150).setVPDistance(1000);

        final Camera.Builder camera = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setLocation(new Point(0, 0, 1700))
                .setVpSize(150, 150).setVPDistance(1000);


        Material spMaterial = new Material().setKd(0.6).setKs(0.9).setNShininess(3000).setKt(0.0).setKr(0.0);
        Material spMaterial1 = new Material().setKd(0.0).setKs(0.0).setNShininess(1000).setKt(0);
        Material spMaterial2 = new Material().setKd(0.0).setKs(0.0).setNShininess(2000).setKt(0);
        Material spMaterial3 = new Material().setKt(0.1).setNShininess(1000);
        Material trMaterial1 = new Material().setKd(0.0).setKs(0.0).setNShininess(1000).setKt(0);
        Material spMaterial5 = new Material().setKd(0.0).setKs(0.0).setNShininess(1000).setKt(0.0).setKr(0);

        scene._geometries.add(

                //eyes
                new Sphere(new Point(-18, -5, -55), 7d).setEmission(Color.BLACK).setMaterial(spMaterial1),
                new Sphere(new Point(18, -5, -55), 7d).setEmission(Color.BLACK).setMaterial(spMaterial1),
                new Sphere(new Point(-15,-2, -50), 2d).setEmission(new Color(WHITE)).setMaterial(spMaterial2),
                new Sphere(new Point(21,-2, -50), 2d).setEmission(new Color(WHITE)).setMaterial(spMaterial2),

                //head
                new Sphere(new Point(0, 0, -100), 50d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(0, 0, -150), 53d).setEmission(Color.BLACK).setMaterial(spMaterial),

                //body
                new Sphere(new Point(0, -50, -100), 45d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(0, -54, -150), 50d).setEmission(Color.BLACK).setMaterial(spMaterial),

                //belly button
                new Sphere(new Point(0, -70, -50), 2d).setEmission(Color.BLACK).setMaterial(spMaterial1),

                //shirt
                new Triangle(new Point(-43, -60, -55), new Point(43, -60, -55), new Point(42.5, -35, -55))
                        .setEmission(Color.BLACK).setMaterial(new Material().setKr(0.2)),
                new Triangle(new Point(-43, -60, -55), new Point(-42.5, -35, -55), new Point(42.5, -35, -55))
                        .setEmission(Color.BLACK).setMaterial(new Material().setKr(0.2)),

                //hands
                new Sphere(new Point(45, -47, -50), 10d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(-45, -47, -50), 10d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(45, -47, -55), 11.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
                new Sphere(new Point(-45, -47, -55), 11.5d).setEmission(Color.BLACK).setMaterial(spMaterial),

                //legs
                new Sphere(new Point(37, -80, -50), 13d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(-37, -80, -50), 13d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(37, -80, -55), 14.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
                new Sphere(new Point(-37, -80, -55), 14.5d).setEmission(Color.BLACK).setMaterial(spMaterial),

                //ears
                new Sphere(new Point(37, 37, -100), 20d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(-37, 37, -100), 20d).setEmission(new Color(ORANGE)).setMaterial(spMaterial),
                new Sphere(new Point(37, 37, -150), 23d).setEmission(Color.BLACK).setMaterial(spMaterial),
                new Sphere(new Point(-37, 37, -150), 23d).setEmission(Color.BLACK).setMaterial(spMaterial),

                //nose
                new Triangle(new Point(-6,-10,-20), new Point(6,-10,-20), new Point(0,-15,-20)).setMaterial(trMaterial1),

                //mouth
                new Triangle(new Point(-7,-18,-20), new Point(7,-18,-20), new Point(0,-20,-20)).setMaterial(trMaterial1),


                //floor
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -115), new Point(150, 0, -150))  //floor
                        .setEmission(new Color(200,80,79)).setMaterial(new Material().setKr(0.05).setNShininess(20000)),
                new Triangle(new Point(-150, -150, -115), new Point(-150, -5, -150), new Point(150, 0, -150))  //floor
                        .setEmission(new Color(200,80,79)).setMaterial(new Material().setKr(0.05).setNShininess(20000)),


                //letters
                new Triangle(new Point(-95,-45,-100), new Point(-95.5,-45,-100), new Point(-95,-80,-100)).setMaterial(trMaterial1),
                new Triangle(new Point(-105,-45,-100), new Point(-105.5,-45,-100), new Point(-105,-80,-100)).setMaterial(trMaterial1),
                new Triangle(new Point(-105,-48,-95), new Point(-88,-48,-95), new Point(-105,-48.5,-95)).setMaterial(trMaterial1),

                //jar
                new Sphere(new Point(-100, -75, -100), 23d).setEmission(new Color(CYAN)).setMaterial(spMaterial3),
                new Triangle(new Point(-123, -75, -100), new Point(-77, -75, -100), new Point(-77, -35, -100))
                        .setEmission(new Color(CYAN)).setMaterial(spMaterial3),
                new Triangle(new Point(-123, -75, -100), new Point(-123, -35, -100), new Point(-77, -35, -100))
                        .setEmission(new Color(CYAN)).setMaterial(spMaterial3));

        //honey
        for(int i=-78; i>= -122; i--)
        {
            scene._geometries.add(new Sphere(new Point(i, -35, -100), 3d).setEmission(new Color(YELLOW)).setMaterial(spMaterial));
            if(i%7 == 0)
            {
                scene._geometries.add(new Sphere(new Point(i, -37, -100), 3d).setEmission(new Color(YELLOW)).setMaterial(spMaterial));
            }
            if(i == -90)
            {
                scene._geometries.add(new Sphere(new Point(i, -41, -100), 3d).setEmission(new Color(YELLOW)).setMaterial(spMaterial));
            }
            if(i == -110)
            {
                scene._geometries.add(new Sphere(new Point(i, -60, -50), 2d).setEmission(new Color(YELLOW)).setMaterial(spMaterial));
                scene._geometries.add(new Sphere(new Point(i, -62.5, -50), 3d).setEmission(new Color(YELLOW)).setMaterial(spMaterial));
            }
        }
		 		//wallpaper
		 		for(int j = -140; j <= 250; j+=30)
		 		{
		 		for(int i = -250; i <= 250; i+=30)

			 		{
			 			scene._geometries.add(new Sphere(new Point(i, j, -1700), 10d).setEmission(new Color(CYAN)).setMaterial(spMaterial5));
			 		}
		 		}


        scene._geometries.add(new Sphere(new Point(500, 500, -10000), 200d).setEmission(new Color(CYAN)).setMaterial(spMaterial5));
        scene._geometries.add(new Sphere(new Point(-30, 30, 1000), 12d).setEmission(new Color(CYAN)).setMaterial(spMaterial5));

        scene._lights.add(new SpotLight(new Color(WHITE), new Point(-250, 400, 1500), new Vector(-40,-1, -2))
                .setKl(0.000000004).setKq(0.000000006));
        scene._lights.add(new DirectionalLight( new Color(150,150,50), new Vector(-50, -1, -1))); //purplish

        camera.setImageWriter(new ImageWriter("Picture", 500, 500)) //
                .setRayTracer(new SimpleRayTracer(scene)) //
                .setMultithreading(0)//
                .build()
                .renderImage() //
                .writeToImage();
    }

//
//    @Test
//    public void PictureDTest()
//    {
//        Scene scene = new Scene("PictureD")
//                .setBackground(Color.RED);
//
//        Camera camera = new Camera(new Point(0,0,1700), new Vector(0, 0, -1), new Vector(0, 1, 0))
//                .setVPSize(150, 150).setVPDistance(1000)
//                .setDepthOfFieldFlag(true).setNumOfPoints(100).setApertureSize(1).setFocalPlaneDis(1800)
//                .setAntiAliasing(false).setGridSize(4);
//
//        Material spMaterial = new Material().setkD(0.6).setkS(0.9).setnShininess(3000).setkT(0.0).setkR(0.0);
//        Material spMaterial1 = new Material().setkD(0.0).setkS(0.0).setnShininess(1000).setkT(0);
//        Material spMaterial2 = new Material().setkD(0.0).setkS(0.0).setnShininess(2000).setkT(0);
//        Material spMaterial3 = new Material().setkT(0.1).setnShininess(1000);
//        Material trMaterial1 = new Material().setkD(0.0).setkS(0.0).setnShininess(1000).setkT(0);
//        Material spMaterial5 = new Material().setkD(0.0).setkS(0.0).setnShininess(1000).setkT(0.0).setkR(0);
//
//        scene._geometries.add(
//
//                //eyes
//                new Sphere(new Point(-18, -5, -55), 7d).setEmission(Color.BLACK).setMaterial(spMaterial1),
//                new Sphere(new Point(18, -5, -55), 7d).setEmission(Color.BLACK).setMaterial(spMaterial1),
//                new Sphere(new Point(-15,-2, -50), 2d).setEmission(Color.WHITE).setMaterial(spMaterial2),
//                new Sphere(new Point(21,-2, -50), 2d).setEmission(Color.WHITE).setMaterial(spMaterial2),
//
//                //head
//                new Sphere(new Point(0, 0, -100), 50d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(0, 0, -150), 53d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //body
//                new Sphere(new Point(0, -50, -100), 45d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(0, -54, -150), 50d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //belly button
//                new Sphere(new Point(0, -70, -50), 2d).setEmission(Color.BLACK).setMaterial(spMaterial1),
//
//                //shirt
//                new Triangle(new Point(-43, -60, -55), new Point(43, -60, -55), new Point(42.5, -35, -55))
//                        .setEmission(Color.BLACK).setMaterial(new Material().setkR(0.2)),
//                new Triangle(new Point(-43, -60, -55), new Point(-42.5, -35, -55), new Point(42.5, -35, -55))
//                        .setEmission(Color.BLACK).setMaterial(new Material().setkR(0.2)),
//
//                //hands
//                new Sphere(new Point(45, -47, -50), 10d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(-45, -47, -50), 10d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(45, -47, -55), 11.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//                new Sphere(new Point(-45, -47, -55), 11.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //legs
//                new Sphere(new Point(37, -80, -50), 13d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(-37, -80, -50), 13d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(37, -80, -55), 14.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//                new Sphere(new Point(-37, -80, -55), 14.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //ears
//                new Sphere(new Point(37, 37, -100), 20d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(-37, 37, -100), 20d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(37, 37, -150), 23d).setEmission(Color.BLACK).setMaterial(spMaterial),
//                new Sphere(new Point(-37, 37, -150), 23d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //nose
//                new Triangle(new Point(-6,-10,-20), new Point(6,-10,-20), new Point(0,-15,-20)).setMaterial(trMaterial1),
//
//                //mouth
//                new Triangle(new Point(-7,-18,-20), new Point(7,-18,-20), new Point(0,-20,-20)).setMaterial(trMaterial1),
//
//
//                //floor
//                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -115), new Point(150, 0, -150))  //floor
//                        .setEmission(new Color(200,80,79)).setMaterial(new Material().setkR(0.05).setnShininess(20000)),
//                new Triangle(new Point(-150, -150, -115), new Point(-150, -5, -150), new Point(150, 0, -150))  //floor
//                        .setEmission(new Color(200,80,79)).setMaterial(new Material().setkR(0.05).setnShininess(20000)),
//
//                //letters
//                new Triangle(new Point(-95,-45,-100), new Point(-95.5,-45,-100), new Point(-95,-80,-100)).setMaterial(trMaterial1),
//                new Triangle(new Point(-105,-45,-100), new Point(-105.5,-45,-100), new Point(-105,-80,-100)).setMaterial(trMaterial1),
//                new Triangle(new Point(-105,-48,-95), new Point(-88,-48,-95), new Point(-105,-48.5,-95)).setMaterial(trMaterial1),
//
//                //jar
//                new Sphere(new Point(-100, -75, -100), 23d).setEmission(Color.CYAN).setMaterial(spMaterial3),
//                new Triangle(new Point(-123, -75, -100), new Point(-77, -75, -100), new Point(-77, -35, -100))
//                        .setEmission(Color.CYAN).setMaterial(spMaterial3),
//                new Triangle(new Point(-123, -75, -100), new Point(-123, -35, -100), new Point(-77, -35, -100))
//                        .setEmission(Color.CYAN).setMaterial(spMaterial3));
//
//        //honey
//        for(int i=-78; i>= -122; i--)
//        {
//            scene._geometries.add(new Sphere(new Point(i, -35, -100), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            if(i%7 == 0)
//            {
//                scene._geometries.add(new Sphere(new Point(i, -37, -100), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            }
//            if(i == -90)
//            {
//                scene._geometries.add(new Sphere(new Point(i, -41, -100), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            }
//            if(i == -110)
//            {
//                scene._geometries.add(new Sphere(new Point(i, -60, -50), 2d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//                scene._geometries.add(new Sphere(new Point(i, -62.5, -50), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            }
//        }
//
//
//        scene._geometries.add(new Sphere(new Point(500, 500, -10000), 200d).setEmission(Color.CYAN).setMaterial(spMaterial5));
//        scene._geometries.add(new Sphere(new Point(-30, 30, 1000), 12d).setEmission(Color.CYAN).setMaterial(spMaterial5));
//
//
//
//        scene._lights.add(new SpotLight(Color.WHITE, new Point(-250, 400, 1500), new Vector(-40,-1, -2))
//                .setkL(0.000000004).setkQ(0.000000006));
//        scene._lights.add(new DirectionalLight( new Color(150,150,50), new Vector(-50, -1, -1))); //purplish
//
//
//
//        camera.setImageWriter(new ImageWriter("PictureD", 500, 500))
//                .setRayTracerBase(new RayTracerBasic(scene))
//                .setMultiThreading(3)
//                .setDebugPrint(0.2)
//                .renderImageThreaded()
////         .renderImage()
//                .writeToImage();
//
//
//    }



//
//
//    @Test
//    public void PictureD1Test()
//    {
//        Scene scene = new Scene("PictureD1")
//                .setBackground(Color.RED);
//
//        Camera camera = new Camera(new Point(0,0,1700), new Vector(0, 0, -1), new Vector(0, 1, 0))
//                .setVPSize(150, 150).setVPDistance(1000)
//                .setDepthOfFieldFlag(false).setNumOfPoints(100).setApertureSize(1).setFocalPlaneDis(1800)
//                .setAntiAliasing(true).setGridSize(4);
//
//        Material spMaterial = new Material().setkD(0.6).setkS(0.9).setnShininess(3000).setkT(0.0).setkR(0.0);
//        Material spMaterial1 = new Material().setkD(0.0).setkS(0.0).setnShininess(1000).setkT(0);
//        Material spMaterial2 = new Material().setkD(0.0).setkS(0.0).setnShininess(2000).setkT(0);
//        Material spMaterial3 = new Material().setkT(0.1).setnShininess(1000);
//        Material trMaterial1 = new Material().setkD(0.0).setkS(0.0).setnShininess(1000).setkT(0);
//        Material spMaterial5 = new Material().setkD(0.0).setkS(0.0).setnShininess(1000).setkT(0.0).setkR(0);
//
//        scene._geometries.add(
//
//                //eyes
//                new Sphere(new Point(-18, -5, -55), 7d).setEmission(Color.BLACK).setMaterial(spMaterial1),
//                new Sphere(new Point(18, -5, -55), 7d).setEmission(Color.BLACK).setMaterial(spMaterial1),
//                new Sphere(new Point(-15,-2, -50), 2d).setEmission(Color.WHITE).setMaterial(spMaterial2),
//                new Sphere(new Point(21,-2, -50), 2d).setEmission(Color.WHITE).setMaterial(spMaterial2),
//
//                //head
//                new Sphere(new Point(0, 0, -100), 50d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(0, 0, -150), 53d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //body
//                new Sphere(new Point(0, -50, -100), 45d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(0, -54, -150), 50d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //belly button
//                new Sphere(new Point(0, -70, -50), 2d).setEmission(Color.BLACK).setMaterial(spMaterial1),
//
//                //shirt
//                new Triangle(new Point(-43, -60, -55), new Point(43, -60, -55), new Point(42.5, -35, -55))
//                        .setEmission(Color.BLACK).setMaterial(new Material().setkR(0.2)),
//                new Triangle(new Point(-43, -60, -55), new Point(-42.5, -35, -55), new Point(42.5, -35, -55))
//                        .setEmission(Color.BLACK).setMaterial(new Material().setkR(0.2)),
//
//                //hands
//                new Sphere(new Point(45, -47, -50), 10d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(-45, -47, -50), 10d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(45, -47, -55), 11.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//                new Sphere(new Point(-45, -47, -55), 11.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //legs
//                new Sphere(new Point(37, -80, -50), 13d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(-37, -80, -50), 13d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(37, -80, -55), 14.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//                new Sphere(new Point(-37, -80, -55), 14.5d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //ears
//                new Sphere(new Point(37, 37, -100), 20d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(-37, 37, -100), 20d).setEmission(Color.ORANGE).setMaterial(spMaterial),
//                new Sphere(new Point(37, 37, -150), 23d).setEmission(Color.BLACK).setMaterial(spMaterial),
//                new Sphere(new Point(-37, 37, -150), 23d).setEmission(Color.BLACK).setMaterial(spMaterial),
//
//                //nose
//                new Triangle(new Point(-6,-10,-20), new Point(6,-10,-20), new Point(0,-15,-20)).setMaterial(trMaterial1),
//
//                //mouth
//                new Triangle(new Point(-7,-18,-20), new Point(7,-18,-20), new Point(0,-20,-20)).setMaterial(trMaterial1),
//
//
//                //floor
//                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -115), new Point(150, 0, -150))  //floor
//                        .setEmission(new Color(200,80,79)).setMaterial(new Material().setkR(0.05).setnShininess(20000)),
//                new Triangle(new Point(-150, -150, -115), new Point(-150, -5, -150), new Point(150, 0, -150))  //floor
//                        .setEmission(new Color(200,80,79)).setMaterial(new Material().setkR(0.05).setnShininess(20000)),
//
//                //letters
//                new Triangle(new Point(-95,-45,-100), new Point(-95.5,-45,-100), new Point(-95,-80,-100)).setMaterial(trMaterial1),
//                new Triangle(new Point(-105,-45,-100), new Point(-105.5,-45,-100), new Point(-105,-80,-100)).setMaterial(trMaterial1),
//                new Triangle(new Point(-105,-48,-95), new Point(-88,-48,-95), new Point(-105,-48.5,-95)).setMaterial(trMaterial1),
//
//                //jar
//                new Sphere(new Point(-100, -75, -100), 23d).setEmission(Color.CYAN).setMaterial(spMaterial3),
//                new Triangle(new Point(-123, -75, -100), new Point(-77, -75, -100), new Point(-77, -35, -100))
//                        .setEmission(Color.CYAN).setMaterial(spMaterial3),
//                new Triangle(new Point(-123, -75, -100), new Point(-123, -35, -100), new Point(-77, -35, -100))
//                        .setEmission(Color.CYAN).setMaterial(spMaterial3));
//
//        //honey
//        for(int i=-78; i>= -122; i--)
//        {
//            scene._geometries.add(new Sphere(new Point(i, -35, -100), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            if(i%7 == 0)
//            {
//                scene._geometries.add(new Sphere(new Point(i, -37, -100), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            }
//            if(i == -90)
//            {
//                scene._geometries.add(new Sphere(new Point(i, -41, -100), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            }
//            if(i == -110)
//            {
//                scene._geometries.add(new Sphere(new Point(i, -60, -50), 2d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//                scene._geometries.add(new Sphere(new Point(i, -62.5, -50), 3d).setEmission(Color.YELLOW).setMaterial(spMaterial));
//            }
//        }
//        //wallpaper
////		 		for(int j = -140; j <= 250; j+=60)
////		 		{
////		 		for(int i = -250; i <= 250; i+=60)
////
////			 		{
////			 			scene.geometries.add(new Sphere(40d, new Point(i, j, -1700)).setEmission(Color.CYAN).setMaterial(spMaterial5));
////			 		}
////		 		}
//
//        scene._geometries.add(new Sphere(new Point(500, 500, -10000), 200d).setEmission(Color.CYAN).setMaterial(spMaterial5));
//        scene._geometries.add(new Sphere(new Point(-30, 30, 1000), 12d).setEmission(Color.CYAN).setMaterial(spMaterial5));
//
//
//
//        scene._lights.add(new SpotLight(Color.WHITE, new Point(-250, 400, 1500), new Vector(-40,-1, -2))
//                .setkL(0.000000004).setkQ(0.000000006));
//        scene._lights.add(new DirectionalLight( new Color(150,150,50), new Vector(-50, -1, -1))); //purplish
//
//
//
//
//        camera.setImageWriter(new ImageWriter("PictureD1", 500, 500))
//                .setRayTracerBase(new RayTracerBasic(scene))
//                .setMultiThreading(3)
//                .setDebugPrint(0.2)
//                .renderImageThreaded()
////         .renderImage()
//                .writeToImage();
//
//
//    }

}
