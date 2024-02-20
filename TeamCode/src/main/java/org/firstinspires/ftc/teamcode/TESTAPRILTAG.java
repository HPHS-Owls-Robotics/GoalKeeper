package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous
public class TESTAPRILTAG extends LinearOpMode {

    int tag=0;

    int color =0;
    int sleepTimer =3000;
    int scanTimer=3000;
    Bot2MoveSys m;
    ArmSys as;
    OpticSys o;

    @Override
    public void runOpMode() throws InterruptedException {
        m= new Bot2MoveSys(hardwareMap);
        as = new ArmSys(hardwareMap);
        //a= new EOCVAPRILTAGS(hardwareMap);
        o= new OpticSys(hardwareMap,0);
        //a.initAprilTag();

        int tag=0;
        int go=0;

        o.initOpenCV();
        o.startOpenCV();
        waitForStart();
        if(opModeIsActive())
            {
//                as.trap();
//                sleep(3000);
                telemetry.addData("going", m.getCurrentTicks());
                telemetry.update();
                //o.startOpenCV();
                sleep(3000);
                go= o.runRed();

                if(go==2)
                {
                    telemetry.addData("going", "middle");
                    telemetry.update();
                    m.right(55);
                    sleep(6000);
                    as.trap();
                    sleep(3000);
                    m.right(-16);
                    sleep(3000);
                    tag=2;
                }
                else
                {
                    m.right(24);

                    if(go==3)
                    {
                        telemetry.addData("going", "left");
                        telemetry.update();
                        tag=3;
                        m.forward(12);
                        as.trap();
                        m.forward(-12);

                    }
                    else
                    {
                        tag=1;
                        telemetry.addData("going", "right");
                        telemetry.update();
                        m.forward(-36);
                        sleep(3000);
                        as.trap();
                        sleep(3000);
                        m.forward(12);
                        sleep(3000);
                        m.right(-24);
                        sleep(3000);
                        m.forward(24);
                        sleep(3000);



                    }
                }

                m.forward(36);
                telemetry.addData("one", "test igf crash");
                telemetry.update();

                o.closeOpenCV();

                o.initAprilTags();
                o.startAprilTags();
                sleep(3000);
                telemetry.addData("two", "see igf crash");
                telemetry.update();

                sleep(3000);
//
//
               o.getTag();
                telemetry.addData("tag",o.getTag());
                telemetry.addData("X",o.getX());
                telemetry.addData("Y",o.getY());
                telemetry.update();

                findTag(tag);
                sleep(1000);

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
