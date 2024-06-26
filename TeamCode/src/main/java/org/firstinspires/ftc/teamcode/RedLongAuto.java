package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name= "red far")
public class RedLongAuto extends LinearOpMode {

    int tag=0;

    int color =0;
    int sleepTimer =3000;
    int scanTimer=3000;
    Bot2MoveSys m;
    ArmSys as;
    OpticSysPhonecam o;



    @Override
    public void runOpMode() throws InterruptedException {
        o= new OpticSysPhonecam(hardwareMap,0);
        o.initOpenCV();
        o.startOpenCV();


        waitForStart();
        if(opModeIsActive())
        {

            //sleep(3000);

            while(opModeIsActive())
            {
                sleep(1000);
                telemetry.addData("location", o.getLocation());

                telemetry.update();
            }


        }

    }

        public void findTag(int t)
        {
//            if(color!=1)
//            {
//                t+=3;
//            }
//
//            if(o.getTag()==t)
//            {
//                m.forward(20);
//            }
//            else if(o.getTag()>t)
//            {
//                m.right(10);
//                sleep(1000);
//                m.forward(20);
//            }
//            else if(o.getTag()<t)
//            {
//                m.right(-10);
//                sleep(1000);
//                m.forward(20);
//            }

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
