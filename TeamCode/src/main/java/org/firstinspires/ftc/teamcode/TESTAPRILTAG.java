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
    OpticSysOpenCV o;
    OpticSysAprilTag a;

    @Override
    public void runOpMode() throws InterruptedException {
           m= new Bot2MoveSys(hardwareMap);
           as = new ArmSys(hardwareMap);
            a= new OpticSysAprilTag(hardwareMap);
            o= new OpticSysOpenCV(hardwareMap,1);
            //a.initAprilTag();
            o.initOpenCV();

            int tag=0;
            int go=0;

            waitForStart();
            if(opModeIsActive())
            {
                telemetry.addData("going", m.getCurrentTicks());
                telemetry.update();
                o.startOpenCV();
                m.right(-24);
                sleep(3000);
                go= o.run();






                o.closeOpenCV();

                a.initAprilTag();
                a.getTag();
                telemetry.addData(String.valueOf(a.getTag()),a.getTag());


                sleep(1000);

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
