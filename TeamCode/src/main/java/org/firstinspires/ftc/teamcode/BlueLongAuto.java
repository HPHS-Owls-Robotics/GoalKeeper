package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class BlueLongAuto extends LinearOpMode {

    int tag=0;

    int color =0;
    int sleepTimer =3000;
    int scanTimer=3000;
    Bot2MoveSys m;
    ArmSys as;
    OpticSys o;

    @Override
    public void runOpMode() throws InterruptedException {
       // m= new Bot2MoveSys(hardwareMap);
        //as = new ArmSys(hardwareMap);
        //a= new EOCVAPRILTAGS(hardwareMap);
        o= new OpticSys(hardwareMap,1);
        //a.initAprilTag();

        int tag=0;
        int go=0;
        telemetry.addData("one", "moving right");
        telemetry.update();
        o.initOpenCV(); // set hardwaremap, ie set cameras
        o.startOpenCV();// start streaming, set pipeline
        waitForStart();
        if(opModeIsActive())
        {
            sleep(3000);
            go = o.runBlue();
            telemetry.addData("run", go);
            telemetry.update();
            sleep(3000);

//            telemetry.addData("two", "moving right");
//            telemetry.update();
//            as.sweep1();
//            sleep(3000);
//
//            m.right(-52);
//            telemetry.addData("one", "moving right");
//            telemetry.update();
//            sleep(3000);
//            go= o.runRed();
//            telemetry.addData("two",go);
//            telemetry.update();
//            sleep(2000);
//
//            if(go==1)//leftmost, far from backboard
//            {
//                // m.forward(18);
//                //sleep(3000);
//
//                as.sweep1();
//                sleep(3000);
//
//                m.forward(-18);
//                sleep(3000);
//
//
//            }
//            else if(go==2)//middle
//            {
//                m.forward(-10);
//                sleep(3000);
//                m.right(-12);
//                sleep(3000);
//                m.right(12);
//                sleep(3000);
//                m.forward(-8);
//                sleep(1000);
//
//
//            }
//            else if(go==3)//close to backboard
//            {
//                m.forward(-18);
//                sleep(3000);
//                as.sweep1();
//                sleep(3000);
//
//
//            }
//
//            m.forward(84);
//            sleep(3000);
//            o.closeOpenCV();
//            o.initAprilTags();
//            o.startAprilTags();
//
//            o.getTag();
//            telemetry.addData("tag",o.getTag());
//            telemetry.addData("X",o.getX());
//            telemetry.addData("Y",o.getY());
//            telemetry.update();
//
//            findTag(tag);
//            sleep(3000);

        }


    }

    public void findTag(int t)
    {
        if(color!=1)
        {
            t+=3;
        }

        if(o.getTag()==t)
        {
            m.forward(20);
        }
        else if(o.getTag()>t)
        {
            m.right(10);
            sleep(1000);
            m.forward(20);
        }
        else if(o.getTag()<t)
        {
            m.right(-10);
            sleep(1000);
            m.forward(20);
        }

    }

//    public void findTag(int tag)
//    {
//        if(color!=1)
//        {
//            tag+=3;
//        }
//        if( a.getTag()== tag+3)
//        {
//            driveToTag();
//        }
//        else if(a.getTag()< tag)
//        {
//            m.rotate(90);
//            m.forward(-10 );
//            m.rotate(90);
//            findTag(tag);
//
//        }
//        else if(a.getTag()> tag)
//        {
//            m.rotate(-90);
//            m.forward(10);
//            m.rotate(-90);
//            findTag(tag);
//        }
//    }
    // Drive up close to tag
//    public void driveToTag()
//    {
//        double x = a.getX();
//        double y = a.getY();
//        double angle =  Math.tan(x/y);
//        int length = (int)Math.sqrt(Math.pow(x,2)+Math.pow(y,2)); // set distance to travel as the hypotenuse of a triangle with x and y as sides
//        m.rotate((int) angle);
//        m.forward(length);
//        m.rotate(-90+(int)angle);
//    }
}
